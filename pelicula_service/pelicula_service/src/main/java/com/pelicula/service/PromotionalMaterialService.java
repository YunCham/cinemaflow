package com.pelicula.service;

import com.pelicula.dto.PromotionalMaterialRequest;
import com.pelicula.enums.MaterialStatus;
import com.pelicula.enums.MaterialType;
import com.pelicula.model.PromotionalMaterial;
import com.pelicula.repository.PromotionalMaterialRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class PromotionalMaterialService {

    @Autowired
    private PromotionalMaterialRepository repository;

    public List<PromotionalMaterial> getAllMaterials() {
        return repository.findAll();
    }

    public PromotionalMaterial getMaterialById(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Material not found with ID: " + id));
    }

    public PromotionalMaterial createMaterial(PromotionalMaterialRequest request) {
        PromotionalMaterial material = new PromotionalMaterial();
        material.setId(UUID.randomUUID().toString());
        material.setMovieId(request.movieId());
        material.setType(MaterialType.valueOf(request.type().toUpperCase()));
        material.setQuantity(request.quantity());
        material.setWarehouseLocation(request.warehouseLocation());
        material.setStatus(MaterialStatus.valueOf(request.status().toUpperCase()));
        return repository.save(material);
    }

    public PromotionalMaterial updateMaterial(String id, PromotionalMaterialRequest request) {
        PromotionalMaterial existingMaterial = getMaterialById(id);
        existingMaterial.setQuantity(request.quantity());
        existingMaterial.setStatus(MaterialStatus.valueOf(request.status().toUpperCase()));
        return repository.save(existingMaterial);
    }
}