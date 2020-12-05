package com.anercan.sorucevap.client.mapper;

import com.anercan.sorucevap.entity.Answer;
import com.anercan.sorucevap.entity.Question;
import com.anercan.sorucevap.client.resource.AnswerResource;
import com.anercan.sorucevap.client.resource.QuestionResource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionMapper {

    public static QuestionResource mapQuestionWithAnswers(Question question) {
        QuestionResource questionResource = new QuestionResource();
        List<AnswerResource> answerResourceList = new ArrayList<>();
        questionResource.setTitle(question.getTitle());
        questionResource.setUserName(question.getUser().getUsername());
        questionResource.setContent(question.getContent());
        questionResource.setDate(question.getCreatedDate());
        for (Answer answer : question.getAnswer()) {
            AnswerResource answerResource = AnswerMapper.mapAnswer(answer);
            answerResourceList.add(answerResource);
        }
        Collections.sort(answerResourceList);
        List<AnswerResource> sortedList = answerResourceList.stream().sorted(Comparator.comparing(AnswerResource::isVerified)).collect(Collectors.toList());
        Collections.reverse(sortedList);
        questionResource.getAnswerResourceList().addAll(sortedList);
        return questionResource;
    }
}
