package com.anercan.sorucevap.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "CATEGORIES")
public class Category {

    @Id
    private Long id;

    private String name;


}
