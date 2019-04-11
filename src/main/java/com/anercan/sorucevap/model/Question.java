package com.anercan.sorucevap.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="Question_db")
public class Question {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="QuestionSeq")
    @SequenceGenerator(name="QuestionSeq",sequenceName="QUESTION_SEQ")
    private Long id;

    private String title;

    private String content;

    private int answerCount;

    private int likeCount;

    private int dislikeCount;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="OWNER_ID")
    private User user;

    private Date date;

}
