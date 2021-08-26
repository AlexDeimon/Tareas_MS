package com.misiontic.Tareas_MS.models;

import org.springframework.data.annotation.Id;

public class Category {
    @Id
    private String categoryId;
    private String categoryName;
    private String color;
    private String userId;

    public Category(String categoryId, String categoryName, String color, String userId) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.color = color;
        this.userId = userId;
    }

    public String getCategoryId() { return categoryId; }

    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }

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

    public  String getUserId() { return  userId; }

    public void setUserId(String userId) { this.userId = userId; }
}
