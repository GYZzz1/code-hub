package com.gyzjc.subject.application.controller;

import com.gyzjc.subject.infra.basic.dao.SubjectCategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 刷题controller
 *
 * @author gyz
 * @since 2024/06/15
 */
@RestController
public class SubjectController {

    @Autowired
    private SubjectCategoryDao subjectCategoryDao;
}
