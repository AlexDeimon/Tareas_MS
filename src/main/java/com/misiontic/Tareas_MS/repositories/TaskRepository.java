package com.misiontic.Tareas_MS.repositories;
import com.misiontic.Tareas_MS.models.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String>{
    List<Task> findByUserId(String userId);
    Task findByUserIdAndTaskTittle(String userId, String taskTittle);
    List<Task> findByUserIdAndTaskDate(String userId, Date taskDate);
}
