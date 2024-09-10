package com.gyzjc.subject.domain.service;

import com.gyzjc.subject.domain.entity.SubjectCategoryBO;
import com.gyzjc.subject.domain.entity.SubjectLabelBO;

import java.util.List;

public interface SubjectLabelDomainService {

    /**
     * 新增标签
     * @param subjectLabelBO
     */
    Boolean add(SubjectLabelBO subjectLabelBO);

    /**
     * 更新标签
     * @param subjectLabelBO
     * @return
     */
    Boolean update(SubjectLabelBO subjectLabelBO);

    /**
     * 删除标签
     * @param subjectLabelBO
     * @return
     */
    Boolean delete(SubjectLabelBO subjectLabelBO);

    // /**
    //  * 查询岗位大类
    //  * @return
    //  */
    // List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO);
    //
    // /**
    //  * 更新分类
    //  * @param subjectCategoryBO
    //  * @return
    //  */
    // Boolean update(SubjectCategoryBO subjectCategoryBO);
    //
    // /**
    //  * 删除分类
    //  * @param subjectCategoryBO
    //  * @return
    //  */
    // Boolean delete(SubjectCategoryBO subjectCategoryBO);
}
