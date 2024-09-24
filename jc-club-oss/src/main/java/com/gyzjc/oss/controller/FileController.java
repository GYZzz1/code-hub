package com.gyzjc.oss.controller;

import com.gyzjc.oss.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @ClassName : FileController
 * @Description : 文件controller
 * @Author : GYZzz
 * @Date: 2024-09-13 01:29
 */
@RestController
@RefreshScope
public class FileController {

    @Resource
    private FileService fileService;

    @RequestMapping("/getUrl")
    public String getUrl(String bucketName, String objectName) {
        return fileService.getUrl(bucketName, objectName);
    }

    @GetMapping("/testBean")
    public String testBean() {
        return fileService.getAllBuckets().get(0);
    }

    @RequestMapping("/upload")
    public String upload(MultipartFile uploadFile, String bucket, String objectName) {
        return fileService.uploadFile(uploadFile, bucket, objectName);
    }
}
