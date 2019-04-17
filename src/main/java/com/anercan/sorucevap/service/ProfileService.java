package com.anercan.sorucevap.service;


import com.anercan.sorucevap.dao.QuestionRepository;
import com.anercan.sorucevap.dao.UserRepository;
import com.anercan.sorucevap.model.Question;
import com.anercan.sorucevap.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfileService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    public List<Question> getAnsweredQuestions(User user){
        List<Question> answeredQuestionList= new ArrayList<>();

        for (Question answeredQuestion : user.getQuestionList()) {
            if(answeredQuestion.getAnswerCount()>0)
                answeredQuestionList.add(answeredQuestion);
        }
        return answeredQuestionList;
    }

    public List<Question> getNotAnsweredQuestions(User user){
        List<Question> notAnsweredQuestionList= new ArrayList<>();

        for (Question nonAnsweredQuestion : user.getQuestionList()) {
            if(nonAnsweredQuestion.getAnswerCount()==0)
                notAnsweredQuestionList.add(nonAnsweredQuestion);
        }
        return notAnsweredQuestionList;
    }
}

