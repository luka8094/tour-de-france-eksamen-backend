package com.example.tourdefrance.repositories;

import com.example.tourdefrance.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamsRepository extends JpaRepository<Team, Long> {

    Team getById(Long id);

    Team getByTeamName(String teamName);

}
