package com.misiontic.Tareas_MS.controllers;

import com.misiontic.Tareas_MS.exceptions.CategoryNotFoundException;
import com.misiontic.Tareas_MS.exceptions.DuplicatedTaskNameException;
import com.misiontic.Tareas_MS.exceptions.TaskNotFoundException;
import com.misiontic.Tareas_MS.models.Task;
import com.misiontic.Tareas_MS.models.Category;
import com.misiontic.Tareas_MS.repositories.CategoryRepository;
import com.misiontic.Tareas_MS.repositories.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;



@RestController
public class TaskController {
    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;

    public TaskController(TaskRepository taskRepository, CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
    }

    @DeleteMapping("deleteTask/{userId}/{taskTittle}")
    String deleteTask(@PathVariable String userId, @PathVariable String taskTittle){

        Task taskToDelete = taskRepository.findByUserIdAndTaskTittle(userId,taskTittle);

        if (taskToDelete == null){
            throw new TaskNotFoundException("No existe una tarea: " + taskTittle + ", creada por el usuario: " + userId);
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

    @GetMapping("getTask/{userId}/{taskTittle}")
    Task getTask(@PathVariable String userId, @PathVariable String taskTittle){
        Task task = taskRepository.findByUserIdAndTaskTittle(userId,taskTittle);

        if (task == null){
            throw new TaskNotFoundException("No existe una tarea: " + taskTittle + ", creada por el usuario: " + userId);
        }

        return task;
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
    List<Task> getTaskList(@PathVariable String userId , @PathVariable String finalDate) {
        List<Task> TaskList = taskRepository.findByUserId(userId);
        if(TaskList.size() == 0){
            throw new TaskNotFoundException("No hay tareas registradas por el usuario con id: " + userId);
        }
        try{
            DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date fecha = fechaHora.parse(finalDate);
            TaskList.removeIf(tarea -> tarea.getFinalDate().equals(fecha));
        }catch (ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return TaskList;
    }
}
