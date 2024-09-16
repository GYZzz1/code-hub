package com.gyzjc.auth.application.convert;

import com.gyzjc.auth.application.dto.AuthPermissionDTO;
import com.gyzjc.auth.domain.entity.AuthPermissionBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 权限DTO转换器
 */
@Mapper
public interface AuthPermissionDTOConverter {
    AuthPermissionDTOConverter INSTANCE = Mappers.getMapper(AuthPermissionDTOConverter.class);

    AuthPermissionBO convertDtoToBO(AuthPermissionDTO authPermissionDTO);
}
