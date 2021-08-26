package com.misiontic.Tareas_MS.models;

import org.springframework.data.annotation.Id;

public class Account {
    @Id
    private String userId;
    private String userName;

    public Account(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
