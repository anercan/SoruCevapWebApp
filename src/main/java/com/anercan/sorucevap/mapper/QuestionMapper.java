package com.anercan.sorucevap.mapper;

import com.anercan.sorucevap.entity.Answer;
import com.anercan.sorucevap.entity.Question;
import com.anercan.sorucevap.resource.AnswerResource;
import com.anercan.sorucevap.resource.QuestionResource;

public class QuestionMapper {

    public static QuestionResource mapQuestionWithAnswers(Question question){
        QuestionResource questionResource = new QuestionResource();
        questionResource.setTitle(question.getTitle());
        questionResource.setContent(question.getContent());
        for(Answer answer:question.getAnswer()){
            AnswerResource answerResource = AnswerMapper.mapAnswer(answer);
            questionResource.getAnswerResourceList().add(answerResource);
        }
        return questionResource;
    }
}
