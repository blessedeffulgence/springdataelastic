package com.springdata.elastic.controller;

import com.springdata.elastic.model.Product;
import com.springdata.elastic.service.product.ProductIndexService;
import com.springdata.elastic.service.product.ProductIndexServiceWithRepo;
import com.springdata.elastic.service.product.ProductQueryService;
import com.springdata.elastic.service.product.ProductQueryServiceWithRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductIndexServiceWithRepo productIndexServiceWithRepo;
    private final ProductIndexService productIndexService;
    private final ProductQueryService productQueryService;
    private final ProductQueryServiceWithRepo productQueryServiceWithRepo;

    public ProductController(ProductIndexServiceWithRepo productIndexServiceWithRepo, ProductIndexService productIndexService, ProductQueryService productQueryService, ProductQueryServiceWithRepo productQueryServiceWithRepo) {
        this.productIndexServiceWithRepo = productIndexServiceWithRepo;
        this.productIndexService = productIndexService;
        this.productQueryService = productQueryService;
        this.productQueryServiceWithRepo = productQueryServiceWithRepo;
    }

    @PostMapping("/")
    public ResponseEntity indexDocumentRepo(@RequestBody @Validated List<Product> product)
    {
        logger.info("Saving product: {}",product);
       // productSearchServiceWithRepo.createProductIndex(product);
        //productSearchService.createProductIndex(product);
        productIndexService.createProductIndexBulk(product);

        return ResponseEntity.ok().body("Product Saved successfully");
    }

    @GetMapping("/{id}")
    public SearchHits<Product> queryProductById(@PathVariable String id)
    {

      SearchHits<Product> searchHits = productQueryService.findProductById(id);

      return searchHits;

    }

    @GetMapping("/repo/{id}")
    public Optional<Product> queryProductByIdRepo(@PathVariable String id)
    {
        return productQueryServiceWithRepo.findProductById(id);

    }

    @GetMapping("/repo/name/{name}")
    public List<Product> getProductByName(@PathVariable String name)
    {
        return productQueryServiceWithRepo.findProductByName(name);
    }

}
