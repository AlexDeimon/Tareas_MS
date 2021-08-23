package com.misiontic.Tareas_MS.repositories;
import com.misiontic.Tareas_MS.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String>{
    List<Category> findByUserId(String userId);
    List<Category> findByCategoryName(String categoryName);
}
