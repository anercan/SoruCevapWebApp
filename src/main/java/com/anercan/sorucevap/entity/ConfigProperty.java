package com.anercan.sorucevap.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "PROPERTIES")
public class ConfigProperty extends BaseEntity {

    @Id
    String key;

    String value;

}
