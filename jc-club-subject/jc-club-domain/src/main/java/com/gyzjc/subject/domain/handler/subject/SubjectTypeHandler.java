package com.gyzjc.subject.domain.handler.subject;

import com.gyzjc.subject.common.enums.SubjectInfoTypeEnum;
import com.gyzjc.subject.domain.entity.SubjectInfoBO;
import com.gyzjc.subject.domain.entity.SubjectOptionBO;

public interface SubjectTypeHandler {

    /**
     * 枚举身份的识别
     * @return
     */
    SubjectInfoTypeEnum getHandlerType();

    /**
     * 实际的题目的插入
     * @param subjectInfoBO
     */
    void add(SubjectInfoBO subjectInfoBO);

    /**
     * 查询
     * @param subjectId
     */
    SubjectOptionBO query(int subjectId);
}
