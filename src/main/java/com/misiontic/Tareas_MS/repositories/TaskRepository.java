package com.misiontic.Tareas_MS.repositories;
import com.misiontic.Tareas_MS.models.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Date;

public interface TaskRepository extends MongoRepository<Task, String>{
    List<Task> findByUserId(String userId);
    List<Task> findByDate(Date finalDate);
}
