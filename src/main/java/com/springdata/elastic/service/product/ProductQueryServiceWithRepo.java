package com.springdata.elastic.service.product;

import com.springdata.elastic.model.Product;
import com.springdata.elastic.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductQueryServiceWithRepo {

    private final ProductRepository productRepository;

    public ProductQueryServiceWithRepo(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> findProductById(String id)
    {
        return productRepository.findById(id);
    }
    
    public List<Product> findProductByName(String name)
    {
        return productRepository.findByName(name);
    }
}
