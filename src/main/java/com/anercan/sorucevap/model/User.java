package com.anercan.sorucevap.model;

import lombok.Data;

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

    @Email(message = "Lütfen Mail Adresinizi Giriniz.")
    private String mail;

    @Pattern(regexp = "([a-zA-Z0-9]+\\S)",message = "Lütfen türkçe karakter kullanmayınız")
    @Size(min=5, max=15,message = "Kullanıcı adı 5-15 karakter uzunluğunda olmalıdır.")
    private String username;

    @Pattern(regexp = "([a-zA-Z0-9]+\\S)",message = "Lütfen uygun bir şifre giriniz." )
    @Size(min=8, max=30,message = "Lütfen en az 8 karakter uzunluğunda bir şifre giriniz.")
    private String password;

    private int question_status;

    private Date date;

    @OneToMany(mappedBy = "user")
    private List<Question> questionList;

}