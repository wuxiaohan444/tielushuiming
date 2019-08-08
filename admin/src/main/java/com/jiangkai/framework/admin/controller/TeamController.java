package com.jiangkai.framework.admin.controller;

import com.jiangkai.framework.admin.bean.TeamBean;
import com.jiangkai.framework.admin.common.Result;
import com.jiangkai.framework.admin.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: zvbb
 * Date: 2019/6/13
 * Description:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamController {
    private final TeamService teamService;

    @GetMapping("/indexInfo")
    public Result indexInfo(Long id) {

        return null;
    }

    @GetMapping("/baseInfo")
    public Result baseInfo(TeamBean teamBean) {
        return teamService.baseInfo(teamBean);
    }

    @GetMapping("/pieInfo")
    public Result pieInfo(TeamBean teamBean) {
        return teamService.pieInfo(teamBean);
    }

    /**
     * @param
     * @return
     * @Description 同比趋势图
     * @date 2019/6/14
     * @auther zvbb
     */
    @GetMapping("/tbTrend")
    public Result tbTrend(TeamBean teamBean) {
        return teamService.tbTrend(teamBean);
    }

    @GetMapping("/hbTrend")
    public Result hbTrend(TeamBean teamBean) {
        return teamService.hbTrend(teamBean);
    }
}
