package com.petstore.demo.controller;

import com.petstore.demo.exception.PetNotFoundException;
import com.petstore.demo.model.Pet;
import com.petstore.demo.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PetController {
    @Autowired
    PetService petService;

    @GetMapping("/pets")
    private ResponseEntity<List<Pet>> getAllPets(@RequestParam(defaultValue = "100") int limit,  @RequestParam(required = false, defaultValue = "0") int skip) {
        return new ResponseEntity<>(petService.getPets(limit, skip),HttpStatus.OK);
    }

    @GetMapping("/pets/{id}")
    private ResponseEntity<Object> getPetById(@PathVariable("id") int id) {
        boolean isPetPresent = petService.isPetPresent(id);
        if(isPetPresent) {
            Pet pet = petService.getPetById(id);
            return new ResponseEntity<>(pet, HttpStatus.OK);
        }else {
            throw new PetNotFoundException();
        }
    }

    @DeleteMapping("/pets/{id}")
    private ResponseEntity<Object> delete(@PathVariable("id") int id) {
        boolean isPetPresent = petService.isPetPresent(id);
        if(isPetPresent){
            petService.delete(id);
            return new ResponseEntity<>("Pet with " + id + " deleted successfully", HttpStatus.OK);
        } else {
            throw new PetNotFoundException();
        }

    }

    @PostMapping("/pets")
    private ResponseEntity<Object> createPet(@RequestBody Pet pet) {
        petService.saveOrUpdate(pet);
        return new ResponseEntity<>("Pet has been created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/pets/{id}")
    private ResponseEntity<Object> updatePet(@PathVariable("id") int id, @RequestBody Pet pet){
        boolean isPetPresent = petService.isPetPresent(id);
        if(isPetPresent){
            petService.updatePet(id, pet);
            return new ResponseEntity<>("Pet with ID : " + id + " updated successfully!", HttpStatus.OK);
        }else {
            throw new PetNotFoundException();
        }

    }
}
