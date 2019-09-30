package com.anercan.sorucevap.entity.dto;

import lombok.Data;

@Data
public class AnswerDto extends BaseDto {

    private Long questionId;

    private Long userId;

    private String content;

}
