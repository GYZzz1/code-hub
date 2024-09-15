package com.gyzjc.auth.domain.service;

import com.gyzjc.auth.domain.entity.AuthUserBO;

/**
 * @ClassName : AuthUserDomainService
 * @Description : 用户领域service
 * @Author : GYZzz
 * @Date: 2024-09-16 02:43
 */
public interface AuthUserDomainService {
    /**
     * 注册
     * @param subjectCategoryBO
     * @return
     */
    Boolean register(AuthUserBO subjectCategoryBO);

    /**
     * 更新用户信息
     *
     * @param authUserBO
     * @return
     */
    Boolean update(AuthUserBO authUserBO);
}
