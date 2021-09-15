package com.misiontic.Tareas_MS.models;

import org.springframework.data.annotation.Id;

public class Category {
    @Id
    private String categoryId;
    private String userId;
    private String categoryName;
    private String categoryColor;

    public Category(String categoryId, String userId, String categoryName, String categoryColor) {
        this.categoryId = categoryId;
        this.userId = userId;
        this.categoryName = categoryName;
        this.categoryColor = categoryColor;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public  String getUserId() {
        return  userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryColor() {
        return categoryColor;
    }

    public void setCategoryColor(String categoryColor) {
        this.categoryColor = categoryColor;
    }
}
