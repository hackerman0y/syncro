package com.example.syncro_backend.Entity;
import com.example.syncro_backend.Enum.TeamRole;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private TeamRole role;

    private LocalDateTime joinedAt;

    public void setRole(TeamRole role) {
        this.role = role;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public TeamRole getRole() {
        return role;
    }

    public User getUser() {
        return user;
    }

    public Team getTeam() {
        return team;
    }
}
