package com.anercan.sorucevap.controller.auth;

import com.anercan.sorucevap.client.dto.AnswerDto;
import com.anercan.sorucevap.controller.BaseController;
import com.anercan.sorucevap.client.resource.JsonResponse;
import com.anercan.sorucevap.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-answer")
public class UserAnswerController extends BaseController {

    @Autowired
    AnswerService answerService;

    @PostMapping
    JsonResponse<Boolean> createAnswer(@RequestBody AnswerDto answerDto) {
        return createJsonResponse(answerService.createAnswer(answerDto));
    }

    @PostMapping("/delete")
    void deleteAnswer(@RequestBody AnswerDto answerDto) {
        answerService.deleteAnswer(answerDto);
    }

    /*  @GetMapping("{id}/like")
      JsonResponse<Answer> likeAnswer(@PathVariable Long id){
          return answerService.likeAnswer(id);
      }

      @GetMapping("{id}/dislike")
      JsonResponse<Answer> dislikeAnswer(@PathVariable Long id){
          return answerService.dislikeAnswer(id);
      }
  */
}
