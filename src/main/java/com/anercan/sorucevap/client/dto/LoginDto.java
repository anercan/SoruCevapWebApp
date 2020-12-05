package com.anercan.sorucevap.client.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class LoginDto {

    @Pattern(regexp = "([a-zA-Z0-9]+\\S)")
    String username;

    @Pattern(regexp = "([a-zA-Z0-9]+\\S)")
    String password;
}
