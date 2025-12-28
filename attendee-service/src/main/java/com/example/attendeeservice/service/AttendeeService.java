package com.example.attendeeservice.service;

import com.example.attendeeservice.dto.AttendeeDTO;
import com.example.attendeeservice.entity.Attendee;
import com.example.attendeeservice.repository.AttendeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AttendeeService {

  private final AttendeeRepository attendeeRepository;

  public AttendeeService(AttendeeRepository attendeeRepository) {
    this.attendeeRepository = attendeeRepository;
  }

  public AttendeeDTO createAttendee(AttendeeDTO attendeeDTO) {
    Attendee attendee = new Attendee();
    BeanUtils.copyProperties(attendeeDTO, attendee);
    Attendee savedAttendee = attendeeRepository.save(attendee);
    return mapToDTO(savedAttendee);
  }

  public AttendeeDTO getAttendeeById(Long id) {
    Attendee attendee = attendeeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Attendee not found"));
    return mapToDTO(attendee);
  }

  private AttendeeDTO mapToDTO(Attendee attendee) {
    AttendeeDTO dto = new AttendeeDTO();
    BeanUtils.copyProperties(attendee, dto);
    return dto;
  }
}
