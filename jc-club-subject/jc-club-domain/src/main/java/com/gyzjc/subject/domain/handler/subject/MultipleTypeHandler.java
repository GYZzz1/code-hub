package com.gyzjc.subject.domain.handler.subject;

import com.gyzjc.subject.common.enums.SubjectInfoTypeEnum;
import com.gyzjc.subject.domain.entity.SubjectInfoBO;
import org.springframework.stereotype.Component;

/**
 * 多选题目的策略类
 */
@Component
public class MultipleTypeHandler implements SubjectTypeHandler {
    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.MULTIPLE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        // 多选题目的插入

    }
}
