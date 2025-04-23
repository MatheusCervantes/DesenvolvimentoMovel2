package com.example.trabalhofinal;

// Classe usada para representar um filme e mapear os dados JSON em um objeto Java é bascimente um DTO do MVC
public class Movie {

    private int id;
    private String title;
    private String overview;
    private String release_date;
    private String poster_path;

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public String getPosterPath() {
        return poster_path;
    }
}
