package com.example.bookingservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Ticket {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long eventId;
  private Long attendeeId;
  private LocalDateTime bookingDate;

  public Ticket() {
  }

  public Ticket(Long id, Long eventId, Long attendeeId, LocalDateTime bookingDate) {
    this.id = id;
    this.eventId = eventId;
    this.attendeeId = attendeeId;
    this.bookingDate = bookingDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getEventId() {
    return eventId;
  }

  public void setEventId(Long eventId) {
    this.eventId = eventId;
  }

  public Long getAttendeeId() {
    return attendeeId;
  }

  public void setAttendeeId(Long attendeeId) {
    this.attendeeId = attendeeId;
  }

  public LocalDateTime getBookingDate() {
    return bookingDate;
  }

  public void setBookingDate(LocalDateTime bookingDate) {
    this.bookingDate = bookingDate;
  }
}
