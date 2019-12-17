package com.anercan.sorucevap.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "ANSWERS")
public class Answer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AnswerSeq")
    @SequenceGenerator(name = "AnswerSeq", sequenceName = "ANSWER_SEQ")
    private Long id;

    @Size(min = 5, max = 250, message = "Must be 5-250 char")
    private String content;

    private int likeCount;

    private int dislikeCount;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private User user;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    private boolean verified = false;

    @Override
    public String toString() {
        return "Answer id =" + id;
    }
}