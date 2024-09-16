package com.gyzjc.auth.application.convert;

import com.gyzjc.auth.application.dto.AuthPermissionDTO;
import com.gyzjc.auth.application.dto.AuthRolePermissionDTO;
import com.gyzjc.auth.domain.entity.AuthPermissionBO;
import com.gyzjc.auth.domain.entity.AuthRolePermissionBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 角色权限DTO转换器
 */
@Mapper
public interface AuthRolePermissionDTOConverter {
    AuthRolePermissionDTOConverter INSTANCE = Mappers.getMapper(AuthRolePermissionDTOConverter.class);

    AuthRolePermissionBO convertDtoToBO(AuthRolePermissionDTO authRolePermissionDTO);
}
