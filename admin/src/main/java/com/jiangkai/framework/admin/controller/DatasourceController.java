package com.jiangkai.framework.admin.controller;

import com.jiangkai.framework.admin.bean.DataSourceBean;
import com.jiangkai.framework.admin.common.Result;
import com.jiangkai.framework.admin.service.DatasourceService;
import com.jiangkai.framework.source.model.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * User: zvbb
 * Date: 2019/6/11
 * Description:
 */
@RestController
@RequestMapping("/datasource")
@RequiredArgsConstructor
public class DatasourceController {
    private final DatasourceService datasourceService;
    private final HttpServletRequest request;

    @GetMapping("/page")
    public Result page(DataSourceBean dataSourceBean) {
        return datasourceService.page(dataSourceBean);
    }

    @PutMapping("/insert")
    public Result insert(DataSource dataSource) {
        return datasourceService.insert(dataSource, request);
    }

    @GetMapping("/{id}")
    public Result select(@PathVariable Long id) {
        return datasourceService.select(id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        return datasourceService.delete(id, request);
    }

    @PostMapping("/update")
    public Result update(DataSource dataSource) {
        return datasourceService.update(dataSource, request);
    }

    @PostMapping("/onlink")
    public Result onLink(Long id) throws Exception {
        return datasourceService.onLink(id, request);
    }

    @PostMapping("/offlink")
    public Result offLink(Long id) {
        return datasourceService.offLink(id, request);
    }
}
