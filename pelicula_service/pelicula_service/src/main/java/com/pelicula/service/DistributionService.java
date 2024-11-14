package com.pelicula.service;

import com.pelicula.dto.DistributionRequest;
import com.pelicula.enums.DistributionStatus;
import com.pelicula.enums.Role;
import com.pelicula.exception.InvalidRoleException;
import com.pelicula.model.Distribution;
import com.pelicula.repository.DistributionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class DistributionService {

    @Autowired
    private DistributionRepository distributionRepository;

    public Distribution createDistribution(DistributionRequest distributionRequest) {
        Distribution distribution = new Distribution();

        distribution.setUserId(distributionRequest.userId());
        distribution.setMovieId(distributionRequest.movieId());
        distribution.setTheaterId(distributionRequest.theaterId());
        distribution.setStatus(distributionRequest.status());

        LocalDate startLocalDate = LocalDate.parse(distributionRequest.startDate());
        LocalDate endLocalDate = LocalDate.parse(distributionRequest.endDate());

        distribution.setStartDate(startLocalDate);
        distribution.setEndDate(endLocalDate);

        distribution.setRevenue(distributionRequest.revenue());
        distribution.setViewers(distributionRequest.viewers());

        if (distributionRequest.assignedDCP() != null) {
            distribution.setAssignedDCPId(distributionRequest.assignedDCP());
        }

        String role = distribution.getStatus().name();

        if (!Role.isValid(role)) {
            throw new InvalidRoleException("Invalid status: " + distribution.getStatus());
        }

        distribution.setId(UUID.randomUUID().toString());


        return distributionRepository.save(distribution);
    }

    public Distribution updateDistribution(String id, DistributionRequest distributionRequest) {
        Optional<Distribution> existingDistributionOpt = distributionRepository.findById(id);

        if (existingDistributionOpt.isEmpty()) {
            throw new RuntimeException("Distribution not found with ID: " + id);
        }

        Distribution existingDistribution = existingDistributionOpt.get();

        // Actualiza solo si los valores no son nulos en la distributionRequest
        if (distributionRequest.userId() != null) {
            existingDistribution.setUserId(distributionRequest.userId());
        }
        if (distributionRequest.movieId() != null) {
            existingDistribution.setMovieId(distributionRequest.movieId());
        }
        if (distributionRequest.theaterId() != null) {
            existingDistribution.setTheaterId(distributionRequest.theaterId());
        }
        if (distributionRequest.status() != null) {
            existingDistribution.setStatus(distributionRequest.status());
        }
        if (distributionRequest.startDate() != null) {
            LocalDate startLocalDate = LocalDate.parse(distributionRequest.startDate());
            existingDistribution.setStartDate(startLocalDate);
        }
        if (distributionRequest.endDate() != null) {
            LocalDate endLocalDate = LocalDate.parse(distributionRequest.endDate());
            existingDistribution.setEndDate(endLocalDate);
        }
        if (distributionRequest.revenue() != null) {
            existingDistribution.setRevenue(distributionRequest.revenue());
        }
        if (distributionRequest.viewers() != null) {
            existingDistribution.setViewers(distributionRequest.viewers());
        }
        if (distributionRequest.assignedDCP() != null) {
            existingDistribution.setAssignedDCPId(distributionRequest.assignedDCP());
        }

        return distributionRepository.save(existingDistribution);
    }


    public Distribution getDistributionById(String id) {
        return distributionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Distribution not found with id: " + id));
    }

    public List<Distribution> getAllDistributions() {
        return distributionRepository.findAll();
    }

}