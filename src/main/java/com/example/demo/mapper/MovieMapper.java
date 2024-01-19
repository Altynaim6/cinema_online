package com.example.demo.mapper;

import com.example.demo.dto.movie.MovieResponse;
import com.example.demo.entities.Movie;

import java.util.List;

public interface MovieMapper {
    List<MovieResponse> toDtoS(List<Movie> all);

    MovieResponse toDto(Movie movie);
}
