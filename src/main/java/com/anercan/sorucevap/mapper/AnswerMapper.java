package com.anercan.sorucevap.mapper;

import com.anercan.sorucevap.entity.Answer;
import com.anercan.sorucevap.resource.AnswerResource;

public class AnswerMapper {

    public static AnswerResource mapAnswer(Answer answer){
        AnswerResource answerResource = new AnswerResource();

        answerResource.setContent(answer.getContent());
        answerResource.setDislikeCount(answer.getDislikeCount());
        answerResource.setLikeCount(answer.getLikeCount());
        answerResource.setUserName(answer.getUser().getUsername());
        answerResource.setVerified(answer.isVerified());
        answerResource.setDate(answer.getDate());

        return answerResource;
    }
}
