package com.gyzjc.oss.adapter;

import com.gyzjc.oss.entity.FileInfo;
import com.gyzjc.oss.adapter.StorageAdapter;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : AliStorageServiceImpl
 * @Description : aliOss存储服务适配器
 * @Author : GYZzz
 * @Date: 2024-09-13 01:42
 */
public class AliStorageAdapter implements StorageAdapter {
    @Override
    public void createBucket(String bucket) {

    }

    @Override
    public void uploadFile(MultipartFile uploadFile, String bucket, String objectName) {

    }

    @Override
    public List<String> getAllBuckets() {
        List<String> bucketNameList = new ArrayList<>();
        bucketNameList.add("aliyunxx");
        return bucketNameList;
    }

    @Override
    public List<FileInfo> getAllFiles(String bucket) {
        return null;
    }

    @Override
    public InputStream downLoad(String bucket, String objectName) {
        return null;
    }

    @Override
    public void deleteBucket(String bucket) {

    }

    @Override
    public void deleteObject(String bucket, String objectName) {

    }
}
