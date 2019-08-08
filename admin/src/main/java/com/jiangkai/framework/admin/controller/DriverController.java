package com.jiangkai.framework.admin.controller;

import com.jiangkai.framework.admin.bean.DriverBean;
import com.jiangkai.framework.admin.bean.DriverSumBean;
import com.jiangkai.framework.admin.common.Result;
import com.jiangkai.framework.admin.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: zvbb
 * Date: 2019/6/11
 * Description:
 */
@RestController
@RequestMapping("/driver")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @GetMapping("/page")
    public Result page(DriverBean driverBean) {
        return driverService.page(driverBean);
    }

    @GetMapping("/baseInfo")
    public Result baseInfo(DriverSumBean driverSumBean) {
        return driverService.baseInfo(driverSumBean);
    }

    @GetMapping("/htBrTrend")
    public Result htBrTrend(DriverSumBean driverSumBean) {
        return driverService.htBrTrend(driverSumBean);
    }

    @GetMapping("/sleepEffTrend")
    public Result sleepEffTrend(DriverSumBean driverSumBean) {
        return driverService.sleepEffTrend(driverSumBean);
    }

    @GetMapping("/sleepTimeTrend")
    public Result sleepTimeTrend(DriverSumBean driverSumBean) {
        return driverService.sleepTimeTrend(driverSumBean);
    }

    @GetMapping("/ringSleepKpi")
    public Result ringSleepKpi(DriverSumBean driverSumBean){
        return driverService.ringSleepKpi(driverSumBean);
    }

    /**
     * @Description 睡眠综合分析
     */
    @GetMapping("/sleepAnalysis")
    public Result sleepAnalysis(DriverSumBean driverSumBean) {
        return driverService.sleepAnalysis(driverSumBean);
    }

    @GetMapping("/driverInfo")
    public Result driverInfo(String no) {
        return driverService.driverInfo(no);
    }

    @GetMapping("/monitorRecords")
    public Result monitorRecords(DriverSumBean driverSumBean) {
        return driverService.monitorRecords(driverSumBean);
    }
}
