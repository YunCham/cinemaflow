package com.pelicula.service;

import com.pelicula.dto.DCPInventoryRequest;
import com.pelicula.enums.DCPStatus;
import com.pelicula.model.DCPInventory;
import com.pelicula.repository.DCPInventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class DCPInventoryService {

    @Autowired
    private DCPInventoryRepository repository;

    public List<DCPInventory> getAllDCPs() {
        return repository.findAll();
    }

    public DCPInventory getDCPById(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("DCP not found with ID: " + id));
    }

    public DCPInventory createDCP(DCPInventoryRequest request) {
        DCPInventory dcp = new DCPInventory();
        dcp.setId(UUID.randomUUID().toString());
        dcp.setMovieId(request.movieId());
        dcp.setCode(request.code());
        dcp.setStatus(DCPStatus.valueOf(request.status().toUpperCase()));
        dcp.setReceptionDate(request.receptionDate());
        dcp.setLocation(request.location());
        return repository.save(dcp);
    }


    public DCPInventory updateDCP(String id, DCPInventoryRequest request) {
        DCPInventory existingDCP = getDCPById(id);
        existingDCP.setCode(request.code());
        existingDCP.setStatus(DCPStatus.valueOf(request.status().toUpperCase()));
        return repository.save(existingDCP);
    }
}