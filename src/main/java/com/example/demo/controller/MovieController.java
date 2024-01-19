package com.example.demo.controller;


import com.example.demo.dto.movie.MovieAddRequest;
import com.example.demo.dto.movie.MovieResponse;
import com.example.demo.entities.Movie;
import com.example.demo.repositories.MovieRepository;
import com.example.demo.service.MovieService;
import com.example.demo.service.impl.MovieSubscriptionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
@AllArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final MovieRepository movieRepository;
    private final MovieSubscriptionServiceImpl movieSubscriptionService;

    @PostMapping("/add")
    public void addMovie(@RequestBody MovieAddRequest request, @RequestHeader("Authorization") String token) {
        movieService.addMovie(request, token);
    }
    @GetMapping("/movies")
    public List<MovieResponse> movieResponses(@RequestHeader("Authorization") String string){
        return movieService.getAll(string);
    }
    @GetMapping("/movies2")
    public List<Movie> movieResponses2(){
        return movieRepository.findAll();
    }

    @PostMapping("/subscribe/{movieId}")
    public void subscribe(@PathVariable Long movieId, @RequestHeader("Authorization") String token) {
        movieSubscriptionService.subscribeForMonth(movieId, token);
    }

    @GetMapping("/my/movies")
    public List<MovieResponse> myMovie(@RequestHeader("Authorization") String string){
        return MovieService.getMyMovies(string);
    }

}
