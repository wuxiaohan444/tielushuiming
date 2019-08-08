package com.jiangkai.framework.admin.util;

import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * User: zvbb
 * Date: 2019/6/11
 * Description:
 */
public class FileUtils {
    /**
     * 上传文件,并获取文件相对网络位置
     *
     * @param file MultipartFile {@link org.springframework.web.multipart.MultipartFile}
     * @return String
     */
    public static String writeFile(MultipartFile file, MultipartProperties properties,String prefix) {
        String fileName = getFileName(file.getOriginalFilename());//生成新的文件名称 加入上传的是1.txt  变成 adsadsadadweraeaf5656.txt
        String filePath = properties.getLocation() + fileName;//新文件的路径
        File writeFile = new File(filePath); //新文件
        //makeDir(filePath);
        try {
            file.transferTo(writeFile);//将上传的文件转换成新文件
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prefix + fileName;
    }

    /**
     * 生成新的文件名
     */
    public static String getFileName(String fileOriginName) {
        return getUUID() + getSuffix(fileOriginName);
    }

    /**
     * UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取文件后缀
     */
    private static String getSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
