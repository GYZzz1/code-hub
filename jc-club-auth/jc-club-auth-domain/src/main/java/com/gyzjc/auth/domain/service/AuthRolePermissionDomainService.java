package com.gyzjc.auth.domain.service;

import com.gyzjc.auth.domain.entity.AuthPermissionBO;
import com.gyzjc.auth.domain.entity.AuthRolePermissionBO;

/**
 * @ClassName : AuthRolePermissionDomainService
 * @Description : 角色权限关联领域service
 * @Author : GYZzz
 * @Date: 2024-09-17 00:02
 */
public interface AuthRolePermissionDomainService {

    /**
     * 添加权限
     * @param authRolePermissionBO
     * @return
     */
    Boolean add(AuthRolePermissionBO authRolePermissionBO);
}
