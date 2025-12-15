package com.example.demo.service;

import com.example.demo.PetDTO;
import com.example.demo.PetResponse;
import com.example.demo.repository.PetClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PetService {

    private final PetClient petClient;
    private final ObjectMapper objectMapper;

    public Optional<PetDTO> fetchPetDetails(Long id) throws JsonProcessingException {
        try {
            String json = petClient.getPetById(id).block();

            JsonNode root = objectMapper.readTree(json);

            PetDTO dto = new PetDTO();
            dto.setId(root.get("id").asLong());
            dto.setName(root.get("name").asText());
            dto.setStatus(root.get("status").asText());

            return Optional.of(dto);

        } catch (WebClientResponseException.NotFound e) {
            return Optional.empty();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error procesando JSON", e);
        }

    }
    public PetResponse createPet(PetDTO petDTO) throws JsonProcessingException {

        String jsonRequest = objectMapper.writeValueAsString(petDTO);

        String jsonResponse = petClient.createPet(jsonRequest).block();

        log.info("Response POST: {}", jsonResponse);

        JsonNode root = objectMapper.readTree(jsonResponse);
        // fecha de hoy en la variable dateCreated
        String dateCreated = java.time.LocalDate.now().toString();
        //Generar el campo del response “transactionId” con un formato de
        //UUIDv4 en la capa service.

        String transactionId = java.util.UUID.randomUUID().toString();
        PetDTO responseDto = new PetDTO();
        responseDto.setId(root.get("id").asLong());
        responseDto.setName(root.get("name").asText());
        responseDto.setStatus(root.get("status").asText());

        return new PetResponse(responseDto, dateCreated,transactionId);
    }
}
