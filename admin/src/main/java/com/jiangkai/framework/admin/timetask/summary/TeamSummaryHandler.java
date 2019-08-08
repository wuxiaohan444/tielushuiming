package com.jiangkai.framework.admin.timetask.summary;

import com.jiangkai.framework.admin.timetask.JobManager;
import com.jiangkai.framework.admin.timetask.summary.data.UpdateDB;
import com.jiangkai.framework.admin.timetask.summary.team.DeptBedDimensionSummary;
import com.jiangkai.framework.admin.timetask.summary.team.DeptPersonDimensionSummary;
import com.jiangkai.framework.admin.common.enums.DataName;
import com.jiangkai.framework.source.mapper.BedInfoMapper;
import com.jiangkai.framework.source.mapper.BedRecordMapper;
import com.jiangkai.framework.source.mapper.DriverMapper;
import com.jiangkai.framework.source.mapper.LeaveBedInfoMapper;
import com.jiangkai.framework.source.model.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;

/**
 * User: zvbb
 * Date: 2019/5/15
 * Description:
 */
@Component
@RequiredArgsConstructor
public class TeamSummaryHandler<T, R> extends SummaryHandler<HashMap<DataName, List>, R> {
    private final BedInfoMapper bedInfoMapper;
    private final BedRecordMapper bedRecordMapper;
    private final LeaveBedInfoMapper leaveBedInfoMapper;
    private final DriverMapper driverMapper;
    private final JobManager jobManager;
    private final UpdateDB updateDB;
    private final DeptBedDimensionSummary deptBedDimensionSummary;
    private final DeptPersonDimensionSummary deptPersonDimensionSummary;

    @Getter
    @Setter
    private HashMap<String, Long> deptNo2ID;

    @Override
    protected R deal(HashMap<DataName, List> request) {
        HashMap<DataName, List> data = (HashMap<DataName, List>) request;

        List<BedInfo> newBedInfoList = data.get(DataName.NEW_BED_INFOS);
        List<Driver> newDriverList = data.get(DataName.NEW_DRIVERS);
        List<BedActive> newBedActiveList = data.get(DataName.NEW_BED_ACTIVE);
        //数据入库(磁盘数据库)
        updateDB.insertDB(data, newBedInfoList, newDriverList, newBedActiveList);
        //团体综合统计
        summaryTeamData(data);
        return null;
    }

    /**
     * @param
     * @return
     * @Description 统计团体数据
     * @date 2019/5/15
     * @auther zvbb
     */
    public void summaryTeamData(HashMap<DataName, List> data) {
        List<BedRecord> bedRecordList = data.get(DataName.NEW_BEDRECORD);
        if (!CollectionUtils.isEmpty(bedRecordList)) {
            HashMap assistMap = (HashMap) SummaryHelper.threadLocal.get();
            deptBedDimensionSummary.sumaryEntry(bedRecordList, assistMap);
            deptPersonDimensionSummary.sumaryEntry(bedRecordList, assistMap);
        }
    }
}
