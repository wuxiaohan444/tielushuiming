package com.jiangkai.framework.admin.controller;

import com.jiangkai.framework.admin.bean.BasedataBean;
import com.jiangkai.framework.admin.common.Result;
import com.jiangkai.framework.admin.service.BaseDataService;
import com.jiangkai.framework.source.model.BaseData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * User: zvbb
 * Date: 2019/6/11
 * Description:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/baseData")
public class BaseDataController {
    private final BaseDataService basedataService;
    private final HttpServletRequest request;

    @GetMapping("/page")
    public Result page(BasedataBean basedataBean) {
        return basedataService.page(basedataBean);
    }

    @PutMapping(value = "/insert")
    public Result insert(BaseData baseData) {
        return basedataService.insert(baseData, request);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        return basedataService.delete(id, request);
    }

    @PostMapping("/update")
    public Result update(BaseData baseData) {
        return basedataService.update(baseData, request);
    }

    @GetMapping("/{id}")
    public Result select(@PathVariable Long id) {
        return basedataService.select(id);
    }
}
