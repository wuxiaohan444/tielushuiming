package com.jiangkai.framework.admin;

import com.jiangkai.framework.admin.service.UserService;
import com.jiangkai.framework.admin.timetask.JobManager;
import com.jiangkai.framework.source.model.BedRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * User: zvbb
 * Date: 2019/5/13
 * Description:
 */
@SpringBootTest
@ComponentScan("com.jiangkai.framework.admin")
@RunWith(SpringRunner.class)
public class SleepMonitoringTest {
    @Autowired
    private UserService userService;

    @Autowired
    private JobManager jobManager;


    @Test
    public void testSleepAnalysisTable(){

    }

    /**
     * @param
     * @return
     * @Description 测试user接口
     * @date 2019/5/13
     * @auther zvbb
     */
    @Test
    public void testUser() {

    }

    /**
     * @param
     * @return
     * @Description 测试数据源的初始化;
     * 观察com.jiangkai.framework.admin.config.CustomApplicationRunner类的run方法
     * @date 2019/5/14
     * @auther zvbb
     */
    @Test
    public void testDataSourceInit() {
        try {
            Thread.sleep(14000000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTimeTask() {
        try {
            Thread.sleep(1000000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDynamicRedisDb() {
    }

    @Test
    public void testSetOperation() {
        jobManager.initContext();
        BedRecord bedRecord = new BedRecord();
    }
}
