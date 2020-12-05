package com.anercan.sorucevap.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "CLIENT_TEXTS")
public class ClientProperty extends BaseEntity {

    @Id
    String key;

    String trValue;

    String engValue;

}
