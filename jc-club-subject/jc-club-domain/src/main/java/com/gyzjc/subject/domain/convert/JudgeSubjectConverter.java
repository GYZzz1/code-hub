package com.gyzjc.subject.domain.convert;

import com.gyzjc.subject.domain.entity.SubjectAnswerBO;
import com.gyzjc.subject.domain.entity.SubjectInfoBO;
import com.gyzjc.subject.domain.entity.SubjectOptionBO;
import com.gyzjc.subject.infra.basic.entity.SubjectBrief;
import com.gyzjc.subject.infra.basic.entity.SubjectJudge;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface JudgeSubjectConverter {
    JudgeSubjectConverter INSTANCE = Mappers.getMapper(JudgeSubjectConverter.class);

    List<SubjectAnswerBO> convertEntityToAnswerBOList(List<SubjectJudge> subjectJudges);
}
