package com.gyzjc.auth.domain.convert;

import com.gyzjc.auth.domain.entity.AuthUserBO;
import com.gyzjc.auth.infra.basic.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @ClassName : AuthUserBOConvertor
 * @Description : 用户BO转换器
 * @Author : GYZzz
 * @Date: 2024-09-16 02:47
 */
@Mapper
public interface AuthUserBOConvertor {
    AuthUserBOConvertor INSTANCE = Mappers.getMapper(AuthUserBOConvertor.class);

    AuthUser convertBOToEntity(AuthUserBO authUserBO);
    AuthUserBO convertEntityToBO(AuthUser authUser);
}
