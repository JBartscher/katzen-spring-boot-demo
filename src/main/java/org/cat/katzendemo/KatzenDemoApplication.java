package org.cat.katzendemo;

import org.cat.katzendemo.model.Breed;
import org.cat.katzendemo.model.Cat;
import org.cat.katzendemo.model.CatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;
import java.util.stream.Stream;

@SpringBootApplication
public class KatzenDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(KatzenDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner initialize(CatRepository catRepository) {
        return args -> {
            Stream.of("Pomelo", "Sir Henry", "Barny", "Snowball", "Pablo", "Finchen").forEach(name -> {
                Cat cat = new Cat(name, Breed.getRandom(),randomWeight() );
                catRepository.save(cat);
            });
        };
    }

    private static float randomWeight()
    {
        double rangeMin = 0.0f;
        double rangeMax = 7.0f;
        Random r = new Random();
        return (float) (rangeMin + (rangeMax - rangeMin) * r.nextDouble());
    }


}
