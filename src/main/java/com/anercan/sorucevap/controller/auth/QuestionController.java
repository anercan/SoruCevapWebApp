package com.anercan.sorucevap.controller.auth;

import com.anercan.sorucevap.entity.Question;
import com.anercan.sorucevap.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    Optional<Question> getByUserId(@PathVariable Long id){
        return questionService.getById(id);
    }

    @PostMapping
    Question createQuestion(@RequestBody Question question){
        return questionService.createQuestion(question);
    }

    @DeleteMapping("id/{id}")
    void deleteQuestion(@PathVariable Long id){
        questionService.deleteQuestion(id);
    }
}
