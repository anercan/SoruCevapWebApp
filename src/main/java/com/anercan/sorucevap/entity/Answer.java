package com.anercan.sorucevap.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Table(name = "ANSWER_DB")
public class Answer {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="AnswerSeq")
    @SequenceGenerator(name="AnswerSeq",sequenceName="ANSWER_SEQ")
    private Long id;

    @Size(min=5, max=250,message = "Content 5-100 karakter uzunluğunda olmalıdır.")
    private String content;

    private int likeCount;

    private int dislikeCount;

    private Long ownerId; // todo ForeignKey?

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="QUESTION_ID")
    private Question question;

    private boolean verified=false;

    private Date date;
}