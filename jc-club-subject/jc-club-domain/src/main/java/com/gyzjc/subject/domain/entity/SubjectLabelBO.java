package com.gyzjc.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目标签BO
 *
 * @author makejava
 * @since 2024-08-11 21:34:07
 */
@Data
public class SubjectLabelBO implements Serializable {
    private static final long serialVersionUID = 488132488979112555L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 标签分类
     */
    private String labelName;
    /**
     * 分类id
     */
    private Long categoryId;
    /**
     * 排序
     */
    private Integer sortNum;

}

