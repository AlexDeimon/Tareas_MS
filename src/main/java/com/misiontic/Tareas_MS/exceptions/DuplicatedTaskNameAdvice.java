package com.misiontic.Tareas_MS.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody

public class DuplicatedTaskNameAdvice {
    @ResponseBody
    @ExceptionHandler(DuplicatedTaskNameException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    String DuplicatedTaskName(DuplicatedTaskNameException ex){
        return ex.getMessage();
    }
}
