package com.example.bookingservice.dto;

import java.time.LocalDateTime;

public class EventDTO {
  private Long id;
  private String title;
  private LocalDateTime date;
  private String location;
  private int capacity;
  private int availableSeats;

  public EventDTO() {
  }

  public EventDTO(Long id, String title, LocalDateTime date, String location, int capacity, int availableSeats) {
    this.id = id;
    this.title = title;
    this.date = date;
    this.location = location;
    this.capacity = capacity;
    this.availableSeats = availableSeats;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public int getAvailableSeats() {
    return availableSeats;
  }

  public void setAvailableSeats(int availableSeats) {
    this.availableSeats = availableSeats;
  }
}
