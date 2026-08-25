package com.example.syncro_backend.Repository;

import com.example.syncro_backend.Entity.Team;
import com.example.syncro_backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByOwner(User owner);

}
