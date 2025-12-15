package com.example.demo.controller;


import com.example.demo.service.PetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class PetController {

    private final PetService petService;
    @GetMapping(value = "pet/{petId}")
    public ResponseEntity<?> getPetById(@PathVariable("petId") Long petId) throws JsonProcessingException {
        return ResponseEntity.ok(petService.fetchPetDetails(petId));
    }
}
