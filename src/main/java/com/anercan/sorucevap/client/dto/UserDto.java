package com.anercan.sorucevap.client.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class UserDto extends BaseDto {

    @NonNull
    private String userNameOrMail;
    @NonNull
    private String password;

}
