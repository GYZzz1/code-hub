package com.gyzjc.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目答案DTO
 *
 * @author makejava
 * @since 2024-09-10 19:41:39
 */
@Data
public class SubjectAnswerBO implements Serializable {
    private static final long serialVersionUID = 946309735341667385L;
    /**
     * 答案选项标识
     */
    private Integer optionType;
    /**
     * 答案
     */
    private String optionContent;
    /**
     * 是否正确
     */
    private Integer isCorrect;

}

