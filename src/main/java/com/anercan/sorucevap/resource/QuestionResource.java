package com.anercan.sorucevap.resource;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class QuestionResource {

    String title;
    String content;
    String userName;
    List<AnswerResource> answerResourceList = new ArrayList<>();
    Date date;
}
