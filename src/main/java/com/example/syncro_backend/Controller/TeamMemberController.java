package com.example.syncro_backend.Controller;

import com.example.syncro_backend.Entity.TeamMember;
import com.example.syncro_backend.Enum.TeamRole;
import com.example.syncro_backend.Repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/team-members")
public class TeamMemberController {

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @GetMapping
    public List<TeamMember> getAllTeamMembers() {
        return teamMemberRepository.findAll();
    }

    @PostMapping
    public TeamMember addTeamMember(@RequestBody TeamMember teamMember) {
        teamMember.setRole(TeamRole.MEMBER);
        teamMember.setJoinedAt(LocalDateTime.now());
        return teamMemberRepository.save(teamMember);
    }
}
