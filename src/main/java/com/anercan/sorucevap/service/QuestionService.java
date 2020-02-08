package com.anercan.sorucevap.service;

import com.anercan.sorucevap.dto.QuestionDto;
import com.anercan.sorucevap.entity.Question;
import com.anercan.sorucevap.resource.JsonResponse;

import java.util.List;

public interface QuestionService {

    JsonResponse<Question> getById(Long id);

    JsonResponse<List<Question>> getQuestionsByCategoryId(Long id);

    JsonResponse<Boolean> createQuestion(QuestionDto questionDto);

    JsonResponse<Boolean> deleteQuestion(QuestionDto questionDto);

    JsonResponse<Boolean> addToFollowList(QuestionDto questionDto);


}
