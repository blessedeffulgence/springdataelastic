package com.springdata.elastic.service.product;

import com.springdata.elastic.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexedObjectInformation;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductIndexService {

    private static final String PRODUCT_INDEX = "products";
    private final ElasticsearchOperations elasticsearchOperations;
    private static final Logger logger = LoggerFactory.getLogger(ProductIndexService.class);


    public ProductIndexService(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    //index a single document
    public String createProductIndex(Product product)
    {
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(product.getId().toString())
                .withObject(product)
                .build();
        String documentId = elasticsearchOperations.index(indexQuery, IndexCoordinates.of(PRODUCT_INDEX));
        logger.info("saved document with id: {}",documentId);

        return documentId;
    }

    //index bulk documents
    public List<IndexedObjectInformation> createProductIndexBulk
            (final List<Product> products) {


        List<IndexQuery> queries = products.stream()
                .map(product->
                        new IndexQueryBuilder()
                                .withId(product.getId().toString())
                                .withObject(product).build())
                .collect(Collectors.toList());;

        return elasticsearchOperations
                .bulkIndex(queries,IndexCoordinates.of(PRODUCT_INDEX));
    }
}
