package com.anercan.sorucevap.client.dto;

import lombok.Data;

@Data
public class AnswerDto extends BaseDto {

    private QuestionDto questionDto;

    private UserDto userDto;

    private String content;

}
