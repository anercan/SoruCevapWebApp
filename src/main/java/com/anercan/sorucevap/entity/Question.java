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
@Table(name = "Question_db")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QuestionSeq")
    @SequenceGenerator(name = "QuestionSeq", sequenceName = "QUESTION_SEQ")
    private Long id;

    @Size(min = 5, max = 100, message = "Title size limit does not reach requirements")
    @NotNull
    private String title;

    @Size(min = 5, max = 250, message = "Content size limit does not reach requirements")
    @NotNull
    private String content;

    private int likeCount;

    private int dislikeCount;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToMany(mappedBy = "questionFollow")
    private List<User> usersWhoFollow;

    @JsonManagedReference
    @OneToMany(mappedBy = "question")
    private List<Answer> answer = new ArrayList<>();

    private Date date;

    @Override
    public String toString() {
        return "Question id =" + id;
    }
}
