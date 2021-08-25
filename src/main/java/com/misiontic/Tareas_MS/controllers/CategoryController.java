package com.misiontic.Tareas_MS.controllers;

import com.misiontic.Tareas_MS.exceptions.DuplicatedCategoryNameException;
import com.misiontic.Tareas_MS.exceptions.CategoryNotFoundException;
import com.misiontic.Tareas_MS.models.Category;
import com.misiontic.Tareas_MS.repositories.CategoryRepository;
import java.util.List;
import java.util.Locale;

import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("newCategory")
    Category postCategory(@RequestBody Category category){
        List<Category> newCategory = categoryRepository.findByUserId(category.getUserId());
        if(newCategory.size() > 0){
            for (Category categoria:newCategory) {
                if(categoria.getCategoryName().toLowerCase(Locale.ROOT).equals(category.getCategoryName().toLowerCase(Locale.ROOT))) {
                    throw new DuplicatedCategoryNameException("Ya existe una categoria " +category.getCategoryName()+
                            " creada por el usuario: " + category.getUserId());
                }

            }
        }

        return categoryRepository.save(category);
    }

    @DeleteMapping("deleteCategory/{userId}/{categoryName}")
    String deleteCategory(@PathVariable String userId, @PathVariable String categoryName){

        Category categoryToDelete = categoryRepository.findByUserIdAndCategoryName(userId, categoryName);

        if (categoryToDelete == null){
            throw new CategoryNotFoundException("No existe una categoria: " + categoryName + ", creada por el usuario: " + userId);
        }

        categoryRepository.delete(categoryToDelete);

        return "Se ha eliminado la categoria";
    }

}
