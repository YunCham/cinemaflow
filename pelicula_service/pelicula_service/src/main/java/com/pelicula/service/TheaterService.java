package com.pelicula.service;

import com.pelicula.model.Theater;
import com.pelicula.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    public Theater createTheater(Theater theater) {
        theater.setId(UUID.randomUUID().toString());
        theater.setIsActive(true);
        return theaterRepository.save(theater);
    }

    public Theater updateTheater(String theaterId, Theater updatedTheater) {
        Theater existingTheater = theaterRepository.findById(theaterId)
                .orElseThrow(() -> new RuntimeException("Theater not found with ID: " + theaterId));

        if (updatedTheater.getName() != null) existingTheater.setName(updatedTheater.getName());
        if (updatedTheater.getAddress() != null) existingTheater.setAddress(updatedTheater.getAddress());
        if (updatedTheater.getCity() != null) existingTheater.setCity(updatedTheater.getCity());
        if (updatedTheater.getContactPerson() != null) existingTheater.setContactPerson(updatedTheater.getContactPerson());
        if (updatedTheater.getEmail() != null) existingTheater.setEmail(updatedTheater.getEmail());
        if (updatedTheater.getPhone() != null) existingTheater.setPhone(updatedTheater.getPhone());
        if (updatedTheater.getIsActive() != null) existingTheater.setIsActive(updatedTheater.getIsActive());
        if (updatedTheater.getRooms() != null) existingTheater.setRooms(updatedTheater.getRooms());

        return theaterRepository.save(existingTheater);
    }

    public Theater getTheaterById(String id) {
        return theaterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Theater not found with ID: " + id));
    }

    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }

    public void deleteTheater(String id) {
        theaterRepository.deleteById(id);
    }
}

