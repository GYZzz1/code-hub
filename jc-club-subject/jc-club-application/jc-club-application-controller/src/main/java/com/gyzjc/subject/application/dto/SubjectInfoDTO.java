package com.gyzjc.subject.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目信息(SubjectInfo)DTO
 *
 * @author makejava
 * @since 2024-09-10 19:41:39
 */
@Data
public class SubjectInfoDTO implements Serializable {
    private static final long serialVersionUID = 946309735341667385L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 题目名称
     */
    private String subjectName;
    /**
     * 题目难度
     */
    private Integer subjectDifficult;
    /**
     * 出题人名
     */
    private String settleName;
    /**
     * 题目类型 1单选 2多选 3判断 4简答
     */
    private Integer subjectType;
    /**
     * 题目分数
     */
    private Integer subjectScore;
    /**
     * 题目解析
     */
    private String subjectParse;
    /**
     * 分类id
     */
    private List<Integer> categoryIds;
    /**
     * 标签id
     */
    private List<Integer> labelIds;
    /**
     * 答案选项
     */
    private List<SubjectAnswerDTO> optionList;
}

