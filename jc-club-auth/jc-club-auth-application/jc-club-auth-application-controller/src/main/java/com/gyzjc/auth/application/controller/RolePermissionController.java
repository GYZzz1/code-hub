package com.gyzjc.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.gyzjc.auth.application.convert.AuthRolePermissionDTOConverter;
import com.gyzjc.auth.application.dto.AuthRolePermissionDTO;
import com.gyzjc.auth.common.entity.Result;
import com.gyzjc.auth.domain.entity.AuthRolePermissionBO;
import com.gyzjc.auth.domain.service.AuthPermissionDomainService;
import com.gyzjc.auth.domain.service.AuthRolePermissionDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName : RolePermissionController
 * @Description : 角色权限controller
 * @Author : GYZzz
 * @Date: 2024-09-16 23:55
 */
@RestController
@RequestMapping("/rolePermission")
@Slf4j
public class RolePermissionController {

    @Resource
    private AuthRolePermissionDomainService authRolePermissionDomainService;

    /**
     * 新增角色权限关联关系
     * @param authRolePermissionDTO
     * @return
     */
    @RequestMapping ("/add")
    public Result<Boolean> add(@RequestBody AuthRolePermissionDTO authRolePermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RolePermissionController.add.dto:{}", JSON.toJSONString(authRolePermissionDTO));
            }

            Preconditions.checkArgument(!CollectionUtils.isEmpty(authRolePermissionDTO.getPermissionIdList()), "权限关联不能为空");
            Preconditions.checkNotNull(authRolePermissionDTO.getRoleId(), "角色id不能为空");

            AuthRolePermissionBO authRolePermissionBO = AuthRolePermissionDTOConverter.INSTANCE.convertDtoToBO(authRolePermissionDTO);
            return Result.ok(authRolePermissionDomainService.add(authRolePermissionBO));
        } catch (Exception e) {
            log.error("RolePermissionController.add.error:{}", e.getMessage(), e);
            return Result.fail("新增角色权限关联关系失败");
        }
    }
}
