package com.gyzjc.subject.domain.handler.subject;

import com.google.common.base.Preconditions;
import com.gyzjc.subject.common.enums.SubjectInfoTypeEnum;
import com.gyzjc.subject.domain.convert.RadioSubjectConverter;
import com.gyzjc.subject.domain.entity.SubjectInfoBO;
import com.gyzjc.subject.infra.basic.entity.SubjectRadio;
import com.gyzjc.subject.infra.basic.service.SubjectRadioService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 单选题目的策略类
 */
@Component
public class RadioTypeHandler implements SubjectTypeHandler {

    @Resource
    private SubjectRadioService subjectRadioService;
    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.RADIO;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        // 单选题目的插入
        List<SubjectRadio> subjectRadioList = new LinkedList<>();
        Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoBO.getOptionList()));

        subjectInfoBO.getOptionList().forEach(option -> {
            SubjectRadio subjectRadio = RadioSubjectConverter.INSTANCE.convertBoToEntity(option);
            subjectRadio.setSubjectId(subjectInfoBO.getId());
            subjectRadioList.add(subjectRadio);
        });

        subjectRadioService.batchInsert(subjectRadioList);
    }
}
