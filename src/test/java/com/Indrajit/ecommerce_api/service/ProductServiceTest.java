package com.Indrajit.ecommerce_api.service;

import com.Indrajit.ecommerce_api.exceptions.ResourceNotFoundException;
import com.Indrajit.ecommerce_api.model.Category;
import com.Indrajit.ecommerce_api.model.Product;
import com.Indrajit.ecommerce_api.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.module.ResolutionException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product sampleProduct;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        Category category = Category.builder().id(1L).name("Electronics").build();

        sampleProduct = Product.builder()
                .id(1L)
                .name("Phone")
                .price(500.0)
                .category(category)
                .StockQuantity(10)
                .build();
    }

    @Test
    void testGetProductById_Found(){
        when(productRepository.findById(1L)).thenReturn(Optional.of(sampleProduct));

        Product result = productService.getProductById(1L);
        assertNotNull(result);
        assertEquals("Phone", result.getName());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testGetProductById_NotFound(){
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                ()-> productService.getProductById(99L));
    }

    @Test
    void testGetAllProducts(){
        when(productRepository.findAll()).thenReturn(Arrays.asList(sampleProduct));
        List<Product> products = productService.getAllProducts();

        assertEquals(1, products.size());
        assertEquals("Phone", products.get(0).getName());
    }
}
