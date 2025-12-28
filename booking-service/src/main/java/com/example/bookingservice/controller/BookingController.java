package com.example.bookingservice.controller;

import com.example.bookingservice.dto.TicketDTO;
import com.example.bookingservice.service.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

  private final BookingService bookingService;

  public BookingController(BookingService bookingService) {
    this.bookingService = bookingService;
  }

  @PostMapping
  public TicketDTO bookTicket(@RequestBody TicketDTO ticketDTO) {
    return bookingService.bookTicket(ticketDTO);
  }
}
