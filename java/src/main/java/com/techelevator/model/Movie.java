package com.techelevator.model;

import java.time.LocalDate;
import java.util.List;

public class Movie {

    private int id;
    private String title;
    private String genres;
    private LocalDate release_date;
    private String overview;
    private double popularity;
    private String poster_path;

    public Movie() {}

    public Movie(int id, String title, String genres, LocalDate release_date, String overview, double popularity, String poster_path) {
        this.id = id;
        this.title = title;
        this.genres = genres;
        this.release_date = release_date;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenres() {
        return genres;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public String getOverview() {
        return overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
