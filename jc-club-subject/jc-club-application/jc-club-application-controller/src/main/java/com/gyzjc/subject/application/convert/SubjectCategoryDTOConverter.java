package com.gyzjc.subject.application.convert;

import com.gyzjc.subject.application.dto.SubjectCategoryDTO;
import com.gyzjc.subject.domain.entity.SubjectCategoryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectCategoryDTOConverter {
    SubjectCategoryDTOConverter INSTANCE = Mappers.getMapper(SubjectCategoryDTOConverter.class);

    SubjectCategoryBO convertDtoToCategoryBO(SubjectCategoryDTO subjectCategoryDTO);
    List<SubjectCategoryDTO> convertBoListToCategoryDtoList(List<SubjectCategoryBO> subjectCategoryBOList);
}
