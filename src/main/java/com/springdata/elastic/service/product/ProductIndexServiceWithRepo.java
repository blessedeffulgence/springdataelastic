package com.springdata.elastic.service.product;

import com.springdata.elastic.model.Product;
import com.springdata.elastic.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductIndexServiceWithRepo {

    private final ProductRepository productRepository;

    public ProductIndexServiceWithRepo(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public void createProductIndexBulk(final List<Product> products)
    {
        productRepository.saveAll(products);
    }

    public void createProductIndex(final Product product)
    {
        productRepository.save(product);
    }
}
