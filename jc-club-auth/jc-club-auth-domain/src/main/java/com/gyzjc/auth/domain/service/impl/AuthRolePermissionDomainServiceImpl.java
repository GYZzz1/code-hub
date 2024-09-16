package com.gyzjc.auth.domain.service.impl;

import com.gyzjc.auth.common.enums.IsDeletedFlagEnum;
import com.gyzjc.auth.domain.convert.AuthPermissionBOConvertor;
import com.gyzjc.auth.domain.convert.AuthRolePermissionBOConvertor;
import com.gyzjc.auth.domain.entity.AuthPermissionBO;
import com.gyzjc.auth.domain.entity.AuthRolePermissionBO;
import com.gyzjc.auth.domain.service.AuthRolePermissionDomainService;
import com.gyzjc.auth.infra.basic.entity.AuthPermission;
import com.gyzjc.auth.infra.basic.entity.AuthRolePermission;
import com.gyzjc.auth.infra.basic.service.AuthPermissionService;
import com.gyzjc.auth.infra.basic.service.AuthRolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName : AuthRolePermissionDomainServiceImpl
 * @Description : 角色权限关联领域service实现类
 * @Author : GYZzz
 * @Date: 2024-09-17 00:02
 */
@Service
public class AuthRolePermissionDomainServiceImpl implements AuthRolePermissionDomainService {
    @Resource
    private AuthRolePermissionService authRolePermissionService;

    @Override
    public Boolean add(AuthRolePermissionBO authRolePermissionBO) {
        Long roleId = authRolePermissionBO.getRoleId();
        authRolePermissionBO.getPermissionIdList().forEach(permissionId -> {
            AuthRolePermission authRolePermission = new AuthRolePermission();
            authRolePermission.setRoleId(roleId);
            authRolePermission.setPermissionId(permissionId);
            authRolePermission.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
            authRolePermissionService.insert(authRolePermission);
        });

        return true;
    }
}
