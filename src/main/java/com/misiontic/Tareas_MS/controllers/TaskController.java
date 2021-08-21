package com.misiontic.Tareas_MS.controllers;

import com.misiontic.Tareas_MS.exceptions.TaskNotFoundException;
import com.misiontic.Tareas_MS.models.Task;
import com.misiontic.Tareas_MS.repositories.TaskRepository;
import com.misiontic.Tareas_MS.repositories.AccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
public class TaskController {
    private final AccountRepository accountRepository;
    private final TaskRepository taskRepository;

    public TaskController(AccountRepository accountRepository, TaskRepository taskRepository) {
        this.accountRepository = accountRepository;
        this.taskRepository = taskRepository;
    }

    @DeleteMapping("delete/{id}")
    String deleteTask(@PathVariable String Id){
        Task taskToDelete = taskRepository.findById(Id).orElse(null);
        if (taskToDelete == null){
            throw new TaskNotFoundException("No existe una tarea con el Id: " + Id);
        }

        taskRepository.delete(taskToDelete);

        return "Se ha eliminado la tarea";
    }
}
