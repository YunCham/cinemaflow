package com.pelicula.controller;

import com.pelicula.dto.PromotionalMaterialRequest;
import com.pelicula.model.PromotionalMaterial;
import com.pelicula.service.PromotionalMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PromotionalMaterialQraphQlController {


    @Autowired
    private PromotionalMaterialService promotionalMaterialService;

    @QueryMapping
    public List<PromotionalMaterial> getAllPromotionalMaterials() {
        return promotionalMaterialService.getAllMaterials();
    }

    @QueryMapping
    public PromotionalMaterial getMaterialById(@Argument String materialId) {
        return promotionalMaterialService.getMaterialById(materialId);
    }

    @MutationMapping
    public PromotionalMaterial createMaterial(@Argument PromotionalMaterialRequest materialRequest) {
        return promotionalMaterialService.createMaterial(materialRequest);
    }

    @MutationMapping
    public PromotionalMaterial updateMaterial(@Argument String materialId, @Argument PromotionalMaterialRequest materialRequest) {
        return promotionalMaterialService.updateMaterial(materialId, materialRequest);
    }
}
