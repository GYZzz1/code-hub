package com.gyzjc.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.gyzjc.subject.common.enums.IsDeletedFlagEnum;
import com.gyzjc.subject.domain.convert.SubjectCategoryConverter;
import com.gyzjc.subject.domain.convert.SubjectInfoConverter;
import com.gyzjc.subject.domain.entity.SubjectCategoryBO;
import com.gyzjc.subject.domain.entity.SubjectInfoBO;
import com.gyzjc.subject.domain.handler.subject.SubjectTypeHandler;
import com.gyzjc.subject.domain.handler.subject.SubjectTypeHandlerFactory;
import com.gyzjc.subject.domain.service.SubjectCategoryDomainService;
import com.gyzjc.subject.domain.service.SubjectInfoDomainService;
import com.gyzjc.subject.infra.basic.entity.SubjectCategory;
import com.gyzjc.subject.infra.basic.entity.SubjectInfo;
import com.gyzjc.subject.infra.basic.entity.SubjectMapping;
import com.gyzjc.subject.infra.basic.service.SubjectCategoryService;
import com.gyzjc.subject.infra.basic.service.SubjectInfoService;
import com.gyzjc.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {

    @Autowired
    private SubjectInfoService subjectInfoService;
    @Autowired
    private SubjectMappingService subjectMappingService;
    @Autowired
    private SubjectTypeHandlerFactory subjectTypeHandlerFactory;

    @Override
    @Transactional
    public Boolean add(SubjectInfoBO subjectInfoBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectInfoDomainServiceImpl.add.bo:{}", JSON.toJSONString(subjectInfoBO));
        }

        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToInfo(subjectInfoBO);
        subjectInfoService.insert(subjectInfo);
        // 工厂+策略
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
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
                subjectMappingList.add(subjectMapping);
            });
        });
        subjectMappingService.batchInsert(subjectMappingList);

        return true;
    }

}
