package com.example.tourdefrance.controllers;


import com.example.tourdefrance.model.Rider;
import com.example.tourdefrance.repositories.RidersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/riders")
public class RidersRestController {

    RidersRepository ridersRepository;

    public RidersRestController(RidersRepository ridersRepository) {
        this.ridersRepository = ridersRepository;
    }

    //TODO: returnere alle ryttere (GET)
    @GetMapping
    ResponseEntity<List<Rider>> getAllRiders(){
        List<Rider> allRiders = ridersRepository.findAll();
        return new ResponseEntity<>(allRiders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Rider> getOneRider(@PathVariable("id") long id){
        if(ridersRepository.findById(id).isPresent()){
            Rider foundRider = ridersRepository.getById(id);
            return new ResponseEntity<>(foundRider, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //TODO: opret en rytter (POST)
    @PostMapping
    ResponseEntity<Rider> createRider(@RequestBody Rider rider){
        ridersRepository.save(rider);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //TODO: opdatere en rytter (PUT)
    @PutMapping("/{id}")
    ResponseEntity<Rider> updateRider(@PathVariable("id") long id, @RequestBody Rider rider){
        Rider storedRider = ridersRepository.getById(id);
        if(rider != null){
            storedRider.setFirstName(rider.getFirstName());
            storedRider.setLastName(rider.getLastName());
            storedRider.setCountry(rider.getCountry());
            storedRider.setTotalPoints(rider.getTotalPoints());
        }
        ridersRepository.save(storedRider);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //TODO: slet en rytter (DELETE)
    @DeleteMapping("/{id}")
    ResponseEntity<Rider> deleteRider(@PathVariable("id") long id){
        Rider storedRider = ridersRepository.getById(id);
        ridersRepository.delete(storedRider);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
