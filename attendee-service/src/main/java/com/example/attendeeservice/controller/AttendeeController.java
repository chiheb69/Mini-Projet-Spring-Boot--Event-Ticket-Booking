package com.example.attendeeservice.controller;

import com.example.attendeeservice.dto.AttendeeDTO;
import com.example.attendeeservice.service.AttendeeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendees")
public class AttendeeController {

  private final AttendeeService attendeeService;

  public AttendeeController(AttendeeService attendeeService) {
    this.attendeeService = attendeeService;
  }

  @PostMapping
  public AttendeeDTO createAttendee(@RequestBody AttendeeDTO attendeeDTO) {
    return attendeeService.createAttendee(attendeeDTO);
  }

  @GetMapping("/{id}")
  public AttendeeDTO getAttendeeById(@PathVariable Long id) {
    return attendeeService.getAttendeeById(id);
  }
}
