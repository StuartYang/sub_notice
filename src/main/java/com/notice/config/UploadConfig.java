package com.notice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;


/*
文件扫描
 */
@Component
@ConfigurationProperties(prefix = "noticeManager.upload")
public class UploadConfig {

    //上传路径
    private String uploadPath = "upload"+ File.separator;

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

}
