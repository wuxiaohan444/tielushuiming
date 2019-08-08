package com.jiangkai.framework.admin.timetask.summary;

import com.jiangkai.framework.admin.datasource.CustomDataSource;
import com.jiangkai.framework.admin.timetask.JobManager;
import com.jiangkai.framework.admin.timetask.summary.restructure.RetructureInterface;
import com.jiangkai.framework.admin.common.enums.DataName;
import com.jiangkai.framework.extdata.model.InfoBed;
import com.jiangkai.framework.source.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * User: zvbb
 * Date: 2019/5/14
 * Description:
 */
@Component
@RequiredArgsConstructor
public class DealDataHandler<T, R> extends SummaryHandler<HashMap<DataName, List>, HashMap<DataName, List>> {
    private final JobManager jobManager;

    @Override
    protected HashMap<DataName, List> deal(HashMap<DataName, List> request) {
        //原始数据
        HashMap<DataName, List> primaryData = request;
        HashMap assistMap = (HashMap) SummaryHelper.threadLocal.get();
        //数据源
        CustomDataSource customDataSource = (CustomDataSource) assistMap.get(DataName.CUSTOMDATASOURCE);
        //部门id
        Long deptId = customDataSource.getAddress().getDeptId();
        //Info_Bed表
        List<InfoBed> infoBedList = primaryData.get(DataName.PRIMARY_INFO_BED);
        RetructureInterface<List<InfoBed>, List> retructureInfoBed = (infoBedRecords) -> {
            List bedInfoList = new ArrayList();
            DataSource address = customDataSource.getAddress();
            infoBedRecords.forEach(infoRow -> {
                String serialno = infoRow.getSerialno();
                //新的床铺
                if (jobManager.isNewBed(serialno, deptId)) {
                    BedInfo bedInfo = new BedInfo();
                    //①拷贝Info_Bed中的相同属性
                    BeanUtils.copyProperties(infoRow, bedInfo);
                    //②bed_info中添加data_source_id字段的值
                    bedInfo.setDataSourceId(address.getId());
                    //③bed_info中添加dept_id字段的值
                    bedInfo.setDeptId(address.getDeptId());
                    bedInfoList.add(bedInfo);
                }
            });
            return bedInfoList;
        };
        List bedInfoList = this.retructureData(infoBedList, retructureInfoBed);

        //Info_Inteface表
        List infoIntefaceList = primaryData.get(DataName.PRIMARY_INFO_INTEFACE);
        RetructureInterface<List, List<BedRecord>> retructureInfoInteface = (infoIntefaceRecords) -> {
            List<BedRecord> recordInfoList = new ArrayList();
            infoIntefaceRecords.forEach(infoRow -> {
                DataSource address = customDataSource.getAddress();
                BedRecord bedRecord = new BedRecord();
                //①拷贝Info_Inteface中的相同属性
                BeanUtils.copyProperties(infoRow, bedRecord);
                //②bed_record中添加data_source_id字段的值
                bedRecord.setDataSourceId(address.getId());
                //③bed_record中添加dept_id字段的值
                bedRecord.setDeptId(address.getDeptId());
                recordInfoList.add(bedRecord);
            });
            return recordInfoList;
        };
        List<BedRecord> bedRecordList = this.retructureData(infoIntefaceList, retructureInfoInteface);

        //Driver
        RetructureInterface<List<BedRecord>, List> retructureDriver = (infoIntefaceRecords) -> {
            List driverList = new ArrayList();
            infoIntefaceRecords.forEach(bedRecord -> {
                //司机编号
                String driverWorkno = bedRecord.getDriverWorkno();
                if (jobManager.isNewDriver(driverWorkno, bedRecord.getDeptId())) {
                    Driver driver = new Driver();
                    //司机所在部门
                    driver.setDeptId(jobManager.getDriverDeptId(driverWorkno));
                    //司机编号
                    driver.setDriverNo(driverWorkno);
                    //司机姓名
                    String dirverName = bedRecord.getDriverName();
                    driver.setDriverName(dirverName);
                    //添加到driverList
                    driverList.add(driver);
                }
            });
            return driverList;
        };
        List driverList = this.retructureData(bedRecordList, retructureDriver);

        //bed_active表
        RetructureInterface<List<BedRecord>, List<BedActive>> retructureBedActive = (infoIntefaceRecords) -> {
            List bedActiveList = new ArrayList();
            infoIntefaceRecords.forEach(bedRecord -> {
                Date checkindate = bedRecord.getCheckindate();
                String serialno = bedRecord.getBedSerialno();
                List<BedActive> newActiveBed = jobManager.isNewActiveBed(deptId, checkindate, serialno);
                //添加到bedActiveList
                bedActiveList.addAll(newActiveBed);
            });
            return bedActiveList;
        };
        List activeBedList = this.retructureData(bedRecordList, retructureBedActive);

        //Info_Leave表
        List infoLeaveList = primaryData.get(DataName.PRIMARY_INFO_LEAVE);
        RetructureInterface<List, List> retructureInfoLeave = (infoLeaveRecords) -> {
            List leaveInfoList = new ArrayList();
            infoLeaveRecords.forEach(leaveRow -> {
                DataSource address = customDataSource.getAddress();
                LeaveBedInfo leaveBedInfo = new LeaveBedInfo();
                //①拷贝Info_leave中的相同属性
                BeanUtils.copyProperties(leaveRow, leaveBedInfo);
                //②leaveBedInfo中添加data_source_id字段的值
                leaveBedInfo.setDataSourceId(address.getId());
                //③leaveBedInfo中添加dept_id字段的值
                leaveBedInfo.setDeptId(address.getDeptId());
            });
            return leaveInfoList;
        };

        List leaveBedInfoList = this.retructureData(infoLeaveList, retructureInfoLeave);

        HashMap<DataName, List> localDataMap = new HashMap();
        localDataMap.put(DataName.NEW_BED_INFOS, bedInfoList);
        localDataMap.put(DataName.NEW_BEDRECORD, bedRecordList);
        localDataMap.put(DataName.NEW_DRIVERS, driverList);
        localDataMap.put(DataName.NEW_INFO_LEAVE, leaveBedInfoList);
        localDataMap.put(DataName.NEW_BED_ACTIVE, activeBedList);
        return localDataMap;
    }

    /**
     * @param dataList 原始表中的数据
     * @return
     * @Description 把原始表中的数据，转换成满足本地表的数据格式
     * @date 2019/5/15
     * @auther zvbb
     */
    private List retructureData(List dataList, RetructureInterface retructureInterface) {
        List resultList = new ArrayList();
        if (!CollectionUtils.isEmpty(dataList)) {
            return (List) retructureInterface.restructureData(dataList);
        }
        return resultList;
    }
}
