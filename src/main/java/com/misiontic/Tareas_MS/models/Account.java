package com.misiontic.Tareas_MS.models;

import org.springframework.data.annotation.Id;

public class Account {
    @Id
    private String userId;

    public Account(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
