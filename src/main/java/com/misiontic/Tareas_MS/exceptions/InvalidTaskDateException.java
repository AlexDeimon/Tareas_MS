package com.misiontic.Tareas_MS.exceptions;

public class InvalidTaskDateException extends RuntimeException{
    public InvalidTaskDateException(String message) {
        super(message);
    }
}
