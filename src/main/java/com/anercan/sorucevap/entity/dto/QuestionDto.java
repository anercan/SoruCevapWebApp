package com.anercan.sorucevap.entity.dto;

import lombok.Data;

@Data
public class QuestionDto extends BaseDto {

    Long ownerId;
    String content;
    String title;

}
