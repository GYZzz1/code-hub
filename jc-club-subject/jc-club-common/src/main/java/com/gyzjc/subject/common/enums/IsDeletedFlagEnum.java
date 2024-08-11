package com.gyzjc.subject.common.enums;

import lombok.Getter;

@Getter
public enum IsDeletedFlagEnum {
    DELETED(1, "已删除"),
    UN_DELETED(0, "未删除");

    private int code;
    private String desc;

    IsDeletedFlagEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static IsDeletedFlagEnum getByCode(int code) {
        for (IsDeletedFlagEnum resultCodeEnum : IsDeletedFlagEnum.values()) {
            if (resultCodeEnum.code == code) {
                return resultCodeEnum;
            }
        }
        return null;
    }

}
