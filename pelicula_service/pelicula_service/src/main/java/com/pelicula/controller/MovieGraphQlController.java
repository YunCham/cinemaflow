package com.pelicula.controller;

import com.pelicula.dto.MovieRequest;
import com.pelicula.model.Movie;
import com.pelicula.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class MovieGraphQlController {
    @Autowired
    private MovieService movieService;

    @QueryMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @QueryMapping
    public Movie getMovieById(@Argument String movieId) {
        return movieService.getMovieById(movieId);
    }

    @MutationMapping
    public Movie createMovie(@Argument Movie movie) {

        return movieService.createMovie(movie);
    }

    @MutationMapping
    public Movie updateMovie(@Argument String movieId, @Argument MovieRequest movieRequest) {
        Movie existingMovie = movieService.getMovieById(movieId);

        Movie updatedMovie = new Movie(
                existingMovie.getId(),
                movieRequest.title() != null ? movieRequest.title() : existingMovie.getTitle(),
                movieRequest.genre() != null ? movieRequest.genre() : existingMovie.getGenre(),
                movieRequest.releaseDate() != null ? movieRequest.releaseDate() : existingMovie.getReleaseDate(),
                movieRequest.classification() != null ? movieRequest.classification() : existingMovie.getClassification(),
                movieRequest.duration() != null ? movieRequest.duration() : existingMovie.getDuration(),
                movieRequest.distributor() != null ? movieRequest.distributor() : existingMovie.getDistributor(),
                movieRequest.status() != null ? movieRequest.status() : existingMovie.getStatus(),
                movieRequest.synopsis() != null ? movieRequest.synopsis() : existingMovie.getSynopsis(),
                movieRequest.marketingBudget() != null ? movieRequest.marketingBudget() : existingMovie.getMarketingBudget(),
                movieRequest.availableLanguages() != null ? movieRequest.availableLanguages() : existingMovie.getAvailableLanguages()
        );
        return movieService.updateMovie(movieId, updatedMovie);
    }

    @MutationMapping
    public void deleteMovie(@Argument String id) {
        movieService.deleteMovie(id);
    }
}
