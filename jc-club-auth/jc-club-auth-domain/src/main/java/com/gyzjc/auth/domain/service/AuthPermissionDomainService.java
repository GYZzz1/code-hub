package com.gyzjc.auth.domain.service;

import com.gyzjc.auth.domain.entity.AuthPermissionBO;
import com.gyzjc.auth.domain.entity.AuthRoleBO;

/**
 * @ClassName : AuthPermissionDomainService
 * @Description : 权限领域service
 * @Author : GYZzz
 * @Date: 2024-09-17 00:02
 */
public interface AuthPermissionDomainService {

    /**
     * 添加权限
     * @param authPermissionBO
     * @return
     */
    Boolean add(AuthPermissionBO authPermissionBO);

    /**
     * 更新权限
     * @param authPermissionBO
     * @return
     */
    Boolean update(AuthPermissionBO authPermissionBO);

    /**
     * 删除权限
     * @param authPermissionBO
     * @return
     */
    Boolean delete(AuthPermissionBO authPermissionBO);
}
