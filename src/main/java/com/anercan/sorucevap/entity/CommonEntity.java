package com.anercan.sorucevap.entity;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class CommonEntity extends BaseEntity {

    boolean isActive = true;
}
