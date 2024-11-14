package com.pelicula.repository;


import com.pelicula.model.DCPInventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DCPInventoryRepository extends MongoRepository<DCPInventory, String> {
    Optional<DCPInventory> findByCode(String code);

}
