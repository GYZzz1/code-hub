package com.gyzjc.auth.domain.convert;

import com.gyzjc.auth.domain.entity.AuthRoleBO;
import com.gyzjc.auth.infra.basic.entity.AuthRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @ClassName : AuthRoleBOConvertor
 * @Description : 角色BO转换器
 * @Author : GYZzz
 * @Date: 2024-09-16 22:46
 */
@Mapper
public interface AuthRoleBOConvertor {
    AuthRoleBOConvertor INSTANCE = Mappers.getMapper(AuthRoleBOConvertor.class);

    AuthRole convertBOToEntity(AuthRoleBO authRoleBO);
}
