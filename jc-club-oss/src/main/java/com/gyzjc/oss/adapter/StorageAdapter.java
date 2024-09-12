package com.gyzjc.oss.adapter;

import com.gyzjc.oss.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * 文件存储适配器
 */
public interface StorageAdapter {

    /**
     * 创建bucket桶
     *
     * @param bucket
     * @throws Exception
     */
    void createBucket(String bucket);

    /**
     * 上传文件
     *
     * @param uploadFile
     * @param bucket
     * @param objectName
     * @throws Exception
     */
    void uploadFile(MultipartFile uploadFile, String bucket, String objectName);

    /**
     * 列出所有bucket桶
     *
     * @return
     * @throws Exception
     */
    List<String> getAllBuckets();

    /**
     * 列出当前bucket桶及文件
     *
     * @param bucket
     * @return
     * @throws Exception
     */
    List<FileInfo> getAllFiles(String bucket);

    /**
     * 下载文件
     *
     * @param bucket
     * @param objectName
     * @return
     */
    InputStream downLoad(String bucket, String objectName);

    /**
     * 删除桶
     *
     * @param bucket
     * @return
     */
    void deleteBucket(String bucket);

    /**
     * 删除文件
     *
     * @param bucket
     * @param objectName
     * @throws Exception
     */
    void deleteObject(String bucket, String objectName);

}
