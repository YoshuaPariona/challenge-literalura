package com.challenge.literalura.services;

import com.challenge.literalura.models.DataAuthor;
import com.challenge.literalura.models.DataBook;
import com.challenge.literalura.models.DataJson;
import com.challenge.literalura.models.Lang;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ConvertDataService{
    private ObjectMapper mapper = new ObjectMapper();


    private  <T> T getData(String json, Class<T> dataRecord) {
        try {
            return mapper.readValue(json, dataRecord);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public DataBook getDataBook(String json) {

        DataJson dataJson = getData(json, DataJson.class);

        if (dataJson.results() == null || dataJson.results().isEmpty()) {
            throw new RuntimeException("No se encontraron libros.");
        }

        return dataJson.results().get(0);
    }

    public List<DataAuthor> getDataAuthor(String json) {

        return getDataBook(json).authors();
    }

    public List<String> getDataLang(String json) {

        return getDataBook(json).langs();
    }

}
