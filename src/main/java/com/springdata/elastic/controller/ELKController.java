package com.springdata.elastic.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.springdata.elastic.service.ELKService;
import com.springdata.elastic.service.RestService;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

@RestController
public class ELKController {

    private static final Logger LOG = LoggerFactory.getLogger(ELKController.class);

    private final ELKService elkService;

    private final RestService restService;


    public ELKController(ELKService elkService, RestService restService) {
        this.elkService = elkService;
        this.restService = restService;
    }

    @GetMapping(value = "/hello")
    public String helloWorld() {
        LOG.info("Inside Hello World Function");
        String response = "Hello World! " + new Date();
        LOG.info("Response => {}",response);
        return response;
    }

    @GetMapping(value = "/Food-Details")
    public JSONArray foodDetails() throws IOException {
        LOG.info("Inside Food Detail Function");
        return elkService.getAllFoodDetails();
    }

    @GetMapping(value = "/weather/{city}")
    public JsonNode getWeatherInformation(@PathVariable String city){
        return restService.getPostsPlainJSON(city);
    }
}
