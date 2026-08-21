package com.example.syncro_backend.Repository;

import com.example.syncro_backend.Entity.Task;
import com.example.syncro_backend.Entity.Team;
import com.example.syncro_backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTeam(Team team);
    List<Task> findByAssignedTo(User user);
}
