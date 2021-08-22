package com.misiontic.Tareas_MS.controllers;

import com.misiontic.Tareas_MS.exceptions.DuplicatedCategoryNameException;
import com.misiontic.Tareas_MS.exceptions.DuplicatedTaskNameException;
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
    @PostMapping("save/{id}")
    Task saveTask(@RequestBody Task task){
        Task newTask = taskRepository.findByUserId(task.getId());
        if(newTask != null ){
            throw new DuplicatedTaskNameException("Ya existe una categoria con el nombre: " + newTask.getTitleTask());
        }
        taskRepository.save(task);

        return taskRepository.save(task);
    }

    @PutMapping("update/{id}")
    Task updateTask(@RequestBody Task task) {
        Task newTask = taskRepository.findByUserId(task.getId());
        if (newTask != null) {
             throw new TaskNotFoundException("No se encontro la tarea");
        }
        task.setTitleTask(task.getTitleTask());
        task.setDescription(task.getDescription());
        task.setFinalDate(task.getFinalDate());
        task.setStatus(task.getStatus());


        return taskRepository.save(task);
    }

    @GetMapping("/{id}/{date}")
    List<Task> getTaskList(@PathVariable String userId, Date finalDate) {
        List<Task> List = taskRepository.findByUserIdDate( userId, finalDate);


        return List;
    }
}
