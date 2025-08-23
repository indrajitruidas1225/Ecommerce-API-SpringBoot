package com.Indrajit.ecommerce_api.service;

import com.Indrajit.ecommerce_api.exceptions.ResourceNotFoundException;
import com.Indrajit.ecommerce_api.model.Category;
import com.Indrajit.ecommerce_api.repository.CategoryRepository;
import jakarta.validation.constraints.Null;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Such Category Found with "+id));
    }

    public Category getCategoryByName(String name){
        Category category = categoryRepository.getCategoryByName(name);
        if(category == null){
            throw new ResourceNotFoundException("Category not found with name "+ name);
        }
        return category;
    }

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category category){
        Category category1 = getCategoryById(id);
        category1.setName(category.getName());
        category1.setDescription(category.getDescription());
        return categoryRepository.save(category1);
    }
}
