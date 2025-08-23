package com.Indrajit.ecommerce_api.repository;

import com.Indrajit.ecommerce_api.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.name = :name")
    Category getCategoryByName(@Param("name") String name);
}
