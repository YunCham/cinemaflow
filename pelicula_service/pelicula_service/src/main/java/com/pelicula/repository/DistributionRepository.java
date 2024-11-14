package com.pelicula.repository;

import com.pelicula.model.Distribution;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistributionRepository extends MongoRepository<Distribution, String> {
    Optional<Distribution> findByMovieIdAndTheaterId(String movieId, String theaterId);
}