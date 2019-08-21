package com.anercan.sorucevap.controller.auth;

import com.anercan.sorucevap.entity.Answer;
import com.anercan.sorucevap.entity.JsonResponse;
import com.anercan.sorucevap.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth/answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @GetMapping("id/{id}")
    JsonResponse<Optional<Answer>> getByUserId(@PathVariable Long id){
        return answerService.getById(id);
    }

    @PostMapping
    JsonResponse<Answer> createAnswer(@RequestBody Answer answer){
        return answerService.createAnswer(answer);
    }

    @DeleteMapping("id/{id}")
    void deleteAnswer(@PathVariable Long id){
        answerService.deleteAnswer(id);
    }

    @GetMapping("{id}/like")
    JsonResponse<Answer> likeAnswer(@PathVariable Long id){
        return answerService.likeAnswer(id);
    }

    @GetMapping("{id}/dislike")
    JsonResponse<Answer> dislikeAnswer(@PathVariable Long id){
        return answerService.dislikeAnswer(id);
    }

    @GetMapping("question/{id}")
    JsonResponse<List<Answer>> getAnswersByQuestionId(@PathVariable Long id){
        return answerService.getAnswersByQuestionId(id);
    }
}
