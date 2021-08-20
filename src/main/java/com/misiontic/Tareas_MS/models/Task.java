package com.misiontic.Tareas_MS.models;

import org.springframework.data.annotation.Id;
import java.util.Date;

public class Task {
    @Id
    private String id;
    private String titleTask;
    private String categoryTask;
    private String description;
    private Date finalDate;
    private String status;
    private String userId;

    public Task(String id, String titleTask, String categoryTask, String description, Date finalDate, String status, String userId) {
        this.id = id;
        this.titleTask = titleTask;
        this.categoryTask = categoryTask;
        this.description = description;
        this.finalDate = finalDate;
        this.status = status;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitleTask() {
        return titleTask;
    }

    public void setTitleTask(String titleTask) {
        this.titleTask = titleTask;
    }

    public String getCategoryTask() {
        return categoryTask;
    }

    public void setCategoryTask(String categoryTask) {
        this.categoryTask = categoryTask;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
