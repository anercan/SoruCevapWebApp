package com.anercan.sorucevap.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="Question_db")
public class Question {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="QuestionSeq")
    @SequenceGenerator(name="QuestionSeq",sequenceName="QUESTION_SEQ")
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    private int answerCount;

    private int likeCount;

    private int dislikeCount;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    @OneToMany(mappedBy = "question")
    private List<Answer> answer;

    private Date date;

}
