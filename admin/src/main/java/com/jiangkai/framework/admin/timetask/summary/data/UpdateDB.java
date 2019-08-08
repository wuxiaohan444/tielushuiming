package com.jiangkai.framework.admin.timetask.summary.data;

import com.jiangkai.framework.admin.common.enums.HaveEdit;
import com.jiangkai.framework.admin.common.enums.DataName;
import com.jiangkai.framework.source.mapper.*;
import com.jiangkai.framework.source.model.*;
import lombok.RequiredArgsConstructor;
import org.mybatis.dynamic.sql.where.condition.IsEqualTo;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: zvbb
 * Date: 2019/5/17
 * Description:
 */
@Component
@RequiredArgsConstructor
public class UpdateDB {
    private final BedInfoMapper bedInfoMapper;
    private final BedRecordMapper bedRecordMapper;
    private final LeaveBedInfoMapper leaveBedInfoMapper;
    private final BedActiveMapper bedActiveMapper;
    private final DriverMapper driverMapper;
    private final SummaryDeptBedMapper summaryDeptBedMapper;
    private final SummaryDeptPersonMapper summaryDeptPersonMapper;

    /**
     * @param
     * @return
     * @Description 数据入库
     * @date 2019/5/15
     * @auther zvbb
     */
    @Async
    public void insertDB(HashMap<DataName, List> data, List<BedInfo> newBedInfoList,
                         List<Driver> newDriverList, List<BedActive> newBedActiveList) {
        List<BedRecord> bedRecordList = data.get(DataName.NEW_BEDRECORD);
        List<LeaveBedInfo> leaveBedInfoList = data.get(DataName.NEW_INFO_LEAVE);
        //插入bed_info
        if (!CollectionUtils.isEmpty(newBedInfoList)) {
            newBedInfoList.forEach(bedInfoRow -> {
                bedInfoMapper.insert(bedInfoRow);
            });
        }

        //插入driver
        if (!CollectionUtils.isEmpty(newDriverList)) {
            newDriverList.forEach(driver -> {
                driverMapper.insertSelective(driver);
            });
        }

        //插入bed_record
        if (!CollectionUtils.isEmpty(bedRecordList)) {
            bedRecordList.forEach(bedRecordRow -> {
                bedRecordMapper.insert(bedRecordRow);
            });
        }

        //插入leave_bed_info
        if (!CollectionUtils.isEmpty(leaveBedInfoList)) {
            leaveBedInfoList.forEach(leaveBedInfoRow -> {
                leaveBedInfoMapper.insert(leaveBedInfoRow);
            });
        }

        //插入bed_active
        if (!CollectionUtils.isEmpty(newBedActiveList)) {
            newBedActiveList.forEach(record -> {
                bedActiveMapper.insert(record);
            });
        }
    }

    /**
     * @param
     * @return
     * @Description 插入 床铺维度的 统计结果
     * @date 2019/6/13
     * @auther zvbb
     */
    @Async
    public void insertSumBedResut(ConcurrentHashMap<String, SummaryDeptBed> summaryDeptBedMap) {
        Set<Map.Entry<String, SummaryDeptBed>> entries = summaryDeptBedMap.entrySet();
        List<SummaryDeptBed> insertList = new ArrayList();
        synchronized (summaryDeptBedMap) {
            for (Map.Entry<String, SummaryDeptBed> entry : entries) {
                SummaryDeptBed tmp = new SummaryDeptBed();
                SummaryDeptBed value = entry.getValue();
                if (Objects.nonNull(value.getHaveedit())) {
                    //存在修改
                    if (value.getHaveedit().equals(HaveEdit.EDIT.getCode())) {
                        BeanUtils.copyProperties(value, tmp);
                        insertList.add(tmp);
                        value.setHaveedit(HaveEdit.NOEDIT.getCode());
                    }
                }
            }
        }

        SummaryDeptBedDynamicSqlSupport.SummaryDeptBed support = SummaryDeptBedDynamicSqlSupport.summaryDeptBed;
        for (int i = 0; i < insertList.size(); i++) {
            SummaryDeptBed tmp = insertList.get(i);
            List<SummaryDeptBed> existRecord = summaryDeptBedMapper.selectByExample().where(support.deptid, IsEqualTo.of(() -> tmp.getDeptid()))
                    .and(support.date, IsEqualTo.of(() -> tmp.getDate()))
                    .build().execute();
            if (existRecord.size() > 0) {
                tmp.setId(existRecord.get(0).getId());
                summaryDeptBedMapper.updateByPrimaryKeySelective(tmp);
            } else {
                summaryDeptBedMapper.insertSelective(tmp);
            }
        }
    }

    /**
     * @param
     * @return
     * @Description 插入 司机维度的 统计结果
     * @date 2019/6/13
     * @auther zvbb
     */
    @Async
    public void insertSumPersonResut(ConcurrentHashMap<String, SummaryDeptPerson> summaryDeptPersons) {
        Set<Map.Entry<String, SummaryDeptPerson>> entries = summaryDeptPersons.entrySet();
        List<SummaryDeptPerson> insertList = new ArrayList();
        synchronized (summaryDeptPersons) {
            for (Map.Entry<String, SummaryDeptPerson> entry : entries) {
                SummaryDeptPerson tmp = new SummaryDeptPerson();
                BeanUtils.copyProperties(entry.getValue(), tmp);
                insertList.add(tmp);
            }
        }

        SummaryDeptPersonDynamicSqlSupport.SummaryDeptPerson support = SummaryDeptPersonDynamicSqlSupport.summaryDeptPerson;
        for (int i = 0; i < insertList.size(); i++) {
            SummaryDeptPerson tmp = insertList.get(i);
            List<SummaryDeptPerson> existRecord = summaryDeptPersonMapper.selectByExample().where(support.deptid, IsEqualTo.of(() -> tmp.getDeptid()))
                    .and(support.date, IsEqualTo.of(() -> tmp.getDate()))
                    .build().execute();
            if (existRecord.size() > 0) {
                tmp.setId(existRecord.get(0).getId());
                summaryDeptPersonMapper.updateByPrimaryKeySelective(tmp);
            } else {
                summaryDeptPersonMapper.insertSelective(tmp);
            }
        }
    }
}
