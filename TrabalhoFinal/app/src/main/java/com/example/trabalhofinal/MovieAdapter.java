package com.example.trabalhofinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movieList;
    private Context context;

    public MovieAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }


    // Cria o layout para cada item da lista (card do filme)
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(itemView);
    }

    // Preenche os dados do filme em cada item da lista
    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        holder.movieTitle.setText(movie.getTitle());

        String releaseDate = movie.getReleaseDate();
        if (releaseDate != null && !releaseDate.isEmpty()) {
            String formattedDate = formatDate(releaseDate);
            holder.movieReleaseDate.setText(formattedDate);
        } else {
            holder.movieReleaseDate.setText("Data não disponível");
        }

        String overview = movie.getOverview();
        if (overview != null && !overview.isEmpty()) {
            holder.movieOverview.setText(overview);
        } else {
            holder.movieOverview.setText("Descrição não disponível");
        }

        String imageUrl = "https://image.tmdb.org/t/p/w500" + movie.getPosterPath();
        Glide.with(context)
                .load(imageUrl)
                .into(holder.moviePoster);
    }

    // Retorna o número de itens na lista, necessário para o RecyclerView
    @Override
    public int getItemCount() {
        return movieList != null ? movieList.size() : 0;
    }

    // Atualiza a lista de filmes
    public void setMovies(List<Movie> movies) {
        this.movieList = movies;
        notifyDataSetChanged();
    }

    // ViewHolder é usado para armazenar referências das views do item da lista (título, imagem, etc).
    // Isso evita a busca repetida de views (findViewById), tornando o app mais rápido e eficiente.
    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        public TextView movieTitle, movieReleaseDate, movieOverview;
        public ImageView moviePoster;

        public MovieViewHolder(View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            movieReleaseDate = itemView.findViewById(R.id.movieReleaseDate);
            movieOverview = itemView.findViewById(R.id.movieOverview);
            moviePoster = itemView.findViewById(R.id.moviePoster);
        }
    }

    private String formatDate(String releaseDate) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date = inputFormat.parse(releaseDate);
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return releaseDate;
        }
    }
}
