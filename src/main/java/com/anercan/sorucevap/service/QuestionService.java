package com.anercan.sorucevap.service;

import com.anercan.sorucevap.entity.JsonResponse;
import com.anercan.sorucevap.entity.Question;
import com.anercan.sorucevap.entity.dto.QuestionDto;


import java.util.List;
import java.util.Optional;

public interface QuestionService {

    Optional<Question> getById(Long id);

    JsonResponse<List<Question>> getQuestionsByCategoryId(Long id);

    JsonResponse<Boolean> createQuestion(QuestionDto questionDto);

    JsonResponse<Boolean> deleteQuestion(QuestionDto questionDto);

    JsonResponse<Boolean> addToFollowList(QuestionDto questionDto);


}
