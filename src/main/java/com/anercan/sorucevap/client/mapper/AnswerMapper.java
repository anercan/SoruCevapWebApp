package com.anercan.sorucevap.client.mapper;

import com.anercan.sorucevap.entity.Answer;
import com.anercan.sorucevap.client.resource.AnswerResource;

public class AnswerMapper {

    public static AnswerResource mapAnswer(Answer answer) {
        AnswerResource answerResource = new AnswerResource();

        answerResource.setContent(answer.getContent());
        answerResource.setDislikeCount(answer.getDislikeCount());
        answerResource.setLikeCount(answer.getLikeCount());
        answerResource.setUserName(answer.getUser().getUsername());
        answerResource.setVerified(answer.isVerified());
        answerResource.setDate(answer.getCreated_date());

        return answerResource;
    }
}
