package com.anercan.sorucevap.entity.dto;

import lombok.Data;

@Data
public class QuestionDto extends BaseDto {

    UserDto userDto;
    String content;
    String title;

}
