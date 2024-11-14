package com.pelicula.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;


@Configuration
@EnableMongoAuditing
public class MongoConfig {

    @Autowired
    private MongoTemplate mongoTemplate;
//
//    @PostConstruct
//    public void initIndexes() {
//        mongoTemplate.indexOps("users")  // Nombre de la colección
//                .ensureIndex(new Index().on("email", Index.Direction.ASC).unique());
//    }


//    public void createCollections() {
//        mongoTemplate.createCollection("users");
//        mongoTemplate.createCollection("movies");
//        mongoTemplate.createCollection("theaters");
//        mongoTemplate.createCollection("distributions");
//        mongoTemplate.createCollection("dcp_inventory");
//        mongoTemplate.createCollection("promotional_material");
//    }
}
