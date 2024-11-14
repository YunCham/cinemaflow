package com.pelicula.service;

import com.pelicula.model.Movie;
import com.pelicula.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie createMovie(Movie movie) {

        movie.setId(UUID.randomUUID().toString());

        return movieRepository.save(movie);
    }

    public Movie updateMovie(String id, Movie updatedMovie) {
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with ID: " + id));

        if (updatedMovie.getTitle() != null) {
            existingMovie.setTitle(updatedMovie.getTitle());
        }
        if (updatedMovie.getGenre() != null) {
            existingMovie.setGenre(updatedMovie.getGenre());
        }
        if (updatedMovie.getReleaseDate() != null) {
            existingMovie.setReleaseDate(updatedMovie.getReleaseDate());
        }
        if (updatedMovie.getClassification() != null) {
            existingMovie.setClassification(updatedMovie.getClassification());
        }
        if (updatedMovie.getDuration() != null) {
            existingMovie.setDuration(updatedMovie.getDuration());
        }
        if (updatedMovie.getDistributor() != null) {
            existingMovie.setDistributor(updatedMovie.getDistributor());
        }
        if (updatedMovie.getStatus() != null) {
            existingMovie.setStatus(updatedMovie.getStatus());
        }
        if (updatedMovie.getSynopsis() != null) {
            existingMovie.setSynopsis(updatedMovie.getSynopsis());
        }
        if (updatedMovie.getMarketingBudget() != null) {
            existingMovie.setMarketingBudget(updatedMovie.getMarketingBudget());
        }
        if (updatedMovie.getAvailableLanguages() != null) {
            existingMovie.setAvailableLanguages(updatedMovie.getAvailableLanguages());
        }

        return movieRepository.save(existingMovie);
    }

    public Movie getMovieById(String id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with ID: " + id));
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public void deleteMovie(String id) {
        movieRepository.deleteById(id);
    }
}