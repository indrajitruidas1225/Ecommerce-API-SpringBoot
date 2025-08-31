package com.Indrajit.ecommerce_api.repository;

import com.Indrajit.ecommerce_api.model.Category;
import com.Indrajit.ecommerce_api.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void testSaveAndFindById(){
        Category category = Category.builder()
                .name("Books")
                .description("All kind of books")
                .build();
        categoryRepository.save(category);

        Product product = Product.builder()
                .name("1984")
                .price(500.0)
                .description("novel")
                .category(category)
                .StockQuantity(10)
                .build();
        Product savedProduct = productRepository.save(product);

        Optional<Product> found = productRepository.findById(savedProduct.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("1984");
    }

    @Test
    void testFindAll(){
        Category category = Category.builder()
                .name("Fashion")
                .description("Fashion Products")
                .build();

        Category category1 = Category.builder()
                .name("Electronics")
                .description("electronics Products")
                .build();
        categoryRepository.save(category);
        categoryRepository.save(category1);

        Product product = Product.builder()
                .name("Shorts")
                .description("Adidas shorts")
                .price(1000.0)
                .category(category)
                .StockQuantity(10)
                .build();

        Product product1 = Product.builder()
                .name("Samsung-S24")
                .description("Android")
                .price(100000.0)
                .category(category1)
                .StockQuantity(10)
                .build();

        productRepository.save(product);
        productRepository.save(product1);

        List<Product> allProducts = productRepository.findAll();

        assertThat(allProducts).hasSize(2);
        assertThat(allProducts)
                .extracting(Product::getName)
                .containsExactlyInAnyOrder("Shorts", "Samsung-S24");
    }
}
