package com.example.tourdefrance.controllers;


import com.example.tourdefrance.model.Rider;
import com.example.tourdefrance.repositories.RidersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//REST controller til håndtering af ryttere ('riders')
@RestController
@CrossOrigin("*")
@RequestMapping("/api/riders")
public class RidersRestController {

    RidersRepository ridersRepository;

    //Constructor injection af ridersRepository
    public RidersRestController(RidersRepository ridersRepository) {
        this.ridersRepository = ridersRepository;
    }

    //Find alle ryttere i 'riders' tabellen
    @GetMapping
    ResponseEntity<List<Rider>> getAllRiders(){
        List<Rider> allRiders = ridersRepository.findAll();
        return new ResponseEntity<>(allRiders, HttpStatus.OK);
    }

    //Find en bestemt rytter fra 'tabellen'
    @GetMapping("/{id}")
    ResponseEntity<Rider> getOneRider(@PathVariable("id") long id){
        if(ridersRepository.findById(id).isPresent()){
            Rider foundRider = ridersRepository.getById(id);
            return new ResponseEntity<>(foundRider, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //Opret en rytter i 'riders' tabellen
    @PostMapping
    ResponseEntity<Rider> createRider(@RequestBody Rider rider){
        ridersRepository.save(rider);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Opdatere en rytter i 'riders' tabellen
    @PutMapping("/{id}")
    ResponseEntity<Rider> updateRider(@PathVariable("id") long id, @RequestBody Rider rider){
        Rider storedRider = ridersRepository.getById(id);
        if(rider != null){
            storedRider.setFirstName(rider.getFirstName());
            storedRider.setLastName(rider.getLastName());
            storedRider.setCountry(rider.getCountry());
            storedRider.setSprintTime(rider.getSprintTime());
        }
        ridersRepository.save(storedRider);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Slet en rytter i 'riders' tabellen
    @DeleteMapping("/{id}")
    ResponseEntity<Rider> deleteRider(@PathVariable("id") long id){
        Rider storedRider = ridersRepository.getById(id);
        ridersRepository.delete(storedRider);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
