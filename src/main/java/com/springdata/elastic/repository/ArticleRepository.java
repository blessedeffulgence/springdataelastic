package com.springdata.elastic.repository;

import com.springdata.elastic.model.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleRepository extends ElasticsearchRepository<Article,String> {


}
