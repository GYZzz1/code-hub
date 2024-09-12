package com.gyzjc.oss.config;

import com.gyzjc.oss.adapter.StorageAdapter;
import com.gyzjc.oss.adapter.AliStorageAdapter;
import com.gyzjc.oss.adapter.MinioStorageAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName : StorageConfig
 * @Description
 * @Author : GYZzz
 * @Date: 2024-09-13 02:10
 */
@Configuration
public class StorageConfig {

    @Value("${storage.service.type}")
    private String storageType;

    @Bean
    public StorageAdapter storageAdapter() {
        if ("minio".equals(storageType)) {
            return new MinioStorageAdapter();
        } else if ("aliyun".equals(storageType)) {
            return new AliStorageAdapter();
        } else {
            throw new IllegalArgumentException("未找到对应的文件存储处理器");
        }
    }

}
