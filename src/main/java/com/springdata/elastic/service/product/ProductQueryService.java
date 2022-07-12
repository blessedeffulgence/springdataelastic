package com.springdata.elastic.service.product;

import com.springdata.elastic.model.Product;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class ProductQueryService {
    private static final String PRODUCT_INDEX = "products";
    private final ElasticsearchOperations elasticsearchOperations;
    private static final Logger logger = LoggerFactory.getLogger(ProductQueryService.class);

    public ProductQueryService(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    //search a single product using id

    public SearchHits<Product> findProductById(final String id)
    {
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("id",id);

        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        SearchHits<Product> productSearchHits =
                elasticsearchOperations. search(searchQuery,Product.class, IndexCoordinates.of(PRODUCT_INDEX));


        return productSearchHits;

    }
}
