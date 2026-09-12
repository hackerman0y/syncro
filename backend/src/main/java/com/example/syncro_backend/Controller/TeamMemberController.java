package com.example.syncro_backend.Controller;

import com.example.syncro_backend.Entity.Team;
import com.example.syncro_backend.Entity.TeamMember;
import com.example.syncro_backend.Entity.User;
import com.example.syncro_backend.Enum.TeamRole;
import com.example.syncro_backend.Repository.TeamMemberRepository;
import com.example.syncro_backend.Repository.TeamRepository;
import com.example.syncro_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/team-members")
public class TeamMemberController {

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<TeamMember> getAllTeamMembers() {
        return teamMemberRepository.findAll();
    }

    @GetMapping("/team/{teamId}")
    public List<TeamMember> getMembersByTeam(@PathVariable Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));
        return teamMemberRepository.findByTeam(team);
    }

    @PostMapping
    public TeamMember addTeamMember(@RequestBody TeamMember teamMember) {
        teamMember.setRole(TeamRole.MEMBER);
        teamMember.setJoinedAt(LocalDateTime.now());
        return teamMemberRepository.save(teamMember);
    }

    @PostMapping("/invite")
    public TeamMember inviteMember(@RequestBody Map<String, Object> payload) {
        Long teamId = Long.valueOf(payload.get("teamId").toString());
        String email = payload.get("email").toString();

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("No user found with that email"));

        TeamMember member = new TeamMember();
        member.setTeam(team);
        member.setUser(user);
        member.setRole(TeamRole.MEMBER);
        member.setJoinedAt(LocalDateTime.now());
        return teamMemberRepository.save(member);
    }
}