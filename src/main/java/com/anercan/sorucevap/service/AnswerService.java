package com.anercan.sorucevap.service;

import com.anercan.sorucevap.entity.Answer;
import com.anercan.sorucevap.entity.JsonResponse;
import com.anercan.sorucevap.entity.dto.AnswerDto;

import java.util.List;
import java.util.Optional;

public interface AnswerService {

     JsonResponse<List<Answer>> getAnswersByQuestionId(Long id);

     JsonResponse<Optional<Answer>> getById(Long id);

     JsonResponse<Boolean> createAnswer(AnswerDto answerDto);

     JsonResponse<Boolean> deleteAnswer(AnswerDto answerDto);

     JsonResponse<Boolean> dislikeAnswer(Long id);

     JsonResponse<Boolean> likeAnswer(Long id) ;

}
