package com.jiangkai.framework.admin.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Administrator
 * @date 2019/5/6 9:28
 * @apiNote 此实体类是用作别人上传个人报告使用，故考虑使用自定义注解从request中获取对应的参数，将来上传参数更改只需对注解值进行修改即可。
 *
 * 通过HTTP协议POST请求上传数据，ContentType为application/json，
 * 每次上传1份个人报告，
 * 上传的数据是JSON格式对象，含有属性：ReportID、PersonSingleReportPDF、PersonSingleReportPNG，
 * 其中ReportID是GUID，PersonSingleReportPDF、PersonSingleReportPNG是文件字节数组通过Base64编码得到的字符串，
 * HTTP接口返回上传成功失败信息
 */
@Data
public class PersonSingleReportBean {

    @JsonProperty(value = "ReportID")
    private String guid;

    @JsonProperty(value = "PersonSingleReportPDF")
    private String pdf;

    @JsonProperty(value = "PersonSingleReportPNG")
    private String png;

}
