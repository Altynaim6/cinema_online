package com.example.demo.service.impl;

import com.example.demo.dto.movie.MovieAddRequest;
import com.example.demo.dto.movie.MovieResponse;
import com.example.demo.entities.Movie;
import com.example.demo.entities.Type;
import com.example.demo.enums.Role;
import com.example.demo.mapper.MovieMapper;
import com.example.demo.repositories.MovieRepository;
import com.example.demo.repositories.TypeRepository;
import com.example.demo.service.AuthService;
import com.example.demo.service.MovieService;
import lombok.AllArgsConstructor;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final AuthService authService;
    private final TypeRepository typeRepository;
    private final MovieMapper movieMapper;

    private final MovieRepository movieRepository;
    @Override
    public void addMovie(MovieAddRequest request, String token) {
        if(movieRepository.findByName(request.getMovie_name()).isPresent())
            throw new NotFoundException("movie with this name does not exsist!: "+ request.getMovie_name()
                    );
        if (!authService.getUsernameFromToken(token).getRole().equals(Role.ADMIN))
            throw new NotFoundException("this function only for admin!");

        Movie movie = new Movie();

        if (request.getPrize() < 100) {
            throw new NotFoundException("The prize should be at least 100!");
        }

        movie.setName(request.getMovie_name());
        movie.setPrize(request.getPrize());
        movie.setAge_access(request.getAge_access());
        movie.setDescription(request.getDescription());
        movie.setCreated_date(LocalDateTime.now().toString());
        Optional<Object> type = typeRepository.findByName(request.getType());
        if (type.isEmpty())
            throw new NotFoundException("jchb");
            movie.setType((Type) type.get());
            movieRepository.save(movie);

    }

    @Override
    public List<MovieResponse> getAll(String string) {
        return movieMapper.toDtoS(movieRepository.findAll());
    }

}
