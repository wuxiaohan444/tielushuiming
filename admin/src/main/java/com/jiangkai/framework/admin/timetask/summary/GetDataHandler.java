package com.jiangkai.framework.admin.timetask.summary;


import com.jiangkai.framework.admin.common.enums.InfoIntefaceFinish;
import com.jiangkai.framework.admin.datasource.CustomDataSource;
import com.jiangkai.framework.admin.timetask.JobManager;
import com.jiangkai.framework.admin.common.enums.DataName;
import com.jiangkai.framework.extdata.mapper.*;
import com.jiangkai.framework.extdata.model.InfoBed;
import com.jiangkai.framework.extdata.model.InfoInteface;
import com.jiangkai.framework.extdata.model.InfoLeave;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SimpleSortSpecification;
import org.mybatis.dynamic.sql.where.condition.IsGreaterThan;
import org.mybatis.dynamic.sql.where.condition.IsIn;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * User: zvbb
 * Date: 2019/5/14
 * Description:
 */
@RequiredArgsConstructor
@Component
public class GetDataHandler extends SummaryHandler<CustomDataSource, HashMap<DataName, List>> {
    private final JobManager jobManager;

    @Override
    @Synchronized
    protected HashMap<DataName, List> deal(CustomDataSource request) {
        CustomDataSource customDataSource = request;
        //部门Id
        Long deptId = customDataSource.getAddress().getDeptId();
        HashMap assistMap = (HashMap) SummaryHelper.threadLocal.get();
//        System.out.println(Thread.currentThread().getId());
        if (null != assistMap) {
            assistMap.put(DataName.CUSTOMDATASOURCE, customDataSource);
        } else {
            assistMap = new HashMap();
            assistMap.put(DataName.CUSTOMDATASOURCE, customDataSource);
            SummaryHelper.threadLocal.set(assistMap);
        }

        HashMap<DataName, List> remoteData = new HashMap<>();

        //遍历Info_Bed表   (Info_Bed中的主键不是递增的,所以全量获取)
        List<InfoBed> infoBedList = customDataSource.findMapperDoMethod(InfoBedMapper.class, InfoBedMapper -> {
            InfoBedDynamicSqlSupport.InfoBed infoBed = InfoBedDynamicSqlSupport.infoBed;
            return InfoBedMapper.selectByExample().build().execute();
        });
        remoteData.put(DataName.PRIMARY_INFO_BED, infoBedList);

        //查询Info_Interface表    (当前使用MainID,增量读取)
        HashMap finalAssistMap = assistMap;
        List<InfoInteface> infoIntefaceList = customDataSource.findMapperDoMethod(InfoIntefaceMapper.class, InfoIntefaceMapper -> {
            InfoIntefaceDynamicSqlSupport.InfoInteface infoInteface = InfoIntefaceDynamicSqlSupport.infoInteface;
            Integer mainID = jobManager.getMaxMainId(deptId);
            if (null != mainID) {
                // > MainID
                QueryExpressionDSL<MyBatis3SelectModelAdapter<List<InfoInteface>>>.QueryExpressionWhereBuilder builder = InfoIntefaceMapper.selectByExample()
                        .where(infoInteface.mainid, IsGreaterThan.of(() -> mainID));
                //上次统计 finist != 2 的记录
                List<Integer> unFinishRecords = jobManager.getUnFinishRecords(deptId);
                if (Objects.nonNull(unFinishRecords) && !CollectionUtils.isEmpty(unFinishRecords)) {
                    builder.or(infoInteface.mainid, IsIn.of(unFinishRecords));
                }
                return builder.orderBy(SimpleSortSpecification.of(infoInteface.mainid.name()).descending())
                        .build()
                        .execute();
            }

            return InfoIntefaceMapper.selectByExample()
                    .orderBy(SimpleSortSpecification.of(infoInteface.mainid.name()).descending())
                    .build()
                    .execute();

        });

        List<InfoInteface> needDealRecords = this.updateSumStatus(deptId, infoIntefaceList);
        remoteData.put(DataName.PRIMARY_INFO_INTEFACE, needDealRecords);


        //查询Info_Leave   (当前使用ID,增量读取)
        HashMap finalAssistMap1 = assistMap;
        List<InfoLeave> leaveList = customDataSource.findMapperDoMethod(InfoLeaveMapper.class, InfoLeaveMapper -> {
            InfoLeaveDynamicSqlSupport.InfoLeave infoLeave = InfoLeaveDynamicSqlSupport.infoLeave;
            //where条件为ID
            Integer ID = jobManager.getMaxLeaveID(customDataSource.getAddress().getDeptId());
            if (null != ID) {
                // > ID
                return InfoLeaveMapper
                        .selectByExample()
                        .where(infoLeave.id, IsGreaterThan.of(() -> ID))
                        .orderBy(SimpleSortSpecification.of(infoLeave.id.name()).descending())
                        .build()
                        .execute();
            }
            return InfoLeaveMapper
                    .selectByExample()
                    .orderBy(SimpleSortSpecification.of(infoLeave.id.name()).descending())
                    .build()
                    .execute();
        });

        remoteData.put(DataName.PRIMARY_INFO_LEAVE, leaveList);
        this.updateLeaveMaxId(deptId, leaveList);

        return remoteData;
    }


