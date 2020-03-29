package com.anercan.sorucevap.mapper;

import com.anercan.sorucevap.entity.Answer;
import com.anercan.sorucevap.entity.Question;
import com.anercan.sorucevap.resource.AnswerResource;
import com.anercan.sorucevap.resource.QuestionResource;
import org.hibernate.mapping.Collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionMapper {

    public static QuestionResource mapQuestionWithAnswers(Question question){
        QuestionResource questionResource = new QuestionResource();
        List<AnswerResource> answerResourceList = new ArrayList<>();
        questionResource.setTitle(question.getTitle());
        questionResource.setContent(question.getContent());
        questionResource.setDate(question.getDate());
        for(Answer answer:question.getAnswer()){
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
