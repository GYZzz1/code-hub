package com.gyzjc.oss.controller;

import com.gyzjc.oss.util.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
    private MinioUtil minioUtil;

    @GetMapping("/test")
    public String testGetAllBuckets() throws Exception {
        return minioUtil.getAllBuckets() == null ? "null" : minioUtil.getAllBuckets().get(0);
    }

}
