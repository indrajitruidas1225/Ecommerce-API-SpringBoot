package com.Indrajit.ecommerce_api.service;

import com.Indrajit.ecommerce_api.exceptions.ResourceNotFoundException;
import com.Indrajit.ecommerce_api.model.Product;
import com.Indrajit.ecommerce_api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id "+id));
    }

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails){
        Product product = getProductById(id);

        product.setName(productDetails.getName());
        product.setCategory(productDetails.getCategory());
        product.setPrice(productDetails.getPrice());
        product.setDescription(productDetails.getDescription());
        product.setStockQuantity(productDetails.getStockQuantity());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id){
        if(!productRepository.existsById(id)){
            throw new ResourceNotFoundException("product not found with id "+id);
        }
        productRepository.deleteById(id);
    }
}
