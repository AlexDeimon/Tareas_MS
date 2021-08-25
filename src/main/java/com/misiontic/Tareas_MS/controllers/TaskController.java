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
import java.util.Locale;
import java.text.SimpleDateFormat;



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

    @DeleteMapping("deleteTask/{taskId}")
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
        List<Task> newTask = taskRepository.findByUserId(task.getUserId());
        List<Category> findCategoryName = categoryRepository.findByCategoryNameAndUserId(task.getTaskCategory(), task.getUserId());

        if(newTask.size() > 0){
            for (Task tarea:newTask) {
                if(tarea.getFinalDate().equals(task.getFinalDate())  && tarea.getTaskTittle().toLowerCase(Locale.ROOT).equals(task.getTaskTittle().toLowerCase(Locale.ROOT))){
                    throw new DuplicatedTaskNameException("Ya existe una tarea con el titulo: " + task.getTaskTittle() +
                            ", en la fecha: " + task.getFinalDate());
                }
            }
        }
        if(  findCategoryName.size() == 0){
            throw new CategoryNotFoundException("No existe la categoria: " + task.getTaskCategory());
        }

        return taskRepository.save(task);
    }

    @PutMapping("updateTask")
    Task updateTask(@RequestBody Task task) {
        Task updateTask = taskRepository.findById(task.getTaskId()).orElse(null);
        List<Category> findCategoryTask = categoryRepository.findByCategoryNameAndUserId(task.getTaskCategory(), task.getUserId());
        if (updateTask == null) {
             throw new TaskNotFoundException("No existe la tarea con id " + task.getTaskId());
        }

        if(findCategoryTask.size() == 0){
            throw new CategoryNotFoundException("No existe la categoria: " + task.getTaskCategory());
        }

        return taskRepository.save(task);
    }

    @GetMapping("{userId}/{finalDate}")
    List<Task> getTaskList(@PathVariable String userId, Date finalDate) {
        List<Task> TaskList = taskRepository.findByUserId(userId);
        if(TaskList.size() == 0){
            throw new TaskNotFoundException("No hay tareas registradas por el usuario con id: " + userId);
        }

        for(Task tarea:TaskList){
            if(tarea.getFinalDate() != finalDate){
                TaskList.remove(tarea);
            }
        }


        return TaskList;
    }
}
