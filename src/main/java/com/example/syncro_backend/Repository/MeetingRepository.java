package com.example.syncro_backend.Repository;

import com.example.syncro_backend.Entity.Meeting;
import com.example.syncro_backend.Entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findByTeam(Team team);
}
