package com.anercan.sorucevap.resource;

import lombok.Data;

@Data
public class CategoryResource {

    String categoryName;

    int numberOfQuestion;

    public CategoryResource(String categoryName, int numberOfQuestion) {
        this.categoryName = categoryName;
        this.numberOfQuestion = numberOfQuestion;
    }
}
