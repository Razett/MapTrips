package com.razett.maptrips;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MaptripsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaptripsApplication.class, args);
    }

}
