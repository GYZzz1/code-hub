package com.gyzjc.subject.application.convert;

import com.gyzjc.subject.application.dto.SubjectAnswerDTO;
import com.gyzjc.subject.application.dto.SubjectCategoryDTO;
import com.gyzjc.subject.application.dto.SubjectInfoDTO;
import com.gyzjc.subject.domain.entity.SubjectAnswerBO;
import com.gyzjc.subject.domain.entity.SubjectCategoryBO;
import com.gyzjc.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectAnswerDTOConverter {
    SubjectAnswerDTOConverter INSTANCE = Mappers.getMapper(SubjectAnswerDTOConverter.class);

    SubjectAnswerBO convertDtoToBO(SubjectAnswerDTO subjectAnswerDTO);
    List<SubjectAnswerBO> convertDTOListToBOList(List<SubjectAnswerDTO> dtoList);
}
