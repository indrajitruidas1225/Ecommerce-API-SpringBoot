package com.Indrajit.ecommerce_api.controller;

import com.Indrajit.ecommerce_api.model.Category;
import com.Indrajit.ecommerce_api.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory(){
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<Category> getCategoryByName(@RequestParam String name){
        return ResponseEntity.ok(categoryService.getCategoryByName(name));
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category createdCategory = categoryService.createCategory(category);
        return ResponseEntity.created(URI.create("/api/categories/"+createdCategory.getId())).body(createdCategory);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category){
        return ResponseEntity.ok(categoryService.updateCategory(id, category));
    }
}
