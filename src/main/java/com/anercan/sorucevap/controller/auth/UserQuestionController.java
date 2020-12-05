package com.anercan.sorucevap.controller.auth;

import com.anercan.sorucevap.client.dto.QuestionDto;
import com.anercan.sorucevap.controller.BaseController;
import com.anercan.sorucevap.client.resource.JsonResponse;
import com.anercan.sorucevap.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user-question")
@RestController
public class UserQuestionController extends BaseController {

    @Autowired
    QuestionService questionService;

 /*   @GetMapping("/title/{title}")
    Optional<User> getByUsername(@PathVariable String userName){
        return userService.getByUserName(userName);
    }

    @GetMapping("/content/{content}")
    Optional<User> getByMail(@PathVariable String mail){
        return userService.getByMail(mail);
    }*/


    @PostMapping
    JsonResponse<Boolean> createQuestion(@RequestBody QuestionDto questionDto) {
        return createJsonResponse(questionService.createQuestion(questionDto));
    }

    @PostMapping("add-to-followList")
    JsonResponse<Boolean> addToFollowList(@RequestBody QuestionDto questionDto) {
        return createJsonResponse(questionService.addToFollowList(questionDto));
    }

    @PostMapping("delete")
    JsonResponse<Boolean> deleteQuestion(@RequestBody QuestionDto questionDto) {
        return createJsonResponse(questionService.deleteQuestion(questionDto));
    }
}
