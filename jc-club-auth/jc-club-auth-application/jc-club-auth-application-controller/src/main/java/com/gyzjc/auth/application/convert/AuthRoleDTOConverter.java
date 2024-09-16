package com.gyzjc.auth.application.convert;

import com.gyzjc.auth.application.dto.AuthRoleDTO;
import com.gyzjc.auth.domain.entity.AuthRoleBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户DTO转换器
 */
@Mapper
public interface AuthRoleDTOConverter {
    AuthRoleDTOConverter INSTANCE = Mappers.getMapper(AuthRoleDTOConverter.class);

    AuthRoleBO convertDtoToBO(AuthRoleDTO authRoleDTO);
}
