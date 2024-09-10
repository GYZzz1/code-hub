package com.gyzjc.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.gyzjc.subject.application.convert.SubjectAnswerDTOConverter;
import com.gyzjc.subject.application.convert.SubjectInfoDTOConverter;
import com.gyzjc.subject.application.dto.SubjectInfoDTO;
import com.gyzjc.subject.common.entity.PageResult;
import com.gyzjc.subject.common.entity.Result;
import com.gyzjc.subject.domain.entity.SubjectAnswerBO;
import com.gyzjc.subject.domain.entity.SubjectInfoBO;
import com.gyzjc.subject.domain.service.SubjectInfoDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 刷题controller
 *
 * @author gyz
 * @since 2024/06/15
 */
@RestController
@Slf4j
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectInfoDomainService subjectInfoDomainService;


    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectController.add.dto:{}", JSON.toJSONString(subjectInfoDTO));
            }

            Preconditions.checkArgument(!StringUtils.isBlank(subjectInfoDTO.getSubjectName()), "题目名称不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectDifficult(), "题目难度不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectType(), "题目类型不能为空");

            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getCategoryIds()), "分类id不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getLabelIds()), "标签id不能为空");

            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDtoToBO(subjectInfoDTO);
            List<SubjectAnswerBO> subjectAnswerBOS = SubjectAnswerDTOConverter.INSTANCE.convertDTOListToBOList(subjectInfoDTO.getOptionList());
            subjectInfoBO.setOptionList(subjectAnswerBOS);

            subjectInfoDomainService.add(subjectInfoBO);
            return Result.ok(true);
        } catch (Exception e) {
            log.error("SubjectController.add.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 查询题目列表
     * @param subjectInfoDTO
     * @return
     */
    @PostMapping("/getSubjectPage")
    public Result<PageResult<SubjectInfoDTO>> getSubjectPage(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectController.getSubjectPage.dto:{}", JSON.toJSONString(subjectInfoDTO));
            }

            Preconditions.checkNotNull(subjectInfoDTO.getCategoryId(), "题目分类不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getLabelId(), "题目标签不能为空");

            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDtoToBO(subjectInfoDTO);
            List<SubjectAnswerBO> subjectAnswerBOS = SubjectAnswerDTOConverter.INSTANCE.convertDTOListToBOList(subjectInfoDTO.getOptionList());
            subjectInfoBO.setOptionList(subjectAnswerBOS);
            PageResult<SubjectInfoBO> result = subjectInfoDomainService.getSubjectPage(subjectInfoBO);
            return Result.ok(result);
        }catch (Exception e) {
            log.error("SubjectCategoryController.add.error:{}",e.getMessage(),e);
            return Result.fail("add fail");
        }

    }

    /**
     * 查询题目信息
     * @param subjectInfoDTO
     * @return
     */
    @PostMapping("/querySubjectInfo")
    public Result<SubjectInfoDTO> querySubjectInfo(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        return Result.fail("尚未开发");
        // try {
        //
        //     Preconditions.checkNotNull(subjectInfoDTO.getId(), "题目id不能为空");
        //     Preconditions.checkNotNull(subjectInfoDTO.getCategoryId(), "题目分类不能为空");
        //     Preconditions.checkNotNull(subjectInfoDTO.getLabelId(), "题目标签不能为空");
        //
        //     SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDtoToBO(subjectInfoDTO);
        //     SubjectInfoBO boResult = subjectInfoDomainService.querySubjectInfo(subjectInfoBO);
        //     SubjectInfoDTO dto = SubjectInfoDTOConverter.INSTANCE.convertBOToDto(boResult);
        //     return Result.ok(dto);
        // }catch (Exception e) {
        //     log.error("SubjectCategoryController.add.error:{}",e.getMessage(),e);
        //     return Result.fail("add fail");
        // }

    }
}
