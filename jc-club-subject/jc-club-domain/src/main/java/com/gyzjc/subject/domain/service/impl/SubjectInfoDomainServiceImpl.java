package com.gyzjc.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.gyzjc.subject.common.entity.PageResult;
import com.gyzjc.subject.common.enums.IsDeletedFlagEnum;
import com.gyzjc.subject.domain.convert.SubjectCategoryConverter;
import com.gyzjc.subject.domain.convert.SubjectInfoConverter;
import com.gyzjc.subject.domain.entity.SubjectCategoryBO;
import com.gyzjc.subject.domain.entity.SubjectInfoBO;
import com.gyzjc.subject.domain.entity.SubjectOptionBO;
import com.gyzjc.subject.domain.handler.subject.SubjectTypeHandler;
import com.gyzjc.subject.domain.handler.subject.SubjectTypeHandlerFactory;
import com.gyzjc.subject.domain.service.SubjectCategoryDomainService;
import com.gyzjc.subject.domain.service.SubjectInfoDomainService;
import com.gyzjc.subject.infra.basic.entity.SubjectCategory;
import com.gyzjc.subject.infra.basic.entity.SubjectInfo;
import com.gyzjc.subject.infra.basic.entity.SubjectLabel;
import com.gyzjc.subject.infra.basic.entity.SubjectMapping;
import com.gyzjc.subject.infra.basic.service.SubjectCategoryService;
import com.gyzjc.subject.infra.basic.service.SubjectInfoService;
import com.gyzjc.subject.infra.basic.service.SubjectLabelService;
import com.gyzjc.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {

    @Autowired
    private SubjectInfoService subjectInfoService;
    @Autowired
    private SubjectMappingService subjectMappingService;
    @Autowired
    private SubjectLabelService subjectLabelService;
    @Autowired
    private SubjectTypeHandlerFactory subjectTypeHandlerFactory;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(SubjectInfoBO subjectInfoBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectInfoDomainServiceImpl.add.bo:{}", JSON.toJSONString(subjectInfoBO));
        }

        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToInfo(subjectInfoBO);
        subjectInfo.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        subjectInfoService.insert(subjectInfo);
        // 工厂+策略
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        subjectInfoBO.setId(subjectInfo.getId());
        handler.add(subjectInfoBO);

        List<Integer> categoryIds = subjectInfoBO.getCategoryIds();
        List<Integer> labelIds = subjectInfoBO.getLabelIds();
        List<SubjectMapping> subjectMappingList = new LinkedList<>();
        categoryIds.forEach(categoryId -> {
            labelIds.forEach(labelId -> {
                SubjectMapping subjectMapping = new SubjectMapping();
                subjectMapping.setSubjectId(subjectInfo.getId());
                subjectMapping.setCategoryId(Long.valueOf(categoryId));
                subjectMapping.setLabelId(Long.valueOf(labelId));
                subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());

                subjectMappingList.add(subjectMapping);
            });
        });
        subjectMappingService.batchInsert(subjectMappingList);

        return true;
    }

    @Override
    public PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO) {
        PageResult<SubjectInfoBO> pageResult = new PageResult<>();
        pageResult.setPageNo(subjectInfoBO.getPageNo());
        pageResult.setPageSize(subjectInfoBO.getPageSize());
        int start = (subjectInfoBO.getPageNo() - 1) * subjectInfoBO.getPageSize();
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToInfo(subjectInfoBO);
        int count = subjectInfoService.countByCondition(subjectInfo, subjectInfoBO.getCategoryId(),
                subjectInfoBO.getLabelId());

        if (count == 0) {
            return pageResult;
        }

        List<SubjectInfo> subjectInfoList = subjectInfoService.queryPage(subjectInfo, subjectInfoBO.getCategoryId(),
                subjectInfoBO.getLabelId(), start, subjectInfoBO.getPageSize());
        List<SubjectInfoBO> subjectInfoBOS = SubjectInfoConverter.INSTANCE.convertBoToCategory(subjectInfoList);

        pageResult.setRecords(subjectInfoBOS);
        pageResult.setTotal(count);
        return pageResult;
    }

    @Override
    public SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO) {
        SubjectInfo subjectInfo = subjectInfoService.queryById(subjectInfoBO.getId());
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        SubjectOptionBO optionBO = handler.query(subjectInfo.getId().intValue());
        SubjectInfoBO bo = SubjectInfoConverter.INSTANCE.convertOptionAndInfoToBO(optionBO, subjectInfo);

        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setSubjectId(subjectInfo.getId());
        subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        List<SubjectMapping> subjectMappingList = subjectMappingService.queryLabelId(subjectMapping);
        List<Long> labelIds = subjectMappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> labelList = subjectLabelService.batchQueryById(labelIds);
        List<String> labelNameList = CollectionUtils.isEmpty(labelList) ? null : labelList.stream().map(SubjectLabel::getLabelName).collect(Collectors.toList());
        bo.setLabelName(labelNameList);

        return bo;
    }

}
