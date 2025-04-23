package com.example.trabalhofinal;

// Classe usada para representar um filme e mapear os dados JSON em um objeto Java é bascimente um DTO do MVC
public class Movie {

    private int id;
    private String title;
    private String overview;
    private String release_date;
    private String poster_path;
    private String first_air_date;
    private String name;

    public String getTitle() {
        return title != null ? title : name; // name é para séries e title para filmes
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return release_date != null ? release_date : first_air_date; // first_air_date é para séries e release_date para filmes
    }

    public String getPosterPath() {
        return poster_path;
    }
}
