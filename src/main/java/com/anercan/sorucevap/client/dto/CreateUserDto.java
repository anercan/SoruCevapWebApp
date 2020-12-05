package com.anercan.sorucevap.client.dto;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;

@Data
public class CreateUserDto {

    @NonNull
    private String userName;
    @Email
    private String mail;
    @NonNull
    private String password;

}
