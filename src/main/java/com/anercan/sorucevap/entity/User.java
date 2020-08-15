package com.anercan.sorucevap.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
@Data
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Email(message = "Invalid Mail")
    private String mail;

    @Pattern(regexp = "([a-zA-Z0-9]+\\S)", message = "Invalid UserName")
    @Size(min = 5, max = 15, message = "Invalid UserName Size")
    private String username;

    @JsonIgnore
    @Pattern(regexp = "([a-zA-Z0-9]+\\S)", message = "Invalid pwd")
    @Size(min = 8, max = 30, message = "Invalid pwd size")
    private String password;

    private int questionStatus;

    private boolean isActive = true;

    @JsonBackReference
    @OneToMany(mappedBy = "user")
    private List<Question> questionList = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "user")
    private List<Answer> answerList;

    @JsonBackReference
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Question_Follower",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "question_id")}
    )
    private List<Question> questionFollow = new ArrayList<>();

    @Override
    public String toString() {
        return "User id =" + id;

    }
    //todo private List<User> followingsList;

    //todo private List<User> followersList;


}