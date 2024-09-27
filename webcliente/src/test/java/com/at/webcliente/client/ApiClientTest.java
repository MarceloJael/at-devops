package com.at.webcliente.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class ApiClientTest {

    private static WireMockServer wireMockServer;

    @BeforeAll
    public static void startWireMockServer() {
        wireMockServer = new WireMockServer(wireMockConfig().port(8080));
        wireMockServer.start();
    }

    @AfterAll
    public static void stopWireMockServer() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }

    @Test
    public void testGetPostById() {
        // Configura o WireMock para retornar uma resposta simulada
        wireMockServer.stubFor(get(urlEqualTo("/posts/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"userId\":1,\"id\":1,\"title\":\"Teste de Título\",\"body\":\"Conteúdo de teste.\"}")));

        ApiClient apiClient = new ApiClient(WebClient.builder());
        Mono<String> resultado = apiClient.getPostById("1");

        StepVerifier.create(resultado)
                .expectNext("{\"userId\":1,\"id\":1,\"title\":\"Teste de Título\",\"body\":\"Conteúdo de teste.\"}")
                .verifyComplete();
    }
}