package com.gyzjc.subject.domain.convert;

import com.gyzjc.subject.domain.entity.SubjectCategoryBO;
import com.gyzjc.subject.domain.entity.SubjectLabelBO;
import com.gyzjc.subject.infra.basic.entity.SubjectCategory;
import com.gyzjc.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectLabelConverter {
    SubjectLabelConverter INSTANCE = Mappers.getMapper(SubjectLabelConverter.class);

    SubjectLabel convertBoToLabel(SubjectLabelBO subjectLabelBO);
}
