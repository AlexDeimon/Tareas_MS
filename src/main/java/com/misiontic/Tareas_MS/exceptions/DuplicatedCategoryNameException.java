package com.misiontic.Tareas_MS.exceptions;

public class DuplicatedCategoryNameException extends RuntimeException{
    public DuplicatedCategoryNameException(String message) {
        super(message);
    }
}
