package com.pelicula.repository;

import com.pelicula.enums.MaterialType;
import com.pelicula.model.PromotionalMaterial;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromotionalMaterialRepository extends MongoRepository<PromotionalMaterial, String> {
    Optional<PromotionalMaterial> findByTypeAndMovieId(MaterialType type, String movieId);

}