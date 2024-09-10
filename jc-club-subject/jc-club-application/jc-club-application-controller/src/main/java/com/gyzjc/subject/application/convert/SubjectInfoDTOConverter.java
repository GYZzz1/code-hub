package com.gyzjc.subject.application.convert;

import com.gyzjc.subject.application.dto.SubjectCategoryDTO;
import com.gyzjc.subject.application.dto.SubjectInfoDTO;
import com.gyzjc.subject.domain.entity.SubjectCategoryBO;
import com.gyzjc.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoDTOConverter {
    SubjectInfoDTOConverter INSTANCE = Mappers.getMapper(SubjectInfoDTOConverter.class);

    SubjectInfoBO convertDtoToBO(SubjectInfoDTO subjectInfoDTO);
    List<SubjectCategoryDTO> convertBoListToCategoryDtoList(List<SubjectCategoryBO> subjectCategoryBOList);
}
