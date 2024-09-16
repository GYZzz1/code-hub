package com.gyzjc.auth.domain.convert;

import com.gyzjc.auth.domain.entity.AuthRolePermissionBO;
import com.gyzjc.auth.infra.basic.entity.AuthRolePermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @ClassName : AuthRolePermissionBOConvertor
 * @Description : 角色权限BO转换器
 * @Author : GYZzz
 * @Date: 2024-09-16 22:46
 */
@Mapper
public interface AuthRolePermissionBOConvertor {
    AuthRolePermissionBOConvertor INSTANCE = Mappers.getMapper(AuthRolePermissionBOConvertor.class);

    AuthRolePermission convertBOToEntity(AuthRolePermissionBO authRolePermissionBO);
}
