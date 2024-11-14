package com.pelicula;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PeliculaServiceApplication {

	public static void main(String[] args) {
		SpringApplication. run(PeliculaServiceApplication.class, args);
	}

}
