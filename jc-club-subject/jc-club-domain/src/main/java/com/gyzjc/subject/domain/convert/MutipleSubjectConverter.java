package com.gyzjc.subject.domain.convert;

import com.gyzjc.subject.domain.entity.SubjectAnswerBO;
import com.gyzjc.subject.infra.basic.entity.SubjectJudge;
import com.gyzjc.subject.infra.basic.entity.SubjectMultiple;
import com.gyzjc.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MutipleSubjectConverter {
    MutipleSubjectConverter INSTANCE = Mappers.getMapper(MutipleSubjectConverter.class);

    SubjectMultiple convertBoToEntity(SubjectAnswerBO subjectAnswerBO);

    List<SubjectAnswerBO> convertEntityToAnswerBOList(List<SubjectMultiple> subjectMultiples);

}
