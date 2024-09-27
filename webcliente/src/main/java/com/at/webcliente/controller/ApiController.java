package com.at.webcliente.controller;

import com.at.webcliente.client.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final ApiClient apiClient;

    @Autowired
    public ApiController(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @GetMapping("/post/{id}")
    public Mono<String> getPostById(@PathVariable String id) {
        return apiClient.getPostById(id);
    }

    @GetMapping("/posts")
    public Mono<String> getAllPosts() {
        return apiClient.getAllPosts();
    }

}
