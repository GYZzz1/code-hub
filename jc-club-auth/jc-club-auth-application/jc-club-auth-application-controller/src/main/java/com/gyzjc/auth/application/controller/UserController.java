package com.gyzjc.auth.application.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.gyzjc.auth.application.convert.AuthUserDTOConverter;
import com.gyzjc.auth.application.dto.AuthUserDTO;
import com.gyzjc.auth.common.entity.Result;
import com.gyzjc.auth.domain.entity.AuthUserBO;
import com.gyzjc.auth.domain.service.AuthUserDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName : UserController
 * @Description : 用户controller
 * @Author : GYZzz
 * @Date: 2024-09-15 11:22
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private AuthUserDomainService authUserDomainService;

    /**
     * 用户注册
     * @param authUserDTO
     * @return
     */
    @RequestMapping ("/register")
    public Result<Boolean> register(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.register.dto:{}", JSON.toJSONString(authUserDTO));
            }

            checkUserInfo(authUserDTO);

            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDtoToBO(authUserDTO);
            return Result.ok(authUserDomainService.register(authUserBO));
        } catch (Exception e) {
            log.error("UserController.register.error:{}", e.getMessage(), e);
            return Result.fail("注册用户失败");
        }
    }


    /**
     * 修改用户信息
     * @param authUserDTO
     * @return
     */
    @RequestMapping ("/update")
    public Result<Boolean> update(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.update.dto:{}", JSON.toJSONString(authUserDTO));
            }
            checkUserInfo(authUserDTO);
            Preconditions.checkNotNull(authUserDTO.getId(), "用户id不能为空");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDtoToBO(authUserDTO);
            return Result.ok(authUserDomainService.update(authUserBO));
        } catch (Exception e) {
            log.error("UserController.update.error:{}", e.getMessage(), e);
            return Result.fail("更新用户信息失败");
        }
    }

    /**
     * 校验入参
     * @param authUserDTO
     */
    private void checkUserInfo(AuthUserDTO authUserDTO) {
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()), "用户名不能为空");
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getEmail()), "用户邮箱不能为空");
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getPassword()), "用户密码不能为空");
    }

    /**
     * 获取用户信息
     * @param authUserDTO
     * @return
     */
    @RequestMapping ("/getUserInfo")
    public Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.getUserInfo.dto:{}", JSON.toJSONString(authUserDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()), "用户名不能为空");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDtoToBO(authUserDTO);
            AuthUserBO userInfo = authUserDomainService.getUserInfo(authUserBO);
            return Result.ok(AuthUserDTOConverter.INSTANCE.convertBOToDTO(userInfo));
        } catch (Exception e) {
            log.error("UserController.getUserInfo.error:{}", e.getMessage(), e);
            return Result.fail("获取用户信息失败");
        }
    }

    /**
     * 删除用户
     * @param authUserDTO
     * @return
     */
    @RequestMapping ("/delete")
    public Result<Boolean> delete(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.delete.dto:{}", JSON.toJSONString(authUserDTO));
            }
            Preconditions.checkNotNull(authUserDTO.getId(), "用户id不能为空");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDtoToBO(authUserDTO);
            return Result.ok(authUserDomainService.delete(authUserBO));
        } catch (Exception e) {
            log.error("UserController.delete.error:{}", e.getMessage(), e);
            return Result.fail("删除用户信息失败");
        }
    }

    /**
     * 用户状态设置
     * @param authUserDTO
     * @return
     */
    @RequestMapping ("/changeStatus")
    public Result<Boolean> changeStatus(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.changeStatus.dto:{}", JSON.toJSONString(authUserDTO));
            }
            Preconditions.checkNotNull(authUserDTO.getId(), "用户id不能为空");
            Preconditions.checkNotNull(authUserDTO.getStatus(), "用户状态不能为空");

            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDtoToBO(authUserDTO);
            return Result.ok(authUserDomainService.update(authUserBO));
        } catch (Exception e) {
            log.error("UserController.changeStatus.error:{}", e.getMessage(), e);
            return Result.fail("设置用户状态失败");
        }
    }

    /**
     * 登录
     * @param validateCode
     * @return
     */
    @GetMapping("/doLogin")
    public Result<SaTokenInfo> doLogin(@RequestParam("validCode") String validateCode) {
        try {
            Preconditions.checkArgument(StringUtils.isNotBlank(validateCode), "验证码不能为空");
            return Result.ok(authUserDomainService.doLogin(validateCode));
        } catch (Exception e) {
            log.error("UserController.doLogin.error:{}", e.getMessage(), e);
            return Result.fail("登录失败");
        }
    }

    /**
     * 退出登录
     * @param userName
     * @return
     */
    @RequestMapping ("/logOut")
    public Result<Void> logOut(@RequestParam String userName) {
        try {
            log.info("UserController.logOut.userName:{}", userName);
            Preconditions.checkArgument(!StringUtils.isBlank(userName) , "用户名不能为空");
            StpUtil.logout(userName);
            return Result.ok();
        } catch (Exception e) {
            log.error("UserController.logOut.error:{}", e.getMessage(), e);
            return Result.fail("退出登录失败");
        }
    }

    @GetMapping("/isLogin")
    public String isLogin() {
        return "是否登录" + StpUtil.isLogin();
    }
}
