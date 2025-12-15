package com.example.demo.service;

import com.example.demo.PetDTO;
import com.example.demo.repository.PetClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetClient petClient;
    private final ObjectMapper objectMapper;
    public PetDTO fetchPetDetails(Long id) throws JsonProcessingException {
        String json = petClient.getPetById(id).block();

        PetDTO dto = new PetDTO();

        JsonNode root = objectMapper.readTree(json);
        JsonNode root1 = root.get("data");
        JsonNode dataNode = root1;


            dto.setId(root.get("id").asLong());
            dto.setName(root.get("name").asText());
            dto.setStatus(root.get("status").asText());



        return dto;
    }
}
