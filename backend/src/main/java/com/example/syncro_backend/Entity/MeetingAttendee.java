package com.example.syncro_backend.Entity;
import com.example.syncro_backend.Entity.Meeting;
import com.example.syncro_backend.Entity.User;
import com.example.syncro_backend.Enum.MeetingStatus;
import jakarta.persistence.*;
@Entity
public class MeetingAttendee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private MeetingStatus status;

    public void setId(Long id) {
        this.id = id;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStatus(MeetingStatus status) {
        this.status = status;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public User getUser() {
        return user;
    }

    public MeetingStatus getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }
}

