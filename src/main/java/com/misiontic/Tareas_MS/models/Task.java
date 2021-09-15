package com.misiontic.Tareas_MS.models;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Task {
    @Id
    private String taskId;
    private String userId;
    private String taskTittle;
    private String taskCategory;
    private String taskDescription;
    private String taskStatus;
    private Date taskDate;

    public Task(String taskId, String userId, String taskTittle, String taskCategory, String taskDescription, String taskStatus, Date taskDate) {
        this.taskId = taskId;
        this.userId = userId;
        this.taskTittle = taskTittle;
        this.taskCategory = taskCategory;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
        this.taskDate = taskDate;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskTittle() {
        return taskTittle;
    }

    public void setTaskTittle(String taskTittle) {
        this.taskTittle = taskTittle;
    }

    public String getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(String taskCategory) {
        this.taskCategory = taskCategory;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }
}
