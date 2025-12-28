package com.example.eventservice.service;

import com.example.eventservice.dto.EventDTO;
import com.example.eventservice.entity.Event;
import com.example.eventservice.repository.EventRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventService {

  private final EventRepository eventRepository;

  public EventService(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  /**
   * Retrieves all events from the database and converts them to DTOs.
   * 
   * @return List of EventDTO
   */
  public List<EventDTO> getAllEvents() {
    return eventRepository.findAll().stream()
        .map(this::mapToDTO)
        .collect(Collectors.toList());
  }

  public EventDTO getEventById(Long id) {
    Event event = eventRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Event not found"));
    return mapToDTO(event);
  }

  public EventDTO createEvent(EventDTO eventDTO) {
    Event event = new Event();
    BeanUtils.copyProperties(eventDTO, event);
    event.setAvailableSeats(event.getCapacity()); // Initial seats = capacity
    Event savedEvent = eventRepository.save(event);
    return mapToDTO(savedEvent);
  }

  public void reduceSeats(Long id, int count) {
    Event event = eventRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Event not found"));

    if (event.getAvailableSeats() < count) {
      throw new RuntimeException("Not enough seats available");
    }

    event.setAvailableSeats(event.getAvailableSeats() - count);
    eventRepository.save(event);
  }

  private EventDTO mapToDTO(Event event) {
    EventDTO dto = new EventDTO();
    BeanUtils.copyProperties(event, dto);
    return dto;
  }
}
