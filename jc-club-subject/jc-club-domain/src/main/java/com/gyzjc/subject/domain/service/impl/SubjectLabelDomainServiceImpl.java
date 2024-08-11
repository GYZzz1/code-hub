package com.gyzjc.subject.domain.service.impl;

import com.gyzjc.subject.infra.basic.service.SubjectLabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class SubjectLabelDomainServiceImpl implements SubjectLabelService {

    @Resource // TODO: 为什么AutoWired不行
    private SubjectLabelService subjectCategoryService;



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
