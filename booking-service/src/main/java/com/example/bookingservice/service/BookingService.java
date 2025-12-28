package com.example.bookingservice.service;

import com.example.bookingservice.client.EventClient;
import com.example.bookingservice.dto.EventDTO;
import com.example.bookingservice.dto.TicketDTO;
import com.example.bookingservice.entity.Ticket;
import com.example.bookingservice.repository.TicketRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class BookingService {

  private final TicketRepository ticketRepository;
  private final EventClient eventClient;
  private final RabbitTemplate rabbitTemplate;

  public BookingService(TicketRepository ticketRepository, EventClient eventClient, RabbitTemplate rabbitTemplate) {
    this.ticketRepository = ticketRepository;
    this.eventClient = eventClient;
    this.rabbitTemplate = rabbitTemplate;
  }

  /**
   * Books a ticket for a specific event.
   * 1. Verifies event availability synchronously via Feign Client.
   * 2. Reduces the seat count in Event Service.
   * 3. Saves the ticket locally.
   * 4. Sends an asynchronous notification via RabbitMQ.
   * 
   * @param ticketDTO The booking request details
   * @return The created TicketDTO
   */
  public TicketDTO bookTicket(TicketDTO ticketDTO) {
    // 1. Check Event Availability (Sync)
    EventDTO event = eventClient.getEventById(ticketDTO.getEventId());
    if (event.getAvailableSeats() <= 0) {
      throw new RuntimeException("Event is sold out");
    }

    // 2. Reduce Seats (Sync)
    eventClient.reduceSeats(ticketDTO.getEventId(), 1);

    // 3. Save Ticket
    Ticket ticket = new Ticket();
    BeanUtils.copyProperties(ticketDTO, ticket);
    ticket.setBookingDate(LocalDateTime.now());
    Ticket savedTicket = ticketRepository.save(ticket);

    // 4. Send Notification (Async)
    try {
      String message = "Ticket booked for event: " + event.getTitle() + " by attendee ID: " + ticketDTO.getAttendeeId();
      rabbitTemplate.convertAndSend("bookingQueue", message);
      System.out.println("Sent message to RabbitMQ: " + message);
    } catch (Exception e) {
      System.err.println("Failed to send RabbitMQ notification: " + e.getMessage());
      // Continue without failing the booking
    }

    return mapToDTO(savedTicket);
  }

  private TicketDTO mapToDTO(Ticket ticket) {
    TicketDTO dto = new TicketDTO();
    BeanUtils.copyProperties(ticket, dto);
    return dto;
  }
}
