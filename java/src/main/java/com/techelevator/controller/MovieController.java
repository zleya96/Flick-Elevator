package com.techelevator.controller;
import com.techelevator.dao.MovieDao;
import com.techelevator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class MovieController {

    @Autowired
    private MovieDao movieDao;

    @GetMapping("/movie")
    public List<Movie> getAllMovies() {
        return movieDao.getAllMovies();
    }
// add authorization
    @PostMapping("/movie")
    public Movie postMovie(@RequestBody Movie movieToAdd) {
        return movieDao.addMovie(movieToAdd);
    }
// add authorization
    @DeleteMapping("/movie/{movieId}")
    public void deleteMovie(@PathVariable("movieId") int movieId) {
        movieDao.deleteMovie(movieId);
    }






}
