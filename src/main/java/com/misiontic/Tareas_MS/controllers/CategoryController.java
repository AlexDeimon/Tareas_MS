package com.misiontic.Tareas_MS.controllers;

import com.misiontic.Tareas_MS.exceptions.DuplicatedCategoryNameException;
import com.misiontic.Tareas_MS.exceptions.CategoryNotFoundException;
import com.misiontic.Tareas_MS.models.Category;
import com.misiontic.Tareas_MS.repositories.CategoryRepository;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("newCategory")
    Category postCategory(@RequestBody Category category){
        List<Category> newCategory = categoryRepository.findByNameAndUser(category.getCategoryName(), category.getUserId());

        if(newCategory.size() > 0){
            throw new DuplicatedCategoryNameException("Ya existe una categoria con el nombre: " + newCategory.get(0).getCategoryName()
                    + " creada por el usuario: " + newCategory.get(0).getUserId());
        }

        return categoryRepository.save(category);
    }

    @DeleteMapping("delete/{categoryId}")
    String deleteTask(@PathVariable String categoryId){

        Category categoryToDelete = categoryRepository.findById(categoryId).orElse(null);

        if (categoryToDelete == null){
            throw new CategoryNotFoundException("No existe una categoria con el Id: " + categoryId);
        }

        categoryRepository.delete(categoryToDelete);

        return "Se ha eliminado la categoria";
    }

}
