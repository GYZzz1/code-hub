package com.gyzjc.auth.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色权限关系DTO
 *
 * @author makejava
 * @since 2024-09-17 00:32:35
 */
@Data
public class AuthRolePermissionDTO implements Serializable {
    private static final long serialVersionUID = -27965142820327438L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 权限id
     */
    private Long permissionId;
    /**
     * 权限id列表
     */
    private List<Long> permissionIdList;

}
