package com.gyzjc.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.gyzjc.auth.common.enums.AuthUserStatusEnum;
import com.gyzjc.auth.common.enums.IsDeletedFlagEnum;
import com.gyzjc.auth.domain.constants.AuthConstant;
import com.gyzjc.auth.domain.convert.AuthPermissionBOConvertor;
import com.gyzjc.auth.domain.convert.AuthUserBOConvertor;
import com.gyzjc.auth.domain.entity.AuthPermissionBO;
import com.gyzjc.auth.domain.entity.AuthUserBO;
import com.gyzjc.auth.domain.service.AuthPermissionDomainService;
import com.gyzjc.auth.domain.service.AuthUserDomainService;
import com.gyzjc.auth.infra.basic.entity.AuthPermission;
import com.gyzjc.auth.infra.basic.entity.AuthRole;
import com.gyzjc.auth.infra.basic.entity.AuthUser;
import com.gyzjc.auth.infra.basic.entity.AuthUserRole;
import com.gyzjc.auth.infra.basic.service.AuthPermissionService;
import com.gyzjc.auth.infra.basic.service.AuthRoleService;
import com.gyzjc.auth.infra.basic.service.AuthUserRoleService;
import com.gyzjc.auth.infra.basic.service.AuthUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName : AuthPermissionDomainServiceImpl
 * @Description : 权限领域service实现类
 * @Author : GYZzz
 * @Date: 2024-09-17 00:02
 */
@Service
public class AuthPermissionDomainServiceImpl implements AuthPermissionDomainService {
    @Resource
    private AuthPermissionService authPermissionService;

    @Override
    public Boolean add(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionBOConvertor.INSTANCE.convertBOToEntity(authPermissionBO);
        authPermission.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        int count = authPermissionService.insert(authPermission);
        return count > 0;
    }

    @Override
    public Boolean update(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionBOConvertor.INSTANCE.convertBOToEntity(authPermissionBO);
        int count = authPermissionService.update(authPermission);
        return count > 0;
    }

    @Override
    public Boolean delete(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionBOConvertor.INSTANCE.convertBOToEntity(authPermissionBO);
        authPermission.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        int count = authPermissionService.update(authPermission);
        return count > 0;
    }
}
