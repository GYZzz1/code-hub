package com.gyzjc.auth.application.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 权限表DTO
 *
 * @author makejava
 * @since 2024-09-16 23:50:26
 */
@Data
public class AuthPermissionDTO implements Serializable {
    private static final long serialVersionUID = -46483949171356494L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 父id
     */
    private Long parentId;
    /**
     * 权限类型 0菜单 1操作
     */
    private Integer type;
    /**
     * 菜单路由
     */
    private String menuUrl;
    /**
     * 状态 0启用 1禁用
     */
    private Integer status;
    /**
     * 展示状态 0 展示 1隐藏
     */
    private Integer show;
    /**
     * 图标
     */
    private String icon;
    /**
     * 权限唯一标识
     */
    private String permissionKey;

}

