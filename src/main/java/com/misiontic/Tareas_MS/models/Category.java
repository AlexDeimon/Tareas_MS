package com.misiontic.Tareas_MS.models;

import org.springframework.data.annotation.Id;

public class Category {
    @Id
    private String id;
    private String categoryName;
    private String color;

    public Category(String id, String categoryName, String color) {
        this.id = id;
        this.categoryName = categoryName;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
