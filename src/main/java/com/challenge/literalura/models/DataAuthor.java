package com.challenge.literalura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataAuthor(

    String name,
    @JsonAlias("birth_year") Long birthYear,
    @JsonAlias("death_year") Long deathYear

) {
}
