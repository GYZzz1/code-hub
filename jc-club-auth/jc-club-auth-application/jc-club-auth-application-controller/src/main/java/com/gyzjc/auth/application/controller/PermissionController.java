package com.gyzjc.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.gyzjc.auth.application.convert.AuthPermissionDTOConverter;
import com.gyzjc.auth.application.dto.AuthPermissionDTO;
import com.gyzjc.auth.common.entity.Result;
import com.gyzjc.auth.domain.entity.AuthPermissionBO;
import com.gyzjc.auth.domain.service.AuthPermissionDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName : PermissionController
 * @Description : 权限controller
 * @Author : GYZzz
 * @Date: 2024-09-16 23:55
 */
@RestController
@RequestMapping("/permission")
@Slf4j
public class PermissionController {

    @Resource
    private AuthPermissionDomainService authPermissionDomainService;

    /**
     * 新增权限
     * @param authPermissionDTO
     * @return
     */
    @RequestMapping ("/add")
    public Result<Boolean> add(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PermissionController.add.dto:{}", JSON.toJSONString(authPermissionDTO));
            }

            Preconditions.checkArgument(!StringUtils.isBlank(authPermissionDTO.getName()), "权限名称不能为空");
            Preconditions.checkNotNull(authPermissionDTO.getParentId(), "权限父id不能为空");

            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDtoToBO(authPermissionDTO);
            return Result.ok(authPermissionDomainService.add(authPermissionBO));
        } catch (Exception e) {
            log.error("PermissionController.add.error:{}", e.getMessage(), e);
            return Result.fail("新增权限失败");
        }
    }


    /**
     * 修改权限
     * @param authPermissionDTO
     * @return
     */
    @RequestMapping ("/update")
    public Result<Boolean> update(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PermissionController.update.dto:{}", JSON.toJSONString(authPermissionDTO));
            }
            Preconditions.checkNotNull(authPermissionDTO.getId(), "权限id不能为空");

            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDtoToBO(authPermissionDTO);
            return Result.ok(authPermissionDomainService.update(authPermissionBO));
        } catch (Exception e) {
            log.error("PermissionController.update.error:{}", e.getMessage(), e);
            return Result.fail("修改权限信息失败");
        }
    }

    /**
     * 删除用户
     * @param authPermissionDTO
     * @return
     */
    @RequestMapping ("/delete")
    public Result<Boolean> delete(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PermissionController.delete.dto:{}", JSON.toJSONString(authPermissionDTO));
            }
            Preconditions.checkNotNull(authPermissionDTO.getId(), "权限id不能为空");
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDtoToBO(authPermissionDTO);
            return Result.ok(authPermissionDomainService.delete(authPermissionBO));
        } catch (Exception e) {
            log.error("PermissionController.delete.error:{}", e.getMessage(), e);
            return Result.fail("删除权限信息失败");
        }
    }

    /**
     * 查询用户权限
     * @param userName
     * @return
     */
    @RequestMapping ("/getPermission")
    public Result<Boolean> getPermission(String userName) {
        try {
            log.info("PermissionController.getPermission.userName:{}", JSON.toJSONString(userName));
            Preconditions.checkNotNull(userName, "用户名不能为空");
            return Result.ok(authPermissionDomainService.getPermission(userName));
        } catch (Exception e) {
            log.error("PermissionController.getPermission.error:{}", e.getMessage(), e);
            return Result.fail("删除权限信息失败");
        }
    }
}
