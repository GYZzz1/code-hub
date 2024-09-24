package com.gyzjc.oss.adapter;

import com.gyzjc.oss.entity.FileInfo;
import com.gyzjc.oss.util.MinioUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName : MinioStorageServiceImpl
 * @Description : minio存储服务适配器
 * @Author : GYZzz
 * @Date: 2024-09-13 01:42
 */
public class MinioStorageAdapter implements StorageAdapter {

    @Resource
    private MinioUtil minioUtil;

    @Value("${minio.url}")
    private String url;

    @Override
    @SneakyThrows
    public void createBucket(String bucket) {
        minioUtil.createBucket(bucket);
    }

    @Override
    @SneakyThrows
    public void uploadFile(MultipartFile uploadFile, String bucket, String objectName) {
        minioUtil.createBucket(bucket);
        if (objectName != null) {
            minioUtil.uploadFile(uploadFile.getInputStream(), bucket, (objectName + "/" + uploadFile.getName()));
        } else {
            minioUtil.uploadFile(uploadFile.getInputStream(), bucket, uploadFile.getName());
        }
    }

    @Override
    @SneakyThrows
    public List<String> getAllBuckets() {
        return minioUtil.getAllBuckets();
    }

    @Override
    @SneakyThrows
    public List<FileInfo> getAllFiles(String bucket) {
        return minioUtil.getAllFiles(bucket);
    }

    @Override
    @SneakyThrows
    public InputStream downLoad(String bucket, String objectName) {
        return minioUtil.downLoad(bucket, objectName);
    }

    @Override
    @SneakyThrows
    public void deleteBucket(String bucket) {
        minioUtil.deleteBucket(bucket);
    }

    @Override
    @SneakyThrows
    public void deleteObject(String bucket, String objectName) {
        minioUtil.deleteObject(bucket, objectName);
    }

    @Override
    @SneakyThrows
    public String getUrl(String bucket, String objectName) {
        return url + "/" + bucket + "/" + objectName;
    }

}
