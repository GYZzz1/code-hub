package com.gyzjc.subject.domain.entity;

import com.gyzjc.subject.common.entity.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目信息(SubjectInfo)DTO
 *
 * @author makejava
 * @since 2024-09-10 19:41:39
 */
@Data
public class SubjectOptionBO extends PageInfo implements Serializable {
    private static final long serialVersionUID = 946309735341667385L;

    /**
     * 题目解析
     */
    private String subjectAnswer;

    /**
     * 答案选项
     */
    private List<SubjectAnswerBO> optionList;
}

