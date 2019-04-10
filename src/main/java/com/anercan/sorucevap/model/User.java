package com.anercan.sorucevap.model;

import lombok.Data;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
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
    @Column(name="mail")
    private String mail;

    @Pattern(regexp = "([a-zA-Z0-9]+\\S)",message = "Lütfen türkçe karakter kullanmayınız")
    @Size(min=5, max=15,message = "Lütfen 5-15 karakter uzunluğunda kullanıcı adı belirleyiniz.")
    @Column(name="username")
    private String username;

    @Pattern(regexp = "([a-zA-Z0-9]+\\S)",message = "Lütfen uygun bir şifre belirleyiniz." )
    @Size(min=8, max=30,message = "Lütfen en az 8 karakter uzunluğunda bir şifre belirleyiniz.")
    @Column(name="password")
    private String password;

    @Column(name="question_status")
    private int question_status;

    @Column(name="date")
    private Date date;

}