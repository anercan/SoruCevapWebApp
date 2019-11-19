package com.anercan.sorucevap.controller.auth;

import com.anercan.sorucevap.entity.JsonResponse;
import com.anercan.sorucevap.entity.Question;
import com.anercan.sorucevap.entity.dto.QuestionDto;
import com.anercan.sorucevap.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

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
    Optional<Question> getByUserId(@PathVariable Long id) {
        return questionService.getById(id);
    }

    @GetMapping("categories/{id}")
    Optional<Question> getByCategoryId(@PathVariable Long id) {
        return null;//questionService.getByCategory(id);
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
