package com.gyzjc.auth.domain.service.impl;

import com.gyzjc.auth.common.enums.IsDeletedFlagEnum;
import com.gyzjc.auth.domain.convert.AuthRoleBOConvertor;
import com.gyzjc.auth.domain.entity.AuthRoleBO;
import com.gyzjc.auth.domain.service.AuthRoleDomainService;
import com.gyzjc.auth.infra.basic.entity.AuthRole;
import com.gyzjc.auth.infra.basic.service.AuthRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName : AuthRoleDomainServiceImpl
 * @Description : 角色领域service实现类
 * @Author : GYZzz
 * @Date: 2024-09-16 22:46
 */
@Service
public class AuthRoleDomainServiceImpl implements AuthRoleDomainService {

    @Resource
    private AuthRoleService authRoleService;

    @Override
    public Boolean add(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthRoleBOConvertor.INSTANCE.convertBOToEntity(authRoleBO);
        authRole.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        int count = authRoleService.insert(authRole);
        return count > 0;
    }

    @Override
    public Boolean update(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthRoleBOConvertor.INSTANCE.convertBOToEntity(authRoleBO);
        int count = authRoleService.update(authRole);
        return count > 0;
    }

    @Override
    public Boolean delete(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthRoleBOConvertor.INSTANCE.convertBOToEntity(authRoleBO);
        authRole.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        int count = authRoleService.update(authRole);
        return count > 0;
    }
}
