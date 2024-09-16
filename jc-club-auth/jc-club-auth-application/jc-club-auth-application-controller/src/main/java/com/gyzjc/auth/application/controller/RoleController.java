package com.gyzjc.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.gyzjc.auth.application.convert.AuthRoleDTOConverter;
import com.gyzjc.auth.application.convert.AuthUserDTOConverter;
import com.gyzjc.auth.application.dto.AuthRoleDTO;
import com.gyzjc.auth.application.dto.AuthUserDTO;
import com.gyzjc.auth.common.entity.Result;
import com.gyzjc.auth.domain.entity.AuthRoleBO;
import com.gyzjc.auth.domain.entity.AuthUserBO;
import com.gyzjc.auth.domain.service.AuthRoleDomainService;
import com.gyzjc.auth.domain.service.AuthUserDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName : RoleController
 * @Description : 角色controller
 * @Author : GYZzz
 * @Date: 2024-09-16 22:36
 */
@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {

    @Resource
    private AuthRoleDomainService authRoleDomainService;

    /**
     * 新增角色
     * @param authRoleDTO
     * @return
     */
    @RequestMapping ("/add")
    public Result<Boolean> add(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RoleController.add.dto:{}", JSON.toJSONString(authRoleDTO));
            }

            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleKey()), "角色key不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleName()), "角色名称不能为空");

            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDtoToBO(authRoleDTO);
            return Result.ok(authRoleDomainService.add(authRoleBO));
        } catch (Exception e) {
            log.error("RoleController.add.error:{}", e.getMessage(), e);
            return Result.fail("添加角色失败");
        }
    }


    /**
     * 修改角色
     * @param authRoleDTO
     * @return
     */
    @RequestMapping ("/update")
    public Result<Boolean> update(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RoleController.update.dto:{}", JSON.toJSONString(authRoleDTO));
            }
            Preconditions.checkNotNull(authRoleDTO.getId(), "角色id不能为空");

            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDtoToBO(authRoleDTO);
            return Result.ok(authRoleDomainService.update(authRoleBO));
        } catch (Exception e) {
            log.error("RoleController.update.error:{}", e.getMessage(), e);
            return Result.fail("删除角色信息失败");
        }
    }

    /**
     * 删除用户
     * @param authRoleDTO
     * @return
     */
    @RequestMapping ("/delete")
    public Result<Boolean> delete(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RoleController.delete.dto:{}", JSON.toJSONString(authRoleDTO));
            }
            Preconditions.checkNotNull(authRoleDTO.getId(), "角色id不能为空");
            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDtoToBO(authRoleDTO);
            return Result.ok(authRoleDomainService.delete(authRoleBO));
        } catch (Exception e) {
            log.error("RoleController.delete.error:{}", e.getMessage(), e);
            return Result.fail("删除角色信息失败");
        }
    }

    // /**
    //  * 用户状态设置
    //  * @param authUserDTO
    //  * @return
    //  */
    // @RequestMapping ("/changeStatus")
    // public Result<Boolean> changeStatus(@RequestBody AuthUserDTO authUserDTO) {
    //     try {
    //         if (log.isInfoEnabled()) {
    //             log.info("UserController.changeStatus.dto:{}", JSON.toJSONString(authUserDTO));
    //         }
    //         Preconditions.checkNotNull(authUserDTO.getId(), "用户id不能为空");
    //         Preconditions.checkNotNull(authUserDTO.getStatus(), "用户状态不能为空");
    //
    //         AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDtoToBO(authUserDTO);
    //         return Result.ok(authUserDomainService.update(authUserBO));
    //     } catch (Exception e) {
    //         log.error("UserController.changeStatus.error:{}", e.getMessage(), e);
    //         return Result.fail("设置用户状态失败");
    //     }
    // }
}
