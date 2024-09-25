package com.gyzjc.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.gyzjc.subject.common.enums.IsDeletedFlagEnum;
import com.gyzjc.subject.domain.convert.SubjectCategoryConverter;
import com.gyzjc.subject.domain.entity.SubjectCategoryBO;
import com.gyzjc.subject.domain.entity.SubjectLabelBO;
import com.gyzjc.subject.domain.service.SubjectCategoryDomainService;
import com.gyzjc.subject.domain.util.CacheUtil;
import com.gyzjc.subject.infra.basic.entity.SubjectCategory;
import com.gyzjc.subject.infra.basic.entity.SubjectLabel;
import com.gyzjc.subject.infra.basic.entity.SubjectMapping;
import com.gyzjc.subject.infra.basic.service.SubjectCategoryService;
import com.gyzjc.subject.infra.basic.service.SubjectLabelService;
import com.gyzjc.subject.infra.basic.service.SubjectMappingService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {

    @Autowired // TODO: 为什么AutoWired不行
    private SubjectCategoryService subjectCategoryService;
    @Resource
    private SubjectMappingService subjectMappingService;
    @Resource
    private SubjectLabelService subjectLabelService;
    @Resource
    private ThreadPoolExecutor labelThreadPool;
    @Resource
    private CacheUtil cacheUtil;

    @Override
    public void add(SubjectCategoryBO subjectCategoryBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryDomainServiceImpl.add.bo:{}", JSON.toJSONString(subjectCategoryBO));
        }
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        subjectCategoryService.insert(subjectCategory);
    }

    @Override
    public List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);
        List<SubjectCategoryBO> boList = SubjectCategoryConverter.INSTANCE
                .convertBoToCategory(subjectCategoryList);

        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryDomainServiceImpl.queryPrimaryCategory.boList:{}",
                    JSON.toJSONString(boList));
        }

        boList.forEach(bo -> {
            Integer subjectCount = subjectCategoryService.querySubjectCount(bo.getId());
            bo.setCount(subjectCount);
        });

        return boList;
    }

    @Override
    public Boolean update(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        int count = subjectCategoryService.update(subjectCategory);
        return count > 0;
    }

    @Override
    public Boolean delete(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        int count = subjectCategoryService.update(subjectCategory);
        return count > 0;
    }

    @Override
    @SneakyThrows
    public List<SubjectCategoryBO> queryCategoryAndLabel(SubjectCategoryBO subjectCategoryBO) {
        Long id = subjectCategoryBO.getId();
        String cacheKey = "categoryAndLabel." + id;
        return (List<SubjectCategoryBO>) cacheUtil.getResult(cacheKey,
                SubjectCategoryBO.class, s -> getSubjectCategoryBOS(id));
    }

    private List<SubjectCategoryBO> getSubjectCategoryBOS(Long categoryId) {
        // 查询当前大分类下所有小分类
        SubjectCategory subjectCategory = new SubjectCategory();
        subjectCategory.setParentId(categoryId);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);

        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryDomainServiceImpl.queryCategoryAndLabel.subjectCategoryList:{}",
                    JSON.toJSONString(subjectCategoryList));
        }
        List<SubjectCategoryBO> categoryBOList = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryList);
        Map<Long, List<SubjectLabelBO>> map = new HashMap<>();
        List<CompletableFuture<Map<Long, List<SubjectLabelBO>>>> completableFutureList = categoryBOList.stream().map(category ->
                CompletableFuture.supplyAsync(() -> getLabelBOList(category), labelThreadPool)
        ).collect(Collectors.toList());
        completableFutureList.forEach(future -> {
            try {
                Map<Long, List<SubjectLabelBO>> resultMap = future.get();
                if (!CollectionUtils.isEmpty(resultMap)) {
                    map.putAll(resultMap);
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        /*
        // 一次获取标签信息
        List<FutureTask<Map<Long, List<SubjectLabelBO>>>> futureTaskList = new ArrayList<>();

        categoryBOList.forEach(category -> {
            FutureTask<Map<Long, List<SubjectLabelBO>>> futureTask = new FutureTask<>(() -> getLabelBOList(category));
            futureTaskList.add(futureTask);
            labelThreadPool.submit(futureTask);
        });
        for (FutureTask<Map<Long, List<SubjectLabelBO>>> futureTask : futureTaskList) {
            Map<Long, List<SubjectLabelBO>> resultMap = futureTask.get();
            if (CollectionUtils.isEmpty(resultMap)) {
                continue;
            }
            map.putAll(resultMap);
        }*/

        categoryBOList.forEach(categoryBO -> {
            categoryBO.setLabelBOList(map.get(categoryBO.getId()));
        });

        return categoryBOList;
    }

    private Map<Long, List<SubjectLabelBO>> getLabelBOList(SubjectCategoryBO category) {
        Map<Long, List<SubjectLabelBO>> labelMap = new HashMap<>();

        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(category.getId());
        List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);
        if (CollectionUtils.isEmpty(mappingList)) {
            return null;
        }
        List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> labelList = subjectLabelService.batchQueryById(labelIdList);

        List<SubjectLabelBO> labelBOList = new ArrayList<>();
        labelList.forEach(label -> {
            SubjectLabelBO subjectLabelBO = new SubjectLabelBO();
            subjectLabelBO.setId(label.getId());
            subjectLabelBO.setLabelName(label.getLabelName());
            subjectLabelBO.setCategoryId(label.getCategoryId());
            subjectLabelBO.setSortNum(label.getSortNum());
            labelBOList.add(subjectLabelBO);
        });

        labelMap.put(category.getId(), labelBOList);
        return labelMap;
    }


}
