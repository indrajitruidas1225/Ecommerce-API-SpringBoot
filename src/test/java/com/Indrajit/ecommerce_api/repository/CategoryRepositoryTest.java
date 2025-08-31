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
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testSaveAndFindById(){
        Category category = Category.builder()
                .name("Electronics")
                .description("desc")
                .build();

        Category savedCategory = categoryRepository.save(category);

        Optional<Category> found = categoryRepository.findById(savedCategory.getId());

        assertThat(found.isPresent());
        assertThat(found.get().getName()).isEqualTo("Electronics");
    }

    @Test
    void testFindAll() {
        Category category = Category.builder()
                .name("Accessories")
                .description("Fashion accessories")
                .build();
        categoryRepository.save(category);

        Product product = Product.builder()
                .name("Watch")
                .description("Smart watch")
                .price(1999.0)
                .category(category)
                .StockQuantity(5)
                .build();

        productRepository.save(product);

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getName()).isEqualTo("Watch");
    }
}
