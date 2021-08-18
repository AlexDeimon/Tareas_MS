package com.misiontic.Tareas_MS.exceptions;



public class DuplicatedTaskNameException extends RuntimeException{
    public DuplicatedTaskNameException(String message) {
        super(message);
    }
}
