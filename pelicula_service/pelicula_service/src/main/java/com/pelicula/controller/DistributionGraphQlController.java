package com.pelicula.controller;

import com.pelicula.dto.DistributionRequest;
import com.pelicula.model.Distribution;
import com.pelicula.service.DistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class DistributionGraphQlController {
    @Autowired
    private DistributionService distributionService;


    @QueryMapping
    public List<Distribution> getAllDistributions() {
        return distributionService.getAllDistributions();
    }

    @QueryMapping
    public Distribution getDistributionById(@Argument String distributionId) {
        return distributionService.getDistributionById(distributionId);
    }

    @MutationMapping
    public Distribution createDistribution(@Argument DistributionRequest distributionRequest) {
        return distributionService.createDistribution(distributionRequest);
    }

    @MutationMapping
    public Distribution updateDistribution(@Argument String distributionId, @Argument DistributionRequest distributionRequest) {
        return distributionService.updateDistribution(distributionId, distributionRequest);
    }
}
