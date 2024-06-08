package ru.javakira.rucscheduleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RucScheduleApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(RucScheduleApiApplication.class, args);
    }
}
