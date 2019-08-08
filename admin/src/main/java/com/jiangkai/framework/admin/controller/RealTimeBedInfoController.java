package com.jiangkai.framework.admin.controller;

import com.jiangkai.framework.admin.common.Result;
import com.jiangkai.framework.admin.service.RealTimeBedInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: zvbb
 * Date: 2019/6/18
 * Description:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/realTimeBedInfo")
public class RealTimeBedInfoController {
    private final RealTimeBedInfoService realTimeBedInfoService;

    @GetMapping("/beds")
    public Result beds(Long deptID, String buiding) {
        return realTimeBedInfoService.beds(deptID, buiding);
    }

    /**
     * @param
     * @return
     * @Description 床位信息
     * @date 2019/6/19
     * @auther zvbb
     */
    @GetMapping("/bedInfo")
    public Result bedInfo(Integer mainID, Long deptId) {
        return realTimeBedInfoService.bedInfo(mainID, deptId);
    }

    /**
     * @param
     * @return
     * @Description 实时监测
     * @date 2019/6/19
     * @auther zvbb
     */
    @GetMapping("/realMonitor")
    public Result realMonitor(Integer mainID, Long deptId) {
        return realTimeBedInfoService.realMonitor(mainID, deptId);
    }

    /**
     * @param
     * @return
     * @Description 睡眠状态占比
     * @date 2019/6/19
     * @auther zvbb
     */
    @GetMapping("/sleepStatusRatio")
    public Result sleepStatusRatio(Integer mainID, Long deptId) {
        return realTimeBedInfoService.sleepStatusRatio(mainID, deptId);
    }
}
