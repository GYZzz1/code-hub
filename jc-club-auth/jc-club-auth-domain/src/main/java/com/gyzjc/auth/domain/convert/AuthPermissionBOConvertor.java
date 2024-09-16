package com.gyzjc.auth.domain.convert;

import com.gyzjc.auth.domain.entity.AuthPermissionBO;
import com.gyzjc.auth.infra.basic.entity.AuthPermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @ClassName : AuthPermissionBOConvertor
 * @Description : 权限BO转换器
 * @Author : GYZzz
 * @Date: 2024-09-16 22:46
 */
@Mapper
public interface AuthPermissionBOConvertor {
    AuthPermissionBOConvertor INSTANCE = Mappers.getMapper(AuthPermissionBOConvertor.class);

    AuthPermission convertBOToEntity(AuthPermissionBO authPermissionBO);
}
