package com.example.syncro_backend.Controller;

import com.example.syncro_backend.Entity.Task;
import com.example.syncro_backend.Entity.Team;
import com.example.syncro_backend.Repository.TaskRepository;
import com.example.syncro_backend.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/team/{teamId}")
    public List<Task> getTasksByTeam(@PathVariable Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));
        return taskRepository.findByTeam(team);
    }
    @Autowired
    private TaskRepository taskRepository;
    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }
}

