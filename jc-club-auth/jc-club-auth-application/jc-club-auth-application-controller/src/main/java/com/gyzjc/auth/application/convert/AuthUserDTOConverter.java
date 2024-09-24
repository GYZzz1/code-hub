package com.gyzjc.auth.application.convert;

import com.gyzjc.auth.application.dto.AuthUserDTO;
import com.gyzjc.auth.domain.entity.AuthUserBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户DTO转换器
 */
@Mapper
public interface AuthUserDTOConverter {
    AuthUserDTOConverter INSTANCE = Mappers.getMapper(AuthUserDTOConverter.class);

    AuthUserBO convertDtoToBO(AuthUserDTO authUserDTO);
    AuthUserDTO convertBOToDTO(AuthUserBO authUserBO);
}
