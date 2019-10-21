package com.anercan.sorucevap.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="USER_DB")
@Data
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="userSeq")
    @SequenceGenerator(name="userSeq",sequenceName="USER_SEQUENCE")
    private Long id;

    @Email(message = "Invalid Mail")
    private String mail;

    @Pattern(regexp = "([a-zA-Z0-9]+\\S)",message = "Invalid UserName")
    @Size(min=5, max=15,message = "Kullanıcı adı 5-15 karakter uzunluğunda olmalıdır.")
    private String username;

    @Pattern(regexp = "([a-zA-Z0-9]+\\S)",message = "Invalid pwd" )
    @Size(min=8, max=30,message = "Invalid pwd")
    private String password;

    private int questionStatus;

    private Date date;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Question> questionList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Answer> answerList;

    //private List<User> followingsList;

    //private List<User> followersList;
}