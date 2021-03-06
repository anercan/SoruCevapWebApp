package com.anercan.sorucevap.controller;

import com.anercan.sorucevap.client.resource.JsonResponse;
import com.anercan.sorucevap.client.resource.QuestionResource;
import com.anercan.sorucevap.service.LoginService;
import com.anercan.sorucevap.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/question")
@RestController
public class QuestionController extends BaseController {

    @Autowired
    LoginService loginService;

    @Autowired
    QuestionService questionService;

    //todo verified olmayanalrın 1 ay sonra silinnmesi
    //todo cevaplar için pagenation
    @GetMapping("get-by-id/{id}")
    JsonResponse<QuestionResource> getByQuestionId(@PathVariable Long id) {
        return createJsonResponse(questionService.getByIdWithAnswers(id));
    }
}
