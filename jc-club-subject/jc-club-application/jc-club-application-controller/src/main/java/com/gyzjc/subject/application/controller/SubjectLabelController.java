package com.gyzjc.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.gyzjc.subject.application.convert.SubjectCategoryDTOConverter;
import com.gyzjc.subject.application.convert.SubjectLabelDTOConverter;
import com.gyzjc.subject.application.dto.SubjectCategoryDTO;
import com.gyzjc.subject.application.dto.SubjectLabelDTO;
import com.gyzjc.subject.common.entity.Result;
import com.gyzjc.subject.domain.entity.SubjectCategoryBO;
import com.gyzjc.subject.domain.entity.SubjectLabelBO;
import com.gyzjc.subject.domain.service.SubjectCategoryDomainService;
import com.gyzjc.subject.domain.service.SubjectLabelDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 标签controller
 *
 * @author gyz
 * @since 2024/06/15
 */
@Slf4j
@RestController
@RequestMapping("/subject/label")
public class SubjectLabelController {
    @Resource
    private SubjectLabelDomainService subjectLabelDomainService;

    /**
     * 新增标签
     * @param subjectLabelDTO
     * @return
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.add.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }

            Preconditions.checkArgument(!StringUtils.isBlank(subjectLabelDTO.getLabelName()), "标签不能为空");

            SubjectLabelBO subjectCategoryBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToLabelBO(subjectLabelDTO);
            Boolean result = subjectLabelDomainService.add(subjectCategoryBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("SubjectLabelController.add.error:{}", e.getMessage(), e);
            return Result.fail("新增标签失败");
        }
    }

    /**
     * 更新标签
     *
     * @return
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelController.update.dto: {}", subjectLabelDTO);
        }
        Preconditions.checkNotNull(subjectLabelDTO.getId(), "标签id不能为空");
        SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToLabelBO(subjectLabelDTO);
        try {
            Boolean result = subjectLabelDomainService.update(subjectLabelBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("SubjectLabelController.update.error:{}", e.getMessage(), e);
            return Result.fail("更新标签失败");
        }
    }

    /**
     * 删除标签
     * @param subjectLabelDTO
     * @return
     */
    @DeleteMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelController.delete.dto: {}", subjectLabelDTO);
        }
        SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToLabelBO(subjectLabelDTO);
        try {
            Boolean result = subjectLabelDomainService.delete(subjectLabelBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("SubjectLabelController.delete.error:{}", e.getMessage(), e);
            return Result.fail("删除标签失败");
        }
    }

    // @PostMapping("/queryPrimaryCategory")
    // public Result<List<SubjectCategoryDTO>> queryPrimaryCategory(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
    //     try {
    //         SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtoToCategoryBO(subjectCategoryDTO);
    //         // List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService.queryCategory(subjectCategoryBO);
    //         List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDTOConverter.INSTANCE.
    //                 convertBoListToCategoryDtoList(subjectCategoryBOList);
    //         return Result.ok(subjectCategoryDTOList);
    //     } catch (Exception e) {
    //         log.error("SubjectCategoryController.queryPrimaryCategory.error:{}", e.getMessage(), e);
    //         return Result.fail("查询失败");
    //     }
    // }
    //
    // @PostMapping("/queryCategoryByPrimary")
    // public Result<List<SubjectCategoryDTO>> queryCategoryByPrimary(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
    //     if (log.isInfoEnabled()) {
    //         log.info("SubjectCategoryController.queryPrimaryCategory.dto: {}", subjectCategoryDTO);
    //     }
    //
    //     Preconditions.checkNotNull(subjectCategoryDTO.getParentId(), "分類id不爲空");
    //     SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtoToCategoryBO(subjectCategoryDTO);
    //     try {
    //         List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService.queryCategory(subjectCategoryBO);
    //         List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDTOConverter.INSTANCE.
    //                 convertBoListToCategoryDtoList(subjectCategoryBOList);
    //         return Result.ok(subjectCategoryDTOList);
    //     } catch (Exception e) {
    //         log.error("SubjectCategoryController.queryPrimaryCategory.error:{}", e.getMessage(), e);
    //         return Result.fail("查询失败");
    //     }
    // }
    //
    // /**
    //  * 更新分类
    //  *
    //  * @return
    //  */
    // @PostMapping("/update")
    // public Result<Boolean> update(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
    //     if (log.isInfoEnabled()) {
    //         log.info("SubjectCategoryController.update.dto: {}", subjectCategoryDTO);
    //     }
    //     SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtoToCategoryBO(subjectCategoryDTO);
    //     try {
    //         Boolean result = subjectCategoryDomainService.update(subjectCategoryBO);
    //         return Result.ok(result);
    //     } catch (Exception e) {
    //         log.error("SubjectCategoryController.queryPrimaryCategory.error:{}", e.getMessage(), e);
    //         return Result.fail("更新分类失败");
    //     }
    // }
    //
    // /**
    //  * 删除分类
    //  * @param subjectCategoryDTO
    //  * @return
    //  */
    // @DeleteMapping("/delete")
    // public Result<Boolean> delete(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
    //     if (log.isInfoEnabled()) {
    //         log.info("SubjectCategoryController.update.dto: {}", subjectCategoryDTO);
    //     }
    //     SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtoToCategoryBO(subjectCategoryDTO);
    //     try {
    //         Boolean result = subjectCategoryDomainService.delete(subjectCategoryBO);
    //         return Result.ok(result);
    //     } catch (Exception e) {
    //         log.error("SubjectCategoryController.queryPrimaryCategory.error:{}", e.getMessage(), e);
    //         return Result.fail("删除分类失败");
    //     }
    // }
}
