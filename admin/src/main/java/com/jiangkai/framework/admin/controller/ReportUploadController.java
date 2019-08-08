package com.jiangkai.framework.admin.controller;

import com.jiangkai.framework.admin.bean.PersonSingleReportBean;
import com.jiangkai.framework.admin.common.Result;
import com.jiangkai.framework.admin.service.ReportUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author Administrator
 * @date 2019/5/6 10:01
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/reportUpload")
public class ReportUploadController {

    private final ReportUploadService reportUploadService;

    @PostMapping(value = "/personSingle")
    public Result personSingle(@RequestBody PersonSingleReportBean bean) {
        return reportUploadService.personSingle(bean);
    }
}
