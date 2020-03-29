package com.anercan.sorucevap.resource;

import lombok.Data;

import java.util.Date;

@Data
public class AnswerResource implements Comparable<AnswerResource> {

    private String content;

    private int likeCount;

    private int dislikeCount;

    private String userName;

    private boolean isVerified;

    private Date date;

    @Override
    public int compareTo(AnswerResource o) {
        if (getDate() == null || o.getDate() == null)
            return 0;
        return getDate().compareTo(o.getDate());
    }
}
