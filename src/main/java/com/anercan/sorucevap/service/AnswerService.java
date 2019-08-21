package com.anercan.sorucevap.service;

import com.anercan.sorucevap.entity.Answer;
import com.anercan.sorucevap.entity.JsonResponse;

import java.util.List;
import java.util.Optional;

public interface AnswerService {

     JsonResponse<List<Answer>> getAnswersByQuestionId(Long id);

     JsonResponse<Optional<Answer>> getById(Long id);

     JsonResponse<Answer> createAnswer(Answer answer);

     void deleteAnswer(Long id);

     JsonResponse<Answer> dislikeAnswer(Long id);

     JsonResponse<Answer> likeAnswer(Long id) ;

}
