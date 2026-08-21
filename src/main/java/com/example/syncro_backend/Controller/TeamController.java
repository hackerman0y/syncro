package com.example.syncro_backend.Controller;
import com.example.syncro_backend.Entity.Team;
import com.example.syncro_backend.Repository.TeamRepository;
import com.example.syncro_backend.Service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamService teamService;

    @GetMapping
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamService.createTeamWithOwner(team);
    }
}
