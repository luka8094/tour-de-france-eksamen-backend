package com.example.tourdefrance.controllers;


import com.example.tourdefrance.model.Team;
import com.example.tourdefrance.repositories.TeamsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/teams")
public class TeamsRestController {

    TeamsRepository teamsRepository;

    public TeamsRestController(TeamsRepository teamsRepository) {
        this.teamsRepository = teamsRepository;
    }

    //TODO: returnere et hold med de respektive ryttere (GET)
    @GetMapping
    ResponseEntity<List<Team>> getAllTeams(){
        List<Team> allTeams = teamsRepository.findAll();
        return new ResponseEntity<>(allTeams, HttpStatus.OK);
    }

    //TODO: returnere et hold med de respektive ryttere (GET)
    @GetMapping("/{id}")
    ResponseEntity<Team> getOneTeam(@PathVariable("id") Long id){
        if(teamsRepository.findById(id).isPresent()){
            Team oneTeam = teamsRepository.getById(id);
            return new ResponseEntity<>(oneTeam, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
