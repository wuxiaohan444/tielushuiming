package com.jiangkai.framework.admin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @date 2019/4/29 15:20
 */
@Data
@Component
@ConfigurationProperties(prefix = "jiangkai.sleep-monitoring.upload-file")
public class UploadFileProperties {
    //上传前缀
    private String prefix;
}
