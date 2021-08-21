package com.misiontic.Tareas_MS.repositories;
import com.misiontic.Tareas_MS.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String>{
    Category findByUserId (String userId);
    Category findBycategoryName(String categoryName);
}
