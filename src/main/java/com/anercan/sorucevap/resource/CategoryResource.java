package com.anercan.sorucevap.resource;

import lombok.Data;

@Data
public class CategoryResource {

    String categoryName;

    String numberOfQuestion;

    public CategoryResource (String categoryName , String numberOfQuestion){
        this.categoryName = categoryName;
        this.numberOfQuestion = numberOfQuestion;
    }
}
