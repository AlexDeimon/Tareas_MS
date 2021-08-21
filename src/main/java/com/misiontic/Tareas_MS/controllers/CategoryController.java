package com.misiontic.Tareas_MS.controllers;

import com.misiontic.Tareas_MS.exceptions.DuplicatedCategoryNameException;
import com.misiontic.Tareas_MS.models.Category;
import com.misiontic.Tareas_MS.repositories.CategoryRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("newCategory")
    Category postCategory(@RequestBody Category category){
        Category newCategoryIntoUser = categoryRepository.findByUserId(category.getUserId());
        Category newCategory = categoryRepository.findBycategoryName(category.getCategoryName());

        if(newCategory != null && newCategoryIntoUser != null){
            throw new DuplicatedCategoryNameException("Ya existe una categoria con el nombre: " + newCategory.getCategoryName()
                    + " creada por el usuario: " + newCategoryIntoUser.getUserId());
        }

        return categoryRepository.save(category);
    }

}
