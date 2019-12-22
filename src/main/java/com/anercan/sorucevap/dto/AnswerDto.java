package com.anercan.sorucevap.dto;

import lombok.Data;

@Data
public class AnswerDto extends BaseDto {

    private QuestionDto questionDto;

    private UserDto userDto;

    private String content;

}
