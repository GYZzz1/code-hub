package com.gyzjc.subject.domain.service;

import com.gyzjc.subject.common.entity.PageResult;
import com.gyzjc.subject.domain.entity.SubjectInfoBO;

public interface SubjectInfoDomainService {

    /**
     * 新增题目
     * @param subjectInfoBO
     */
    Boolean add(SubjectInfoBO subjectInfoBO);

    /**
     * 分页查询
     * @param subjectInfoBO
     * @return
     */
    PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO);

    /**
     * 分页查询
     * @param subjectInfoBO
     * @return
     */
    SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO);
}
