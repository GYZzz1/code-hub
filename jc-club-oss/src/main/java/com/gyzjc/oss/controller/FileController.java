package com.gyzjc.oss.controller;

import com.gyzjc.oss.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : FileController
 * @Description : 文件controller
 * @Author : GYZzz
 * @Date: 2024-09-13 01:29
 */
@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/test")
    public String testGetAllBuckets() {
        return CollectionUtils.isEmpty(fileService.getAllBuckets() ) ? "null" : fileService.getAllBuckets().get(0);
    }

}
