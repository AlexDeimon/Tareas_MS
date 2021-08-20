package com.misiontic.Tareas_MS.models;

import org.springframework.data.annotation.Id;

public class Category {
    @Id
    private String categoryName;
    private String color;


    public Category(String categoryName, String color) {
        this.categoryName = categoryName;
        this.color = color;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
