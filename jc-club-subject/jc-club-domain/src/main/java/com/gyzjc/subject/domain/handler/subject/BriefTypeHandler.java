package com.gyzjc.subject.domain.handler.subject;

import com.gyzjc.subject.common.enums.IsDeletedFlagEnum;
import com.gyzjc.subject.common.enums.SubjectInfoTypeEnum;
import com.gyzjc.subject.domain.convert.BriefSubjectConverter;
import com.gyzjc.subject.domain.entity.SubjectAnswerBO;
import com.gyzjc.subject.domain.entity.SubjectInfoBO;
import com.gyzjc.subject.domain.entity.SubjectOptionBO;
import com.gyzjc.subject.infra.basic.entity.SubjectBrief;
import com.gyzjc.subject.infra.basic.entity.SubjectJudge;
import com.gyzjc.subject.infra.basic.service.SubjectBriefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 简答题目的策略类
 */
@Component
public class BriefTypeHandler implements SubjectTypeHandler {

    @Autowired
    private SubjectBriefService subjectBriefService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.BRIEF;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        // 简答题目的插入
        SubjectBrief subjectBrief = BriefSubjectConverter.INSTANCE.convertBoToEntity(subjectInfoBO);
        subjectBrief.setSubjectId(subjectInfoBO.getId().intValue());
        subjectBrief.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        subjectBriefService.insert(subjectBrief);
    }

    @Override
    public SubjectOptionBO query(int subjectId) {
        SubjectBrief subjectBrief = subjectBriefService.queryById(Long.valueOf(subjectId));
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setSubjectAnswer(subjectBrief.getSubjectAnswer());
        return subjectOptionBO;
    }
}
