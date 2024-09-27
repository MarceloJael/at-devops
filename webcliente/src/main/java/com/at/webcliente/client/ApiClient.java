package com.at.webcliente.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ApiClient {

    private final WebClient webClient;

    public ApiClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://jsonplaceholder.typicode.com").build();
    }

    public Mono<String> getPostById(String id) {
        return this.webClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(e -> Mono.just("Erro ao buscar recurso: " + e.getMessage()));
    }

    public Mono<String> getAllPosts() {
        return this.webClient.get()
                .uri("/posts")
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(e -> Mono.just("Erro ao buscar recursos: " + e.getMessage()));
    }

}
