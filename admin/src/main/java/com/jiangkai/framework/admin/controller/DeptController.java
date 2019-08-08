package com.jiangkai.framework.admin.controller;

import com.jiangkai.framework.admin.bean.DeptBean;
import com.jiangkai.framework.admin.bean.LoginUser;
import com.jiangkai.framework.admin.common.Result;
import com.jiangkai.framework.admin.service.DeptService;
import com.jiangkai.framework.source.model.Dept;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User: zvbb
 * Date: 2019/6/10
 * Description:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/dept")
public class DeptController {
    private final DeptService deptService;
    private final HttpServletRequest request;
    private final SessionRegistry sessionRegistry;

    @GetMapping("/page")
    public Result page(DeptBean deptBean) {
        Authentication authentication = (Authentication) request.getUserPrincipal();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        this.invalidateSession(loginUser);
        return deptService.page(deptBean);
    }

    @PutMapping("/insert")
    public Result insert(Dept dept) {
        return deptService.insert(dept, request);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        return deptService.delete(id, request);
    }

    @PostMapping("/update")
    public Result update(Dept dept, HttpServletRequest request) {
        return deptService.update(dept, request);
    }

    @GetMapping("/{id}")
    public Result select(@PathVariable Long id) {
        return deptService.select(id);
    }

    /**
     * 节点Id
     */
    @GetMapping("/selectLeafs")
    public Result selectLeafs(Long id) {
        return deptService.selectLeafs(id);
    }

    @GetMapping("/dataControl")
    public Result dataControl() {
        return deptService.deptControl(request);
    }

    /**
     * @Description 让相同用户的session失效
     */
    private void invalidateSession(LoginUser loginUser) {
        List<Object> list = sessionRegistry.getAllPrincipals();
        for (Object principal : list) {
            if (principal instanceof LoginUser) {
                final LoginUser expireUser = (LoginUser) principal;
                if (loginUser.getUsername().equals(expireUser.getUsername())) {
                    List<SessionInformation> sessionsInfoList = sessionRegistry.getAllSessions(principal, false);
                    if (null != sessionsInfoList && sessionsInfoList.size() > 1) {
                        List<SessionInformation> sortSessionInfoList = this.sortSessionInfoList(sessionsInfoList);
                        for (int i = 1; i < sortSessionInfoList.size(); i++) {
                            SessionInformation sessionInformation = sortSessionInfoList.get(i);
                            sessionInformation.expireNow();
                            String sessionId = sessionInformation.getSessionId();
                        }
                    }
                }
            }
        }
    }

    /**
     * @Description 排序
     */
    private List<SessionInformation> sortSessionInfoList(List<SessionInformation> sessionsInfo) {
        List<SessionInformation> sortList = sessionsInfo
                .stream()
                .sorted((sessionsInfo1, sessionsInfo2) -> {
                    if (sessionsInfo1.getLastRequest().getTime() < sessionsInfo2.getLastRequest().getTime()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }).collect(Collectors.toList());
        return sortList;
    }
}
