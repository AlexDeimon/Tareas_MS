package com.misiontic.Tareas_MS.controllers;

import com.misiontic.Tareas_MS.exceptions.CategoryNotFoundException;
import com.misiontic.Tareas_MS.exceptions.DuplicatedTaskNameException;
import com.misiontic.Tareas_MS.exceptions.TaskNotFoundException;
import com.misiontic.Tareas_MS.models.Task;
import com.misiontic.Tareas_MS.models.Category;
import com.misiontic.Tareas_MS.repositories.CategoryRepository;
import com.misiontic.Tareas_MS.repositories.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    @DeleteMapping("/deleteTask/{userId}/{taskTittle}")
    String deleteTask(@PathVariable String userId, @PathVariable String taskTittle){
        Task taskToDelete = taskRepository.findByUserIdAndTaskTittle(userId,taskTittle);
        if (taskToDelete == null){
            throw new TaskNotFoundException("No existe una tarea: " + taskTittle + ", creada por el usuario: " + userId);
        }
        taskRepository.delete(taskToDelete);
        return "Se ha eliminado la tarea";
    }

    @PostMapping("/newTask")
    Task newTask(@RequestBody Task task) throws ParseException {
        List <Task> newTask = taskRepository.findByUserId(task.getUserId());
        List<Category> findCategoryName = categoryRepository.findByCategoryNameAndUserId(task.getTaskCategory(), task.getUserId());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String strDate = formatter.format(task.getTaskDate());
        c.setTime(formatter.parse(strDate));
        c.add(Calendar.DAY_OF_MONTH, 1);
        String newDate = formatter.format(c.getTime());
        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(newDate);
        task.setTaskDate(date1);
        if(newTask.size() > 0){
            for (Task tarea:newTask) {
                if (tarea.getTaskDate().equals(task.getTaskDate()) && tarea.getTaskTittle().toLowerCase(Locale.ROOT).equals(task.getTaskTittle().toLowerCase(Locale.ROOT))) {
                    throw new DuplicatedTaskNameException("Ya existe una tarea con el titulo: " + task.getTaskTittle() + ", en la fecha: " + task.getTaskDate());
                }
            }
        }
        if(findCategoryName.size() == 0){
            throw new CategoryNotFoundException("No existe la categoria: " + task.getTaskCategory());
        }
        return taskRepository.save(task);
    }

    @GetMapping("/getTask/{userId}/{taskTittle}")
    Task getTask(@PathVariable String userId, @PathVariable String taskTittle){
        Task task = taskRepository.findByUserIdAndTaskTittle(userId,taskTittle);
        if (task == null){
            throw new TaskNotFoundException("No existe una tarea: " + taskTittle + ", creada por el usuario: " + userId);
        }
        return task;
    }

    @PutMapping("/updateTask/{taskId}")
    Task updateTask(@PathVariable String taskId, @RequestBody Task task) throws ParseException {
        Task updateTask = taskRepository.findById(taskId).orElse(null);
        List<Category> findCategoryTask = categoryRepository.findByCategoryNameAndUserId(task.getTaskCategory(), task.getUserId());
        if (updateTask == null) {
             throw new TaskNotFoundException("No existe la tarea con id " + task.getTaskId());
        }
        if(findCategoryTask.size() == 0){
            throw new CategoryNotFoundException("No existe la categoria: " + task.getTaskCategory());
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String strDate = formatter.format(task.getTaskDate());
        c.setTime(formatter.parse(strDate));
        c.add(Calendar.DAY_OF_MONTH, 1);
        String newDate = formatter.format(c.getTime());
        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(newDate);
        updateTask.setTaskTittle(task.getTaskTittle());
        updateTask.setTaskCategory(task.getTaskCategory());
        updateTask.setTaskDescription(task.getTaskDescription());
        updateTask.setTaskStatus(task.getTaskStatus());
        updateTask.setTaskDate(date1);
        return taskRepository.save(updateTask);
    }

    @GetMapping("/{userId}/{taskDate}")
    List<Task> getTaskList(@PathVariable String userId , @PathVariable String taskDate) throws ParseException {
        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(taskDate);
        List<Task> TaskList = taskRepository.findByUserIdAndTaskDate(userId, date1);
        if(TaskList.size() == 0){
            throw new TaskNotFoundException("No hay tareas registradas por el usuario con id: " + userId + " en la fecha: " + taskDate);
        }
        return TaskList;
    }
}
