package com.pelicula.controller;

import com.pelicula.dto.TheaterRequest;
import com.pelicula.model.Theater;
import com.pelicula.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TheaterGraphQlController {
    @Autowired
    private TheaterService theaterService;

    @MutationMapping
    public Theater createTheater(@Argument TheaterRequest theaterRequest) {
        Theater theater = new Theater();
        theater.setName(theaterRequest.name());
        theater.setAddress(theaterRequest.address());
        theater.setCity(theaterRequest.city());
        theater.setContactPerson(theaterRequest.contactPerson());
        theater.setEmail(theaterRequest.email());
        theater.setPhone(theaterRequest.phone());
        theater.setRooms(theaterRequest.rooms());

        return theaterService.createTheater(theater);
    }

    @MutationMapping
    public Theater updateTheater(@Argument String theaterId, @Argument TheaterRequest theaterRequest) {
        Theater updatedTheater = new Theater();
        updatedTheater.setName(theaterRequest.name());
        updatedTheater.setAddress(theaterRequest.address());
        updatedTheater.setCity(theaterRequest.city());
        updatedTheater.setContactPerson(theaterRequest.contactPerson());
        updatedTheater.setEmail(theaterRequest.email());
        updatedTheater.setPhone(theaterRequest.phone());
        updatedTheater.setIsActive(theaterRequest.isActive());
        updatedTheater.setRooms(theaterRequest.rooms());

        return theaterService.updateTheater(theaterId, updatedTheater);
    }

    @QueryMapping
    public Theater getTheaterById(@Argument String theaterId) {
        return theaterService.getTheaterById(theaterId);
    }

    @QueryMapping
    public List<Theater> getAllTheaters() {
        return theaterService.getAllTheaters();
    }
}
