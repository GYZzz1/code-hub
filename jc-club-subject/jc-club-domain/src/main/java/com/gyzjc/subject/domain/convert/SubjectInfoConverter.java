package com.gyzjc.subject.domain.convert;

import com.gyzjc.subject.domain.entity.SubjectCategoryBO;
import com.gyzjc.subject.domain.entity.SubjectInfoBO;
import com.gyzjc.subject.domain.entity.SubjectOptionBO;
import com.gyzjc.subject.infra.basic.entity.SubjectCategory;
import com.gyzjc.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoConverter {
    SubjectInfoConverter INSTANCE = Mappers.getMapper(SubjectInfoConverter.class);

    SubjectInfo convertBoToInfo(SubjectInfoBO subjectInfoBO);
    SubjectInfoBO convertOptionToBO(SubjectOptionBO subjectOptionBO);
    SubjectInfoBO convertOptionAndInfoToBO(SubjectOptionBO subjectOptionBO, SubjectInfo subjectInfo);
    List<SubjectInfoBO> convertBoToCategory(List<SubjectInfo> subjectInfoList);
}
