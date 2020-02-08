package com.anercan.sorucevap.service;

import com.anercan.sorucevap.dto.AnswerDto;
import com.anercan.sorucevap.entity.Answer;
import com.anercan.sorucevap.resource.JsonResponse;

import java.util.List;

public interface AnswerService {

    JsonResponse<List<Answer>> getAnswersByQuestionId(Long id);

    JsonResponse<Answer> getById(Long id);

    JsonResponse<Boolean> createAnswer(AnswerDto answerDto);

    JsonResponse<Boolean> deleteAnswer(AnswerDto answerDto);

    JsonResponse<Boolean> dislikeAnswer(Long id);

    JsonResponse<Boolean> likeAnswer(Long id);

}
