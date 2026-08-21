package com.example.syncro_backend.Controller;

import com.example.syncro_backend.Entity.MeetingAttendee;
import com.example.syncro_backend.Repository.MeetingAttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meeting-attendees")
public class MeetingAttendeeController {

    @Autowired
    private MeetingAttendeeRepository meetingAttendeeRepository;

    @GetMapping
    public List<MeetingAttendee> getAllMeetingAttendees() {
        return meetingAttendeeRepository.findAll();
    }

    @PostMapping
    public MeetingAttendee createMeetingAttendee(@RequestBody MeetingAttendee meetingAttendee) {
        return meetingAttendeeRepository.save(meetingAttendee);
    }
}