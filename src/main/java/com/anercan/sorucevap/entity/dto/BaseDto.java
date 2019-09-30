package com.anercan.sorucevap.entity.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BaseDto {

    Date createDate =new Date();
    Long id;

}
