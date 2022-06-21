package com.springdata.elastic.controller;

import com.github.javafaker.Faker;
import com.springdata.elastic.config.ElasticConfig;
import com.springdata.elastic.model.Article;
import com.springdata.elastic.model.Author;
import com.springdata.elastic.repository.ArticleRepository;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("/api")
public class MainController {

    private static final Logger LOG = LoggerFactory.getLogger(MainController.class);

    private final ArticleRepository articleRepository;

    public MainController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    @Scheduled(fixedRate = 10000L)
    public void addArticle()
    {
        Faker faker = new Faker();
        Article article = new Article(faker.idNumber().valid(),faker.name().title());
        article.setAuthors(asList(new Author(faker.name().fullName()), new Author(faker.name().fullName())));

        Article response = articleRepository.save(article);

        String status = response.getId();

        LOG.info("Saved with id {}",status);


    }
}
