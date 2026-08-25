package com.example.syncro_backend.Service;

import com.example.syncro_backend.Entity.Team;
import com.example.syncro_backend.Entity.TeamMember;
import com.example.syncro_backend.Enum.TeamRole;
import com.example.syncro_backend.Repository.TeamRepository;
import com.example.syncro_backend.Repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    public Team createTeamWithOwner(Team team) {
        Team savedTeam = teamRepository.save(team);

        TeamMember ownerMembership = new TeamMember();
        ownerMembership.setTeam(savedTeam);
        ownerMembership.setUser(savedTeam.getOwner());
        ownerMembership.setRole(TeamRole.OWNER);
        ownerMembership.setJoinedAt(LocalDateTime.now());
        teamMemberRepository.save(ownerMembership);

        return savedTeam;
    }
}