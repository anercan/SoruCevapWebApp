package com.anercan.sorucevap.entity.dto;

import lombok.Data;

@Data
public class UserDto extends BaseDto {

    private String mail;
    private String password;

}
