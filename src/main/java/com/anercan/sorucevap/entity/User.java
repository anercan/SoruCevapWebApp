package com.anercan.sorucevap.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
@Data
public class User extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    @Email
    private String mail;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "([a-zA-Z0-9]+\\S)")
    @Size(min = 5, max = 15)
    private String username;

    @JsonIgnore
    @Pattern(regexp = "([a-zA-Z0-9]+\\S)")
    @Size(min = 8, max = 30)
    private String password;

    //todo roles

    private int questionStatus;

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