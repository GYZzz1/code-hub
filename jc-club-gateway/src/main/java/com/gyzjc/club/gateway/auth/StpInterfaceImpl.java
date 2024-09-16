package com.gyzjc.club.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.gyzjc.club.gateway.entity.AuthPermission;
import com.gyzjc.club.gateway.entity.AuthRole;
import com.gyzjc.club.gateway.redis.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义权限验证接口扩展
 */
@Component    // 保证此类被springboot扫描，完成sa-token的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private RedisUtil redisUtil;
    private final String AUTH_PERMISSION_PREFIX = "auth.permission";
    private final String AUTH_ROLE_PREFIX = "auth.role";

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginKey) {
        // redis缓存
        return getAuth(loginId, AUTH_PERMISSION_PREFIX);
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginKey) {
        // redis缓存
        return getAuth(loginId, AUTH_ROLE_PREFIX);
    }

    private List<String> getAuth(Object loginId, String prefix) {
        String authKey = redisUtil.buildKey(prefix, loginId.toString());
        String authValue = redisUtil.get(authKey);
        if (StringUtils.isBlank(authValue)) {
            return Collections.emptyList();
        }

        List<String> authList = new ArrayList<>();
        if (AUTH_ROLE_PREFIX.equals(prefix)) {
            List<AuthRole> roleList = new Gson().fromJson(authValue, new TypeToken<List<AuthRole>>() {
            }.getType());
            authList = roleList.stream().map(AuthRole::getRoleKey).collect(Collectors.toList());
        } else if (AUTH_PERMISSION_PREFIX.equals(prefix)) {
            List<AuthPermission> permissionList = new Gson().fromJson(authValue, new TypeToken<List<AuthPermission>>() {
            }.getType());
            authList = permissionList.stream().map(AuthPermission::getPermissionKey).collect(Collectors.toList());
        }

        return authList;
    }

}