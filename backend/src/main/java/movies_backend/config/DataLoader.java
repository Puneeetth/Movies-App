package movies_backend.config;

import movies_backend.entity.Movie;
import movies_backend.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(MovieRepository repository) {
        return args -> {

            if (repository.count() == 0) {

                repository.save(new Movie(null, "RRR", "NTR", "Telugu", 8.8, 1200, 2022));
                repository.save(new Movie(null, "Pushpa", "Allu Arjun", "Telugu", 7.9, 350, 2021));
                repository.save(new Movie(null, "Jailer", "Rajinikanth", "Tamil", 8.2, 650, 2023));
                repository.save(new Movie(null, "Leo", "Vijay", "Tamil", 7.5, 600, 2023));

                // More Telugu
                repository.save(new Movie(null, "Baahubali 2", "Prabhas", "Telugu", 8.9, 1800, 2017));
                repository.save(new Movie(null, "Ala Vaikunthapurramuloo", "Allu Arjun", "Telugu", 7.6, 260, 2020));
                repository.save(new Movie(null, "Pokiri", "Mahesh Babu", "Telugu", 8.0, 150, 2006));
                repository.save(new Movie(null, "Srimanthudu", "Mahesh Babu", "Telugu", 7.8, 200, 2015));

                // More Tamil
                repository.save(new Movie(null, "Vikram", "Kamal Haasan", "Tamil", 8.4, 500, 2022));
                repository.save(new Movie(null, "Master", "Vijay", "Tamil", 7.4, 300, 2021));
                repository.save(new Movie(null, "Enthiran", "Rajinikanth", "Tamil", 7.1, 290, 2010));
                repository.save(new Movie(null, "Kaithi", "Karthi", "Tamil", 8.5, 105, 2019));
            }
        };
    }
}