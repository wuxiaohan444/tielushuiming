package com.jiangkai.framework.admin.mapper;

import com.jiangkai.framework.admin.bean.DriverTrendBean;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * User: zvbb
 * Date: 2019/6/13
 * Description:
 */
@Mapper
@Component
public interface CustomSQL {

    @Select("select dept_id as deptId,max(ID) as ID from leave_bed_info group by dept_id")
    List<LeaveSection> getMaxDeptInfoLeaveID();


    /**
     * @Description 获取上次统计的状态
     */
    @Select("select top 1 * FROM summary_dept_bed WHERE SumType=1 AND deptid=#{deptId} ORDER BY update_time DESC;")
    List<StatusSection> getUpDealStatus(@Param("deptId") Long deptId);

    @Select("<script>SELECT \n" +
            "  MIN(MinHb) min,\n" +
            "  MAX(MaxHb) max,\n" +
            "  AVG(avgHb) avg\n" +
            "FROM\n" +
            "  [dbo].[bed_record] \n" +
            "WHERE\n" +
            "  Driver_WorkNo = #{driverNo}\n" +
            "  <when test='startTime!=null'> AND CheckInDate &gt;= #{startTime}</when>\n" +
            "  <when test='endTime!=null'>AND CheckInDate &lt; #{endTime} </when></script>")
    DriverTrendBean.HeartDetail getDriverHeartInfo(@Param("driverNo") String driverNo, @Param("startTime") Date startTime, @Param("endTime") Date endTime);


    @Select("<script>SELECT \n" +
            "  MIN(MinBr) min,\n" +
            "  MAX(MaxBr) max,\n" +
            "  AVG(avgBr) avg\n" +
            "FROM\n" +
            "  [dbo].[bed_record] \n" +
            "WHERE\n" +
            "  Driver_WorkNo = #{driverNo}\n" +
            "  <when test='startTime!=null'> AND CheckInDate &gt;= #{startTime}</when>\n" +
            "  <when test='endTime!=null'>AND CheckInDate &lt; #{endTime} </when></script>")
    DriverTrendBean.BreathDetail getDriverBreathfo(@Param("driverNo") String driverNo, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    @Data
    class StatusSection {
        private Long deptId;
        private Integer mainId;
        private String unFinishRecords;
    }

    @Data
    class LeaveSection {
        private Long deptId;
        private Integer ID;
    }
}
