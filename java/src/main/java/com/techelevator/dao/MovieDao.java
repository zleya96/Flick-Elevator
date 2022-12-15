package com.techelevator.dao;

import com.techelevator.model.Movie;

import java.time.LocalDate;
import java.util.List;

public interface MovieDao {

    // Retrieve
    Movie getMovieById(int movieId);

    List<Movie> getAllMovies();

    // Create
    Movie addMovie(Movie movieToAdd);

    // Update
    Movie updateMovie(int id, String title, String[] genres, LocalDate release_date, String overview, double popularity, int runtime, String tagline, String poster_path);

    // Delete
    void deleteMovie(int movieId);
}
