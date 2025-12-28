package com.example.bookingservice.client;

import com.example.bookingservice.dto.EventDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "event-service")
public interface EventClient {

  @GetMapping("/api/events/{id}")
  EventDTO getEventById(@PathVariable Long id);

  @PutMapping("/api/events/{id}/reduce-seats")
  void reduceSeats(@PathVariable Long id, @RequestParam int count);
}
