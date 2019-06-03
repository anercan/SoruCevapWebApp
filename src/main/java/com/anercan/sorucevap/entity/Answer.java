package com.anercan.sorucevap.entity;


import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "ANSWER_DB")
public class Answer {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="AnswerSeq")
    @SequenceGenerator(name="AnswerSeq",sequenceName="ANSWER_SEQ")
    private String id;

    private String content;

    private int likeCount;

    private int dislikeCount;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="QUESTION_ID")
    private Question question;

    private Date date;
}