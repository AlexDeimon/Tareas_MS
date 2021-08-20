package com.misiontic.Tareas_MS.models;

import org.springframework.data.annotation.Id;
import java.util.Date;

public class Account {
    @Id
    private String userId;
    private Date lastChange;


    public Account(String userId, Date lastChange) {
        this.userId = userId;
        this.lastChange = lastChange;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getLastChange() {
        return lastChange;
    }

    public void setLastChange(Date lastChange) {
        this.lastChange = lastChange;
    }
}
