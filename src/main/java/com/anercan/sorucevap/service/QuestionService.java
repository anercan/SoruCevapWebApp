package com.anercan.sorucevap.service;

import com.anercan.sorucevap.entity.Question;


import java.util.Optional;

public interface QuestionService {

    Optional<Question> getById(Long id);

    Question createQuestion(Question question);

    void deleteQuestion(Long id);

}
