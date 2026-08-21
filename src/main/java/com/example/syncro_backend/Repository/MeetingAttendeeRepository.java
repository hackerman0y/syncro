package com.example.syncro_backend.Repository;

import com.example.syncro_backend.Entity.Meeting;
import com.example.syncro_backend.Entity.MeetingAttendee;
import com.example.syncro_backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingAttendeeRepository extends JpaRepository<MeetingAttendee, Long> {
    List<MeetingAttendee> findByMeeting(Meeting meeting);
    List<MeetingAttendee> findByUser(User user);
}
