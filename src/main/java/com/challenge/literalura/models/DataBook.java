package com.challenge.literalura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBook(

        String title,
        List<DataAuthor> authors,
        @JsonAlias("languages") List<String> langs,
        @JsonAlias("download_count") Long numDownloads

) {
}
