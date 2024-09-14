package com.gyzjc.oss.service.impl;

import com.gyzjc.oss.adapter.StorageAdapter;
import com.gyzjc.oss.entity.FileInfo;
import com.gyzjc.oss.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @ClassName : FileServiceImpl
 * @Description : 文件服务实现
 * @Author : GYZzz
 * @Date: 2024-09-13 02:40
 */
@Service
public class FileServiceImpl implements FileService {

    private final StorageAdapter storageAdapter;

    public FileServiceImpl(StorageAdapter storageAdapter) {
        this.storageAdapter = storageAdapter;
    }

    @Override
    public void createBucket(String bucket) {
        storageAdapter.createBucket(bucket);
    }

    @Override
    public void uploadFile(MultipartFile uploadFile, String bucket, String objectName) {
        storageAdapter.uploadFile(uploadFile, bucket, objectName);
    }

    @Override
    public List<String> getAllBuckets() {
        return storageAdapter.getAllBuckets();
    }

    @Override
    public List<FileInfo> getAllFiles(String bucket) {
        return storageAdapter.getAllFiles(bucket);
    }

    @Override
    public InputStream downLoad(String bucket, String objectName) {
        return storageAdapter.downLoad(bucket, objectName);
    }

    @Override
    public void deleteBucket(String bucket) {
        storageAdapter.deleteBucket(bucket);
    }

    @Override
    public void deleteObject(String bucket, String objectName) {
        storageAdapter.deleteObject(bucket, objectName);
    }
}
