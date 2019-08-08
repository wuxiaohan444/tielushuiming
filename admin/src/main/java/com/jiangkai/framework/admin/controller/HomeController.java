package com.jiangkai.framework.admin.controller;

import com.jiangkai.framework.admin.common.Result;
import com.jiangkai.framework.admin.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: zvbb
 * Date: 2019/6/16
 * Description:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {
    private final HomeService homeService;

    @GetMapping("/totalAnalysis")
    public Result totalAnalysis(Long id){
        return  homeService.totalAnalysis(id);
    }

    @GetMapping("/sleepQualityAnalysis")
    public Result sleepQualityAnalysis(Long id){
        return homeService.sleepQualityAnalysis(id);
    }
}
