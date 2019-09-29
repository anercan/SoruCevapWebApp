package com.anercan.sorucevap.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
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

    @Size(min=5, max=100,message = "Title 5-100 karakter uzunluğunda olmalıdır.")
    @NotNull
    private String title;

    @Size(min=5, max=250,message = "Content 5-100 karakter uzunluğunda olmalıdır.")
    @NotNull
    private String content;

    private int answerCount;

    private int likeCount;

    private int dislikeCount;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user = new User();

    @OneToMany(mappedBy = "question")
    private List<Answer> answer = new ArrayList<>();

    private Date date;

}
