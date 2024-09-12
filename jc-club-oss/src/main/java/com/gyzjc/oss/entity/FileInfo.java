package com.gyzjc.oss.entity;

/**
 * @ClassName : FileInfo
 * @Description : 文件类
 * @Author : GYZzz
 * @Date: 2024-09-13 01:05
 */
public class FileInfo {

    private String fileName;
    private Boolean directoryFlag;
    private String etag;

    public Boolean getDirectoryFlag(boolean dir) {
        return directoryFlag;
    }

    public Boolean getDirectoryFlag() {
        return directoryFlag;
    }

    public void setDirectoryFlag(Boolean directoryFlag) {
        this.directoryFlag = directoryFlag;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
