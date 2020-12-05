package com.anercan.sorucevap.entity;

import com.anercan.sorucevap.enums.QuestionStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "QUESTIONS")
public class Question extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QuestionSeq")
    @SequenceGenerator(name = "QuestionSeq", sequenceName = "QUESTION_SEQ")
    private Long id;

    @Size(min = 5, max = 100)
    @NotNull
    private String title;

    @Size(min = 5, max = 250)
    @NotNull
    private String content;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    private QuestionStatus status;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Category_Relations",
            joinColumns = {@JoinColumn(name = "question_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<Category> categories;

    @JsonManagedReference
    @ManyToMany(mappedBy = "questionFollow")
    private List<User> usersWhoFollow;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private List<Answer> answer = new ArrayList<>();

    //private Reports reports //todo report question // statics ?

    @Override
    public String toString() {
        return "Question id =" + id;
    }
}
