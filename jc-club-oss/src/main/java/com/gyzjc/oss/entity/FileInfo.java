package com.gyzjc.oss.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName : FileInfo
 * @Description : 文件类
 * @Author : GYZzz
 * @Date: 2024-09-13 01:05
 */
@Getter
@Setter
public class FileInfo {

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件夹标志位
     */
    private Boolean directoryFlag;

    /**
     * 标签
     */
    private String etag;

}
