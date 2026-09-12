package com.example.syncro_backend.Controller;

import com.example.syncro_backend.Entity.Meeting;
import com.example.syncro_backend.Entity.Team;
import com.example.syncro_backend.Repository.MeetingRepository;
import com.example.syncro_backend.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meetings")
public class MeetingController {

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping
    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }

    @GetMapping("/team/{teamId}")
    public List<Meeting> getMeetingsByTeam(@PathVariable Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));
        return meetingRepository.findByTeam(team);
    }

    @PostMapping
    public Meeting createMeeting(@RequestBody Meeting meeting) {
        return meetingRepository.save(meeting);
    }
}