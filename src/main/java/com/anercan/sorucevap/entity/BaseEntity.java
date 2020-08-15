package com.anercan.sorucevap.entity;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity {

    private Date created_date;
    private Date modified_date;

}
