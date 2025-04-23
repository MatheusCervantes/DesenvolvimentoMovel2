package com.example.trabalhofinal;

import android.os.Bundle;
import android.widget.Toast;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieListActivity extends AppCompatActivity {

    // Configuração da API do The Movie Database
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "8386b388b56f2007df938b7ac3b02b41";

    private RecyclerView recyclerViewMovies; // RecyclerView para exibir a lista de filmes
    private MovieAdapter movieAdapter; // Adaptador para exibir os filmes na RecyclerView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list);

        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));

        movieAdapter = new MovieAdapter(this, null);
        recyclerViewMovies.setAdapter(movieAdapter);
        String query = getIntent().getStringExtra("query"); // Obtém a query da atividade anterior

        if (query != null && !query.isEmpty()) {
            searchMovies(query);
        }

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            finish();
        });

    }

    // Método para buscar filmes na API e preencher o adapter e em seguida a RecyclerView
    private void searchMovies(String query) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TmdbApi api = retrofit.create(TmdbApi.class);
        Call<MovieResponse> call = api.searchMovies(API_KEY, query, "pt-BR", 1);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    List<Movie> movies = response.body().getResults();
                    movieAdapter.setMovies(movies);
                } else {
                    Toast.makeText(MovieListActivity.this, "Erro ao carregar filmes", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(MovieListActivity.this, "Falha na conexão" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
