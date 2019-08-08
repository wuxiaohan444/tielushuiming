package com.jiangkai.framework.admin.service;

import com.jiangkai.framework.admin.bean.PersonSingleReportBean;
import com.jiangkai.framework.admin.common.Result;
import com.jiangkai.framework.source.mapper.BedRecordDynamicSqlSupport;
import com.jiangkai.framework.source.mapper.BedRecordMapper;
import com.jiangkai.framework.source.model.BedRecord;
import lombok.RequiredArgsConstructor;
import org.mybatis.dynamic.sql.where.condition.IsEqualTo;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.List;

/**
 * @author Administrator
 * @date 2019/5/6 10:02
 */
@Component
@RequiredArgsConstructor
public class ReportUploadService {

    private final MultipartProperties multipartProperties;
    private final BedRecordMapper bedRecordMapper;
    public Result personSingle(PersonSingleReportBean bean) {
        if (!StringUtils.hasText(bean.getGuid()))
            return Result.paramError("GUID唯一值不得为空");
        if (!StringUtils.hasText(bean.getPdf()))
            return Result.paramError("pdf值不得为空");
        if (!StringUtils.hasText(bean.getPng()))
            return Result.paramError("png值不得为空");
        String fileName = bean.getGuid();
        try {
            Files.write(Paths.get(multipartProperties.getLocation() + "/png/" + fileName + ".png"), Base64.getDecoder().decode(bean.getPng().getBytes(StandardCharsets.UTF_8)), StandardOpenOption.CREATE);
            Files.write(Paths.get(multipartProperties.getLocation() + "/pdf/" + fileName + ".pdf"), Base64.getDecoder().decode(bean.getPdf().getBytes(StandardCharsets.UTF_8)), StandardOpenOption.CREATE);
            //修改上传标志
            BedRecordDynamicSqlSupport.BedRecord bedRecordSupport = BedRecordDynamicSqlSupport.bedRecord;
            List<BedRecord> bedRecordList = bedRecordMapper.selectByExample().where(bedRecordSupport.reportguid, IsEqualTo.of(() -> bean.getGuid())).build().execute();
            if(!CollectionUtils.isEmpty(bedRecordList)){
                BedRecord bedRecord = bedRecordList.get(0);
                bedRecord.setIsupload(1);
                bedRecordMapper.updateByPrimaryKey(bedRecord);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error();
        }
        return Result.success();
    }
}
