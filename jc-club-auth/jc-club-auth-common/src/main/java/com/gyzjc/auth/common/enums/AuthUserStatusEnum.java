package com.gyzjc.auth.common.enums;

import lombok.Getter;

/**
 * @ClassName : AuthUserStatusEnum
 * @Description : 用户状态枚举
 * @Author : GYZzz
 * @Date: 2024-09-16 02:57
 */
@Getter
public enum AuthUserStatusEnum {
    OPEN(0, "启用"),
    CLOSE(1, "禁用");

    private int code;
    private String desc;

    AuthUserStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AuthUserStatusEnum getByCode(int code) {
        for (AuthUserStatusEnum resultCodeEnum : AuthUserStatusEnum.values()) {
            if (resultCodeEnum.code == code) {
                return resultCodeEnum;
            }
        }
        return null;
    }
}