    /**
     * @param
     * @return
     * @Description 更新下 info_leave表中
     * @date 2019/6/16
     * @auther zvbb
     */
    private void updateLeaveMaxId(Long deptId, List<InfoLeave> infoLeaveList) {
        if (!CollectionUtils.isEmpty(infoLeaveList)) {
            InfoLeave infoLeave = infoLeaveList.get(0);
            Integer id = infoLeave.getId();
            jobManager.setMaxLeaveID(deptId, id);
        }
    }

    /**
     * @Description 更新跟统计状态有关的数据
     */
    private List<InfoInteface> updateSumStatus(Long deptId, List<InfoInteface> infoIntefaceList) {
        //更新MainId
        this.updateMainId(deptId, infoIntefaceList);
        //更新UnFinishRecords(Finish字段不为2的字段)
        List<InfoInteface> needDealRecords = this.updateUnFinishRecords(deptId, infoIntefaceList);

        HashMap assistMap = (HashMap) SummaryHelper.threadLocal.get();
//        System.out.println(Thread.currentThread().getId());
        if (null != assistMap) {
            assistMap.put(DataName.MAINID, jobManager.getMaxMainId(deptId));
            assistMap.put(DataName.UNFINISHRECORS, jobManager.getUnFinishRecords(deptId));
        } else {
            assistMap = new HashMap();
            assistMap.put(DataName.MAINID, jobManager.getMaxMainId(deptId));
            assistMap.put(DataName.UNFINISHRECORS, jobManager.getUnFinishRecords(deptId));
            SummaryHelper.threadLocal.set(assistMap);
        }
        return needDealRecords;
    }

    /**
     * @param
     * @return
     * @Description 更新下 info_inteface表最大的mainid
     * @date 2019/6/13
     * @auther zvbb
     */
    private void updateMainId(Long deptId, List<InfoInteface> infoIntefaceList) {
        if (!CollectionUtils.isEmpty(infoIntefaceList)) {
            InfoInteface infoInteface = infoIntefaceList.get(0);
            Integer old = jobManager.getMaxMainId(deptId);
            Integer now = infoInteface.getMainid();
            if (Objects.isNull(old) || now > old) {
                jobManager.setMaxMainId(deptId, now);
            }
        }
    }

    /**
     * @Description 更新UnFinishRecords(info_inteface表Finish字段不为2的字段)
     * ,返回info_inteface表中需要处理记录
     */
    private List<InfoInteface> updateUnFinishRecords(Long deptId, List<InfoInteface> infoIntefaceList) {
        List<InfoInteface> needDealRecords = new ArrayList<>();
        if (!CollectionUtils.isEmpty(infoIntefaceList)) {
            List<Integer> unFinishRecords = new ArrayList<Integer>();
            for (int i = 0; i < infoIntefaceList.size(); i++) {
                InfoInteface infoInteface = infoIntefaceList.get(i);
                if (!infoInteface.getFinish().equals(InfoIntefaceFinish.FINISH_RECORD.getCode())) {
                    unFinishRecords.add(infoInteface.getMainid());
                } else {
                    //统计字段为空，放弃该条记录的统计
                    if (Objects.isNull(infoInteface.getTotaltime()) || Objects.isNull(infoInteface.getTotalsleept())
                            || Objects.isNull(infoInteface.getSleepeff()) || Objects.isNull(infoInteface.getSleeptime())
                            || Objects.isNull(infoInteface.getAutocallwake()) || Objects.isNull(infoInteface.getWarn())
                            || Objects.isNull(infoInteface.getQian()) || Objects.isNull(infoInteface.getShen())
                            || Objects.isNull(infoInteface.getWakeupdate()) || Objects.isNull(infoInteface.getFirstonbeddate())
                            || Objects.isNull(infoInteface.getAvgbr()) || Objects.isNull(infoInteface.getMinbr())
                            || Objects.isNull(infoInteface.getMaxbr()) || Objects.isNull(infoInteface.getAvghb())
                            || Objects.isNull(infoInteface.getMinhb()) || Objects.isNull(infoInteface.getMaxhb())) {
                        continue;
                    } else {
                        needDealRecords.add(infoInteface);
                    }
                }
            }
            jobManager.setUnFinishRecord(deptId, unFinishRecords);
        }
        return needDealRecords;
    }

    public static void main(String[] args) {
        System.out.println(Long.parseLong(null));
    }

}
