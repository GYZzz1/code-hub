package com.gyzjc.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.gyzjc.auth.common.enums.AuthUserStatusEnum;
import com.gyzjc.auth.common.enums.IsDeletedFlagEnum;
import com.gyzjc.auth.domain.constants.AuthConstant;
import com.gyzjc.auth.domain.convert.AuthUserBOConvertor;
import com.gyzjc.auth.domain.entity.AuthUserBO;
import com.gyzjc.auth.domain.service.AuthUserDomainService;
import com.gyzjc.auth.infra.basic.entity.AuthRole;
import com.gyzjc.auth.infra.basic.entity.AuthUser;
import com.gyzjc.auth.infra.basic.entity.AuthUserRole;
import com.gyzjc.auth.infra.basic.service.AuthRoleService;
import com.gyzjc.auth.infra.basic.service.AuthUserRoleService;
import com.gyzjc.auth.infra.basic.service.AuthUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName : AuthUserDomainServiceImpl
 * @Description : 用户领域service实现类
 * @Author : GYZzz
 * @Date: 2024-09-16 02:44
 */
@Service
public class AuthUserDomainServiceImpl implements AuthUserDomainService {
    @Resource
    private AuthUserService authUserService;
    @Resource
    private AuthUserRoleService authUserRoleService;
    @Resource
    private AuthRoleService authRoleService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean register(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConvertor.INSTANCE.convertBOToEntity(authUserBO);
        authUser.setPassword(SaSecureUtil.md5BySalt(authUser.getPassword(), "codehub"));
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        Integer count = authUserService.insert(authUser);

        // 建立一个初步的角色关联
        AuthRole authRole = new AuthRole();
        authRole.setRoleKey(AuthConstant.NORMAL_USER);
        AuthRole roleResult = authRoleService.queryByCondition(authRole);
        Long roleId = roleResult.getId();
        Long userId = authUser.getId();

        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setRoleId(roleId);
        authUserRole.setUserId(userId);
        authUserRole.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());

        authUserRoleService.insert(authUserRole);

        // TODO 把当前用户角色和权限刷到redis里

        return count > 0;
    }

    @Override
    public Boolean update(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConvertor.INSTANCE.convertBOToEntity(authUserBO);
         Integer count = authUserService.update(authUser);
        // TODO 有任何更新，与缓存同步
        return count > 0;
    }

    @Override
    public Boolean delete(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConvertor.INSTANCE.convertBOToEntity(authUserBO);
        authUser.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        Integer count = authUserService.update(authUser);
        // TODO 有任何更新，与缓存同步
        return count > 0;
    }
}
