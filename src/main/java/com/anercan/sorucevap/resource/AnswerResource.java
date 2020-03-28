package com.anercan.sorucevap.resource;

import lombok.Data;

@Data
public class AnswerResource {

    private String content;

    private int likeCount;

    private int dislikeCount;

    private String userName;

    private String userId;
}
