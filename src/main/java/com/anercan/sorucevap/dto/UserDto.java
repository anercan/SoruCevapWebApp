package com.anercan.sorucevap.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class UserDto extends BaseDto {

    @NonNull
    private String mail;
    @NonNull
    private String password;
    @NonNull
    private String username;
}
