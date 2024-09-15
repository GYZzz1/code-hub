package com.gyzjc.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.gyzjc.subject.common.enums.CategoryTypeEnum;
import com.gyzjc.subject.common.enums.IsDeletedFlagEnum;
import com.gyzjc.subject.domain.convert.SubjectLabelConverter;
import com.gyzjc.subject.domain.entity.SubjectLabelBO;
import com.gyzjc.subject.domain.service.SubjectLabelDomainService;
import com.gyzjc.subject.infra.basic.entity.SubjectCategory;
import com.gyzjc.subject.infra.basic.entity.SubjectLabel;
import com.gyzjc.subject.infra.basic.entity.SubjectMapping;
import com.gyzjc.subject.infra.basic.service.SubjectCategoryService;
import com.gyzjc.subject.infra.basic.service.SubjectLabelService;
import com.gyzjc.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {

    @Resource
    private SubjectLabelService subjectLabelService;
    @Resource
    private SubjectMappingService subjectMappingService;
    @Resource
    private SubjectCategoryService subjectCategoryService;

    @Override
    public Boolean add(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelDomainServiceImpl.add.bo:{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToLabel(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        int count = subjectLabelService.insert(subjectLabel);
        return count > 0;
    }

    @Override
    public Boolean update(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelDomainServiceImpl.update.bo:{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToLabel(subjectLabelBO);
        int count = subjectLabelService.update(subjectLabel);
        return count > 0;
    }

    @Override
    public Boolean delete(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToLabel(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        int count = subjectLabelService.update(subjectLabel);
        return count > 0;
    }

    @Override
    public List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO) {
        // 如果当前分类是1级标签,则查询所有标签
        SubjectCategory subjectCategory = subjectCategoryService.queryById(subjectLabelBO.getCategoryId());
        if (subjectCategory != null && CategoryTypeEnum.PRIMARY.getCode() == subjectCategory.getCategoryType()) {
            SubjectLabel subjectLabel = new SubjectLabel();
            subjectLabel.setCategoryId(subjectLabelBO.getCategoryId());
            subjectLabel.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
            List<SubjectLabel> subjectLabelList = subjectLabelService.queryByCondition(subjectLabel);
            return SubjectLabelConverter.INSTANCE.convertLabelToBOList(subjectLabelList);
        }

        Long categoryId = subjectLabelBO.getCategoryId();
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(categoryId);
        subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);
        if (CollectionUtils.isEmpty(mappingList)) {
            return Collections.emptyList();
        }
        List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> labelList = subjectLabelService.batchQueryById(labelIdList);
        List<SubjectLabelBO> boList = new ArrayList<>();
        labelList.forEach(lablel -> {
            SubjectLabelBO bo = new SubjectLabelBO();
            bo.setCategoryId(lablel.getId());
            bo.setLabelName(lablel.getLabelName());
            bo.setCategoryId(categoryId);
            bo.setSortNum(lablel.getSortNum());
            boList.add(bo);
        });
        return boList;
    }


    // @Override
    // public SubjectLabel queryById(Long id) {
    //     return null;
    // }
    //
    // @Override
    // public SubjectLabel insert(SubjectLabel subjectLabel) {
    //     return null;
    // }
    //
    // @Override
    // public SubjectLabel update(SubjectLabel subjectLabel) {
    //     return null;
    // }
    //
    // @Override
    // public boolean deleteById(Long id) {
    //     return false;
    // }

    // @Override
    // public void add(SubjectCategoryBO subjectCategoryBO) {
    //     if (log.isInfoEnabled()) {
    //         log.info("SubjectCategoryController.add.bo:{}", JSON.toJSONString(subjectCategoryBO));
    //     }
    //     SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
    //     subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
    //     subjectCategoryService.insert(subjectCategory);
    // }
    //
    // @Override
    // public List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO) {
    //     SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
    //     subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
    //     List<SubjectCategory> subjectCategoryList =  subjectCategoryService.queryCategory(subjectCategory);
    //     List<SubjectCategoryBO> boList = SubjectCategoryConverter.INSTANCE
    //             .convertBoToCategory(subjectCategoryList);
    //
    //     if (log.isInfoEnabled()) {
    //         log.info("SubjectCategoryController.queryPrimaryCategory.boList:{}",
    //                 JSON.toJSONString(boList));
    //     }
    //
    //     return boList;
    // }
    //
    // @Override
    // public Boolean update(SubjectCategoryBO subjectCategoryBO) {
    //     SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
    //     int count = subjectCategoryService.update(subjectCategory);
    //     return count > 0;
    // }
    //
    // @Override
    // public Boolean delete(SubjectCategoryBO subjectCategoryBO) {
    //     SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
    //     subjectCategory.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
    //     int count = subjectCategoryService.update(subjectCategory);
    //     return count > 0;
    // }


}
