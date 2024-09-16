package com.gyzjc.auth.domain.service;

import com.gyzjc.auth.domain.entity.AuthRoleBO;

/**
 * @ClassName : AuthRoleDomainService
 * @Description : 角色领域service
 * @Author : GYZzz
 * @Date: 2024-09-16 22:46
 */
public interface AuthRoleDomainService {

    /**
     * 添加角色
     * @param authUserBO
     * @return
     */
    Boolean add(AuthRoleBO authUserBO);

    /**
     * 更新角色
     * @param authRoleBO
     * @return
     */
    Boolean update(AuthRoleBO authRoleBO);

    /**
     * 删除角色
     * @param authRoleBO
     * @return
     */
    Boolean delete(AuthRoleBO authRoleBO);
}
