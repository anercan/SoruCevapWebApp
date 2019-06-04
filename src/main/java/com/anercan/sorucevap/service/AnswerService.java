package com.anercan.sorucevap.service;

import com.anercan.sorucevap.entity.Answer;

import java.util.Optional;

public interface AnswerService {

     Optional<Answer> getById(Long id);

     Answer createAnswer(Answer answer);

     void deleteAnswer(Long id);

     Answer dislikeAnswer(Long id);

     Answer likeAnswer(Long id) ;

}
