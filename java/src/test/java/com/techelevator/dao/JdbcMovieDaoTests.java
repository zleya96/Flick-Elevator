package com.techelevator.dao;

import com.techelevator.model.Movie;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

public class JdbcMovieDaoTests extends BaseDaoTests{

    protected static final Movie MOVIE_1 = new Movie(1, "title1", "action,comedy", LocalDate.of(2020, 01, 01), "overview1", 90.0, "www.poster1.com");
    protected static final Movie MOVIE_2 = new Movie(2, "title2", "romance,disaster", LocalDate.of(2019, 01, 01), "overview2", 80.0, "www.poster2.com");
    private static final Movie MOVIE_3 = new Movie(3, "title3", "horror,thriller", LocalDate.of(2018, 01, 01), "overview3", 70.0, "www.poster3.com");
    private static final Movie MOVIE_4 = new Movie(4, "title4", "comedy", LocalDate.of(2017, 01, 01), "overview4", 60.0, "www.poster4.com");

    private JdbcMovieDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcMovieDao(jdbcTemplate);
    }

    @Test
    public void getAllMovies_returns_list_of_all_movies() {
        List<Movie> allMovies = sut.getAllMovies();

        Assert.assertNotNull(allMovies);
        Assert.assertEquals(3, allMovies.size());
        Assert.assertEquals(MOVIE_1.getId(), allMovies.get(0).getId());
        Assert.assertEquals(MOVIE_2.getId(), allMovies.get(1).getId());
        Assert.assertEquals(MOVIE_3.getId(), allMovies.get(2).getId());

    }

    @Test(expected = IllegalArgumentException.class)
    public void addMovie_given_null_throws_exception() {
        sut.addMovie(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addMovie_given_null_date_throws_exception() {
        Movie nullDateMovie = new Movie(-1, "title", "comedy", null, "overview", 60.0, "www.poster.com");

        sut.addMovie(nullDateMovie);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addMovie_given_current_date_throws_exception() {
        Movie currentDateMovie = new Movie(-1, "title", "comedy", LocalDate.now(), "overview", 60.0, "www.poster.com");

        sut.addMovie(currentDateMovie);
    }

    @Test public void addMovie_given_valid_movie_returns_added_movie() {
        Movie test = sut.addMovie(MOVIE_4);

        Assert.assertNotNull(test);
        Assert.assertEquals(MOVIE_4.getId(), test.getId());
        Assert.assertEquals(MOVIE_4.getOverview(), test.getOverview());
        Assert.assertEquals(MOVIE_4.getTitle(), test.getTitle());
        Assert.assertEquals(MOVIE_4.getGenres(), test.getGenres());
        Assert.assertEquals(MOVIE_4.getPoster_path(), test.getPoster_path());
        Assert.assertEquals(MOVIE_4.getRelease_date(), test.getRelease_date());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteMovie_given_invalid_id_throws_exception() {
        sut.deleteMovie(-1);
    }

    @Test
    public void deleteMovie_given_valid_id_deletes_movie() {
        int movieId = MOVIE_1.getId();

        sut.deleteMovie(movieId);
        Movie deletedMovie = sut.getMovieById(movieId);

        Assert.assertNull(deletedMovie);
    }

}
