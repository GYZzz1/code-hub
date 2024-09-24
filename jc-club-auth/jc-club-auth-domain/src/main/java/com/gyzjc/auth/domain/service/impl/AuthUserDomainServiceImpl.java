package com.gyzjc.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.google.gson.Gson;
import com.gyzjc.auth.common.enums.AuthUserStatusEnum;
import com.gyzjc.auth.common.enums.IsDeletedFlagEnum;
import com.gyzjc.auth.domain.constants.AuthConstant;
import com.gyzjc.auth.domain.convert.AuthUserBOConvertor;
import com.gyzjc.auth.domain.entity.AuthUserBO;
import com.gyzjc.auth.domain.redis.RedisUtil;
import com.gyzjc.auth.domain.service.AuthUserDomainService;
import com.gyzjc.auth.infra.basic.entity.*;
import com.gyzjc.auth.infra.basic.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Resource
    private AuthPermissionService authPermissionService;
    @Resource
    private AuthRolePermissionService authRolePermissionService;
    @Resource
    private RedisUtil redisUtil;

    private static final String LOGIN_PREFIX = "loginCode";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean register(AuthUserBO authUserBO) {
        // 校验用户是否存在
        AuthUser exitsAuthUser = new AuthUser();
        exitsAuthUser.setUserName(authUserBO.getUserName());
        List<AuthUser> existUser = authUserService.queryByCondition(exitsAuthUser);
        if (!existUser.isEmpty()) {
            return true;
        }

        AuthUser authUser = AuthUserBOConvertor.INSTANCE.convertBOToEntity(authUserBO);
        if (StringUtils.isNotBlank(authUser.getPassword())) {
            authUser.setPassword(SaSecureUtil.md5BySalt(authUser.getPassword(), "codehub"));
        }
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        Integer count = authUserService.insert(authUser);

        // 建立一个初步的用户角色关联
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

        // 把当前用户角色和权限刷到redis里
        String roleKey = redisUtil.buildKey(AuthConstant.AUTH_ROLE_PREFIX, authUser.getUserName());
        List<AuthRole> roleList = new ArrayList<>();
        roleList.add(authRole);
        redisUtil.set(roleKey, new Gson().toJson(roleList));

        AuthRolePermission authRolePermission = new AuthRolePermission();
        authRolePermission.setRoleId(roleId);
        List<AuthRolePermission> rolePermissionList = authRolePermissionService.queryByCondition(authRolePermission);

        List<Long> permissionIdList = rolePermissionList.stream().map(AuthRolePermission::getPermissionId).collect(Collectors.toList());
        // 根据roleID查权限
        List<AuthPermission> permissionList = authPermissionService.queryByRoleList(permissionIdList);
        String permissionKey = redisUtil.buildKey(AuthConstant.AUTH_PERMISSION_PREFIX, authUser.getUserName());
        redisUtil.set(permissionKey, new Gson().toJson(permissionList));

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

    @Override
    @Transactional
    public SaTokenInfo doLogin(String validateCode) {
        String loginKey = redisUtil.buildKey(LOGIN_PREFIX, validateCode);
        String openId = redisUtil.get(loginKey);

        if (StringUtils.isBlank(openId)) {
            return null;
        }

        AuthUserBO authUserBO = new AuthUserBO();
        authUserBO.setUserName(openId);
        this.register(authUserBO);

        StpUtil.login(openId);
        return StpUtil.getTokenInfo();
    }

    @Override
    public AuthUserBO getUserInfo(AuthUserBO authUserBO) {
        AuthUser authUser = new AuthUser();
        authUser.setUserName(authUserBO.getUserName());
        List<AuthUser> userList = authUserService.queryByCondition(authUser);
        if (CollectionUtils.isEmpty(userList)) {
            return new AuthUserBO();
        }
        AuthUser user = userList.get(0);
        return AuthUserBOConvertor.INSTANCE.convertEntityToBO(user);
    }
}
