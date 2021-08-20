package com.misiontic.Tareas_MS.models;

import org.springframework.data.annotation.Id;
import java.util.Date;

public class Task {
    @Id
    private String id;
    private String titleTask;
    private String description;
    private Date finalDate;
    private String status;
    private String categoryTask;

    public Task(String id, String titleTask, Date finalDate, String status, String categoryTask) {
        this.id = id;
        this.titleTask = titleTask;
        this.finalDate = finalDate;
        this.status = status;
        this.categoryTask = categoryTask;
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

    public String getCategoryTask() {
        return categoryTask;
    }

    public void setCategoryTask(String categoryTask) {
        this.categoryTask = categoryTask;
    }
}
