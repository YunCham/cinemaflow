package com.pelicula.controller;

import com.pelicula.dto.DCPInventoryRequest;
import com.pelicula.model.DCPInventory;
import com.pelicula.service.DCPInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class DCPInventoryGraphQlController {

    @Autowired
    private DCPInventoryService dcpInventoryService;

    @QueryMapping
    public List<DCPInventory> getAllDCPs() {
        return dcpInventoryService.getAllDCPs();
    }

    @QueryMapping
    public DCPInventory getDCPById(@Argument String dcpId) {
        return dcpInventoryService.getDCPById(dcpId);
    }

    @MutationMapping
    public DCPInventory createDCPInventary(@Argument DCPInventoryRequest dcpRequest) {
        return dcpInventoryService.createDCP(dcpRequest);
    }

    @MutationMapping
    public DCPInventory updateDCPInventary(@Argument String dcpId, @Argument DCPInventoryRequest dcpRequest) {
        return dcpInventoryService.updateDCP(dcpId, dcpRequest);
    }
}
