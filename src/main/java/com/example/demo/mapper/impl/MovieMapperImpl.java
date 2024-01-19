package com.example.demo.mapper.impl;

import com.example.demo.dto.movie.MovieResponse;
import com.example.demo.entities.Movie;
import com.example.demo.mapper.MovieMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapperImpl implements MovieMapper {
    @Override
    public List<MovieResponse> toDtoS(List<Movie> all) {
        List<MovieResponse> movieResponses = new ArrayList<>();
        for (Movie movie: all){
            movieResponses.add(toDto(movie));
        }
        return movieResponses;
    }

    @Override
    public MovieResponse toDto(Movie movie) {
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setId(movie.getId());
        movieResponse.setName(movie.getName());
        movieResponse.setDescription(movie.getDescription());
        movieResponse.setAge_access(movie.getAge_access());
        movieResponse.setPrize(String.valueOf(movie.getPrize()));
        movieResponse.setCreated_date(movie.getCreated_date());
        movieResponse.setType(movie.getType().getName());
        return movieResponse;
    }
}
