package com.gyzjc.subject.domain.handler.subject;

import com.gyzjc.subject.common.enums.IsDeletedFlagEnum;
import com.gyzjc.subject.common.enums.SubjectInfoTypeEnum;
import com.gyzjc.subject.domain.convert.MutipleSubjectConverter;
import com.gyzjc.subject.domain.entity.SubjectInfoBO;
import com.gyzjc.subject.domain.entity.SubjectOptionBO;
import com.gyzjc.subject.infra.basic.entity.SubjectMultiple;
import com.gyzjc.subject.infra.basic.service.SubjectMultipleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 多选题目的策略类
 */
@Component
public class MultipleTypeHandler implements SubjectTypeHandler {

    @Resource
    private SubjectMultipleService subjectMultipleService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.MULTIPLE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        // 多选题目的插入
        List<SubjectMultiple> subjectMultipleList = new LinkedList<>();

        subjectInfoBO.getOptionList().forEach(option -> {
            SubjectMultiple subjectMultiple = MutipleSubjectConverter.INSTANCE.convertBoToEntity(option);
            subjectMultiple.setSubjectId(subjectInfoBO.getId());
            subjectMultiple.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
            subjectMultipleList.add(subjectMultiple);
        });

        subjectMultipleService.batchInsert(subjectMultipleList);
    }

    @Override
    public SubjectOptionBO query(int subjectId) {
        return null;
    }
}
