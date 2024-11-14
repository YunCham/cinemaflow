package com.pelicula.seeder;

import com.pelicula.enums.DCPStatus;
import com.pelicula.enums.MaterialStatus;
import com.pelicula.enums.MaterialType;
import com.pelicula.enums.MovieStatus;
import com.pelicula.model.*;
import com.pelicula.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

import static com.pelicula.enums.DistributionStatus.SCHEDULED;

@Component
@Profile("dev")
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private DistributionRepository distributionRepository;

    @Autowired
    private PromotionalMaterialRepository promotionalMaterialRepository;

    @Autowired
    private DCPInventoryRepository dcpInventoryRepository;

    @Override
    public void run(String... args) throws Exception {

        // Crear usuario si no existe
        User user = userRepository.findByEmail("Admin_0000000@gmail.com").orElseGet(() -> {
            User newUser = new User();
            newUser.setName("Admin");
            newUser.setEmail("Admin@gmail.com");
            return userRepository.save(newUser);
        });
        System.out.println("User created or found: " + user.getId());

        // Crear película si no existe
        Movie movie = movieRepository.findByTitle("Inception_0000000").orElseGet(() -> {
            Movie newMovie = new Movie(
                    null,
                    "Inception_0000000",
                    "Sci-Fi",
                    LocalDate.now(),
                    "PG-13",
                    "148 minutes",
                    "Warner Bros.",
                    MovieStatus.IN_THEATERS,
                    "A thief who steals corporate secrets through the use of dream-sharing technology...",
                    160000000.0,
                    Arrays.asList("English", "Spanish", "French")
            );
            return movieRepository.save(newMovie);
        });
        System.out.println("Movie created or found: " + movie.getId());

        // Crear teatros si no existen
        Theater theater1 = theaterRepository.findByName("Cineplex Central_0000000").orElseGet(() -> {
            Theater newTheater = new Theater();
            newTheater.setName("Cineplex Central_0000000");
            newTheater.setAddress("123 Main Street");
            newTheater.setCity("Springfield");
            newTheater.setContactPerson("John Doe");
            newTheater.setEmail("contact@cineplexcentral.com");
            newTheater.setPhone("+1-555-123-4567");
            newTheater.setIsActive(true);
            newTheater.setRooms(Arrays.asList(
                    new Theater.Room("1", 100),
                    new Theater.Room("2", 150)
            ));
            return theaterRepository.save(newTheater);
        });

        Theater theater2 = theaterRepository.findByName("Grand Cinema_0000000").orElseGet(() -> {
            Theater newTheater = new Theater();
            newTheater.setName("Grand Cinema_0000000");
            newTheater.setAddress("456 Elm Street");
            newTheater.setCity("Shelbyville");
            newTheater.setContactPerson("Jane Smith");
            newTheater.setEmail("info@grandcinema.com");
            newTheater.setPhone("+1-555-987-6543");
            newTheater.setIsActive(true);
            newTheater.setRooms(Arrays.asList(
                    new Theater.Room("1", 120),
                    new Theater.Room("2", 200),
                    new Theater.Room("3", 250)
            ));
            return theaterRepository.save(newTheater);
        });
        System.out.println("Theaters created or found: " + theater1.getId() + ", " + theater2.getId());

        // Crear distribución si no existe
        Distribution distribution = distributionRepository.findByMovieIdAndTheaterId(movie.getId(), theater1.getId()).orElseGet(() -> {
            Distribution newDistribution = new Distribution();
            newDistribution.setUserId(user.getId());
            newDistribution.setMovieId(movie.getId());
            newDistribution.setTheaterId(theater1.getId());
            newDistribution.setStartDate(LocalDate.parse("2024-11-15"));
            newDistribution.setEndDate(LocalDate.parse("2024-12-01"));
            newDistribution.setStatus(SCHEDULED);
            newDistribution.setRevenue(120000.50);
            newDistribution.setViewers(3000);
            return distributionRepository.save(newDistribution);
        });
        System.out.println("Distribution created or found: " + distribution.getId());

        // Crear inventario DCP si no existe
        DCPInventory dcp1 = dcpInventoryRepository.findByCode("DCP-001_0000000").orElseGet(() -> {
//            DCPInventory newDcp = new DCPInventory(movie.getId(), "DCP-001", DCPStatus.AVAILABLE, LocalDate.of(2024, 11, 15), "Warehouse A",
//                    Arrays.asList(
//                            new DCPInventory.DCPHistory(LocalDate.of(2024, 11, 15), DCPStatus.AVAILABLE.name(), "Received in good condition"),
//                            new DCPInventory.DCPHistory(LocalDate.of(2024, 11, 16), DCPStatus.IN_USE.name(), "Assigned to Theater")
//                    )
//            );

            DCPInventory dcp2 = new DCPInventory();
            dcp2.setMovieId("movie123");
            dcp2.setCode("DCP-001_0000000");
            dcp2.setStatus(DCPStatus.AVAILABLE);
            dcp2.setReceptionDate(LocalDate.of(2024, 11, 15));
            dcp2.setLocation("Warehouse A");
            dcp2.setHistory(Arrays.asList(
                    new DCPInventory.DCPHistory(LocalDate.of(2024, 11, 15), DCPStatus.AVAILABLE.name(), "Received in good condition"),
                    new DCPInventory.DCPHistory(LocalDate.of(2024, 11, 16), DCPStatus.IN_USE.name(), "Assigned to Theater A")
            ));
            return dcpInventoryRepository.save(dcp2);
        });


        System.out.println("DCP Inventory created or found: " + dcp1.getCode());

        // Crear material promocional si no existe
        PromotionalMaterial material1 = promotionalMaterialRepository.findByTypeAndMovieId(MaterialType.POSTER, movie.getId()).orElseGet(() -> {
            PromotionalMaterial newMaterial = new PromotionalMaterial();
            newMaterial.setMovieId(movie.getId());
            newMaterial.setType(MaterialType.POSTER);
            newMaterial.setQuantity(100);
            newMaterial.setWarehouseLocation("Warehouse A");
            newMaterial.setStatus(MaterialStatus.AVAILABLE);
            newMaterial.setDistributions(Arrays.asList(
                    new PromotionalMaterial.Distribution(theater1.getId(), 20, LocalDate.now()),
                    new PromotionalMaterial.Distribution(theater2.getId(), 30, LocalDate.now())
            ));
            return promotionalMaterialRepository.save(newMaterial);
        });

        System.out.println("Promotional Material created or found: " + material1.getId());
    }
}