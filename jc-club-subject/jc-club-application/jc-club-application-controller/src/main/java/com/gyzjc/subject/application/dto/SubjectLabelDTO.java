package com.gyzjc.subject.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目标签DTO
 *
 * @author makejava
 * @since 2024-08-11 21:34:07
 */
@Data
public class SubjectLabelDTO implements Serializable {
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

