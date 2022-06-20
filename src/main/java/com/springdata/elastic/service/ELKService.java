package com.springdata.elastic.service;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class ELKService {

    private static final Logger LOG = LoggerFactory.getLogger(ELKService.class);

    public JSONArray getAllFoodDetails() throws IOException {
        LOG.info("Fetching all food details");

        JSONArray foodDetail = new JSONArray();

        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("data/example.json", StandardCharsets.UTF_8));
            JSONObject jsonObject = (JSONObject) obj;
            foodDetail = (JSONArray) jsonObject.get("data");
        } catch (IOException | ParseException e) {
            LOG.error("Error occurred in reading JSON file");
            e.printStackTrace();
        }
        return foodDetail;
    }
}
