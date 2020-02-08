package com.anercan.sorucevap.controller.auth;

import com.anercan.sorucevap.dto.QuestionDto;
import com.anercan.sorucevap.resource.JsonResponse;
import com.anercan.sorucevap.entity.Question;
import com.anercan.sorucevap.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/auth/question")
@RestController
public class QuestionController {

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

    @GetMapping("id/{id}")
    JsonResponse<Question> getByUserId(@PathVariable Long id) {
        return questionService.getById(id);
    }

    @GetMapping("categories/{id}")
    JsonResponse<List<Question>> getByCategoryId(@PathVariable Long id) {
        return questionService.getQuestionsByCategoryId(id);
    }

    @PostMapping
    JsonResponse<Boolean> createQuestion(@RequestBody QuestionDto questionDto) {
        return questionService.createQuestion(questionDto);
    }

    @PostMapping("add-to-followList")
    JsonResponse<Boolean> addToFollowList(@RequestBody QuestionDto questionDto) {
        return questionService.addToFollowList(questionDto);
    }

    @PostMapping("delete")
    JsonResponse<Boolean> deleteQuestion(@RequestBody QuestionDto questionDto) {
        return questionService.deleteQuestion(questionDto);
    }
}
