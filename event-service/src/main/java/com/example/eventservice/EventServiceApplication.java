package com.example.eventservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(EventServiceApplication.class, args);
  }

  @org.springframework.context.annotation.Bean
  public org.springframework.boot.CommandLineRunner dataLoader(
      com.example.eventservice.repository.EventRepository repository) {
    return args -> {
      if (repository.count() == 0) {
        repository.save(new com.example.eventservice.entity.Event(null, "Spring Boot Workshop",
            java.time.LocalDateTime.now().plusDays(3), "Online", 100, 100));
        repository.save(new com.example.eventservice.entity.Event(null, "Microservices Summit",
            java.time.LocalDateTime.now().plusMonths(1), "Paris", 50, 50));
        repository.save(new com.example.eventservice.entity.Event(null, "Java Conference",
            java.time.LocalDateTime.now().plusMonths(2), "Tunis", 200, 200));
      }
    };
  }
}
