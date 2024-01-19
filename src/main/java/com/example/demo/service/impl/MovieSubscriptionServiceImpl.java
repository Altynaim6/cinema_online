package com.example.demo.service.impl;

import com.example.demo.entities.Movie;
import com.example.demo.entities.User;
import com.example.demo.exception.BadCredentialsException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repositories.MovieRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.AuthService;
import com.example.demo.service.MovieSubscriptionService;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieSubscriptionServiceImpl implements MovieSubscriptionService {
    private final AuthService authService;
    public static class SecurityConstants {
        public static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    }

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public void subscribeForMonth(Long movieId, String token) {
        User user = authService.getUsernameFromToken(token);
        Movie movie = getMovieById(movieId);

        if (!user.isSubscribed()) {
            user.setSubscribed(true);
            user.setSubscriptionEndDate(calculateSubscriptionEndDate());
            movie.setCustomer(user.getCustomer());
            movieRepository.save(movie);
            userRepository.save(user);
        } else {
            throw new BadRequestException("User is already subscribed.");
        }
    }

    private User validateUser(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(String.valueOf(secretKey))
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();

            Optional<User> user = userRepository.findByEmail(email);

            if (user.isEmpty()) {
                throw new UsernameNotFoundException("User not found for provided token");
            }

            return user.get();

        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            throw new BadCredentialsException("Invalid or expired token");
        }
    }

    private Movie getMovieById(Long movieId) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);

        return optionalMovie.orElseThrow(() ->
                new NoSuchElementException("Movie not found with ID: " + movieId));
    }

    private LocalDateTime calculateSubscriptionEndDate() {
        return LocalDateTime.now().plus(1, ChronoUnit.MONTHS);
    }
}
