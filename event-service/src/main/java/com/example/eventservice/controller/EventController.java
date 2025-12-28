package com.example.eventservice.controller;

import com.example.eventservice.dto.EventDTO;
import com.example.eventservice.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

  private final EventService eventService;

  public EventController(EventService eventService) {
    this.eventService = eventService;
  }

  @GetMapping
  public List<EventDTO> getAllEvents() {
    return eventService.getAllEvents();
  }

  @GetMapping("/{id}")
  public EventDTO getEventById(@PathVariable Long id) {
    return eventService.getEventById(id);
  }

  @PostMapping
  public EventDTO createEvent(@RequestBody EventDTO eventDTO) {
    return eventService.createEvent(eventDTO);
  }

  @PutMapping("/{id}/reduce-seats")
  public void reduceSeats(@PathVariable Long id, @RequestParam int count) {
    eventService.reduceSeats(id, count);
  }
}
