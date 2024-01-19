package com.example.demo.service;

import com.example.demo.dto.movie.MovieAddRequest;
import com.example.demo.dto.movie.MovieResponse;

import java.util.List;

public interface MovieService {

    void addMovie(MovieAddRequest request, String token);

    List<MovieResponse> getAll(String string);

    static List<MovieResponse> getMyMovies(String string) {
        return null;
    }
}

