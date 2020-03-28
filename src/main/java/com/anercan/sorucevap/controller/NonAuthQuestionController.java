package com.anercan.sorucevap.controller;

import com.anercan.sorucevap.resource.JsonResponse;
import com.anercan.sorucevap.resource.QuestionResource;
import com.anercan.sorucevap.service.QuestionService;
import com.anercan.sorucevap.service.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/question")
@RestController
public class NonAuthQuestionController {

    @Autowired
    LoginService loginService;

    @Autowired
    QuestionService questionService;

    @GetMapping("id/{id}")
    JsonResponse<QuestionResource> getByUserId(@PathVariable Long id) {
        return questionService.getByIdWithAnswers(id);
    }
}
