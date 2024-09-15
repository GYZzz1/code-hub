package com.gyzjc.auth.application.convert;

import com.gyzjc.auth.application.dto.AuthUserDTO;
import com.gyzjc.auth.domain.entity.AuthUserBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户DTO转换器
 */
@Mapper
public interface AuthDTOUserConverter {
    AuthDTOUserConverter INSTANCE = Mappers.getMapper(AuthDTOUserConverter.class);

    AuthUserBO convertDtoToBO(AuthUserDTO authUserDTO);
}
