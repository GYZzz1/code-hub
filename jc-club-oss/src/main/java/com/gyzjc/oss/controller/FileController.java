package com.gyzjc.oss.controller;

import com.gyzjc.oss.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName : FileController
 * @Description : 文件controller
 * @Author : GYZzz
 * @Date: 2024-09-13 01:29
 */
@RestController
public class FileController {

    @Resource
    private FileService fileService;

    @Value("${storage.service.type}")
    private String storageType;

    @GetMapping("/test")
    public String test() {
        return storageType;
    }

    @GetMapping("/testBean")
    public String testBean() {
        return fileService.getAllBuckets().get(0);
    }

}
