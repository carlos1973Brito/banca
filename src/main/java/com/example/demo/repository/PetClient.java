package com.example.demo.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PetClient {
    private final WebClient webClient = WebClient.create("https://petstore.swagger.io/v2/");

    public Mono<String> getPetById(Long id){
        return webClient.get()
                .uri("/pet/{id}", id)
                .retrieve()
                .bodyToMono(String.class);

    }

}

