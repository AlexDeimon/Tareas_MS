package com.misiontic.Tareas_MS.controllers;

import com.misiontic.Tareas_MS.exceptions.CategoryNotFoundException;
import com.misiontic.Tareas_MS.exceptions.DuplicatedTaskNameException;
import com.misiontic.Tareas_MS.exceptions.TaskNotFoundException;
import com.misiontic.Tareas_MS.models.Task;
import com.misiontic.Tareas_MS.models.Category;
import com.misiontic.Tareas_MS.repositories.CategoryRepository;
import com.misiontic.Tareas_MS.repositories.TaskRepository;
import com.misiontic.Tareas_MS.repositories.AccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;



@RestController
public class TaskController {
    private final AccountRepository accountRepository;
    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;

    public TaskController(AccountRepository accountRepository, TaskRepository taskRepository, CategoryRepository categoryRepository) {
        this.accountRepository = accountRepository;
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
    }

    @DeleteMapping("delete/{taskId}")
    String deleteTask(@PathVariable String taskId){

        Task taskToDelete = taskRepository.findById(taskId).orElse(null);

        if (taskToDelete == null){
            throw new TaskNotFoundException("No existe una tarea con el Id: " + taskId);
        }

        taskRepository.delete(taskToDelete);

        return "Se ha eliminado la tarea";
    }

    @PostMapping("newTask")
    Task newTask(@RequestBody Task task){
        List<Task> newTask = taskRepository.findByUserIdDateAndName(task.getUserId(), task.getFinalDate(), task.getTaskTittle());
        List<Category> findCategoryTask = categoryRepository.findByNameAndUser(task.getTaskCategory(), task.getUserId());
        if(newTask.size() > 0){
            throw new DuplicatedTaskNameException("Ya existe una tarea con el titulo: " + task.getTaskTittle() +
                    ", en la fecha: " + task.getFinalDate());
        }
        if(findCategoryTask.size() == 0){
            throw new CategoryNotFoundException("No existe la categoria: " + task.getTaskCategory());
        }

        return taskRepository.save(task);
    }

    @PutMapping("update/")
    Task updateTask(@RequestBody Task task) {
        Task updateTask = taskRepository.findById(task.getTaskId()).orElse(null);
        List<Category> findCategoryTask = categoryRepository.findByNameAndUser(task.getTaskCategory(), task.getUserId());
        if (updateTask == null) {
             throw new TaskNotFoundException("No existe la tarea con id " + task.getTaskId());
        }

        if(findCategoryTask.size() == 0){
            throw new CategoryNotFoundException("No existe la categoria: " + task.getTaskCategory());
        }

        updateTask.setTaskTittle(task.getTaskTittle());
        updateTask.setDescription(task.getDescription());
        updateTask.setFinalDate(task.getFinalDate());
        updateTask.setStatus(task.getStatus());

        return taskRepository.save(updateTask);
    }

    @GetMapping("/{userId}/{finalDate}")
    List<Task> getTaskList(@PathVariable String userId, Date finalDate) {
        List<Task> List = taskRepository.findByUserIdDate( userId, finalDate);
        if(List.size() == 0){
            throw new TaskNotFoundException("No hay tareas registradas por el usuario con id: " + userId);
        }

        return List;
    }
}
