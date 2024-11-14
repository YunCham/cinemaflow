package com.pelicula.repository;

import com.pelicula.enums.MovieStatus;
import com.pelicula.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface MovieRepository extends MongoRepository<Movie, String> {
    Optional<Movie> findByTitle(String title);
}
