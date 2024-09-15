package com.gyzjc.club.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *    自定义权限验证接口扩展 
 */
@Component    // 保证此类被springboot扫描，完成sa-token的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginKey) {
        List<String> permissionList = new ArrayList<>();
        permissionList.add("subject:add");
        return permissionList;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginKey) {
        List<String> roleList = new ArrayList<>();
        roleList.add("admin");
        return roleList;
    }

}