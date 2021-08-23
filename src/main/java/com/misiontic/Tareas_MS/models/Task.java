package com.misiontic.Tareas_MS.models;

import org.springframework.data.annotation.Id;
import java.util.Date;

public class Task {
    @Id
    private String taskId;
    private String taskTittle;
    private String description;
    private Date finalDate;
    private String status;
    private String taskCategory;
    private String userId;

    public Task(String taskId, String taskTittle, String description, Date finalDate, String status, String taskCategory, String userId) {
        this.taskId = taskId;
        this.taskTittle = taskTittle;
        this.description = description;
        this.finalDate = finalDate;
        this.status = status;
        this.taskCategory = taskCategory;
        this.userId = userId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskTittle() {
        return taskTittle;
    }

    public void setTaskTittle(String taskTittle) {
        this.taskTittle = taskTittle;
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

    public String getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(String taskCategory) {
        this.taskCategory = taskCategory;
    }

    public String getUserId() {return userId; }

    public void setUserId(String userId) { this.userId = userId; }
}
