package com.example.trabalhofinal;

import java.util.List;

// A classe MovieResponse armazena a lista de filmes retornados pela API.
public class MovieResponse {

    private List<Movie> results;

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
