package com.example.syncro_backend.Repository;

import com.example.syncro_backend.Entity.Team;
import com.example.syncro_backend.Entity.TeamMember;
import com.example.syncro_backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
    List<TeamMember> findByTeam(Team team);
    List<TeamMember> findByUser(User user);
    Optional<TeamMember> findByTeamAndUser(Team team, User user);
}
