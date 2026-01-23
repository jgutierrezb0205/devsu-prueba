package com.devsu.prueba.controller;

import com.devsu.prueba.entities.Client;
import com.devsu.prueba.entities.enums.Gender;
import com.devsu.prueba.entities.enums.Status;
import com.devsu.prueba.repository.ClientRepository;
import com.devsu.prueba.service.dto.GetClientDto;
import com.devsu.prueba.service.dto.PostClientDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ClientRepository clientRepository;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        clientRepository.deleteAll();
    }

    @Test
    void givenExistingClients_whenGetClients_thenReturnClientsList() {
        
        Client client = createTestClient("Juan Perez", "12345678");
        clientRepository.save(client);
        
        webTestClient.get()
                .uri("/api/v1/clients")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(GetClientDto.class)
                .consumeWith(response -> {
                    List<GetClientDto> clients = response.getResponseBody();
                    assertThat(clients).isNotNull();
                    assertThat(clients).hasSize(1);
                    assertThat(clients.get(0).getName()).isEqualTo("Juan Perez");
                    assertThat(clients.get(0).getIdentification()).isEqualTo("12345678");
                    assertThat(clients.get(0).getAddress()).isEqualTo("Address Test");
                });
    }

    @Test
    void givenNoClients_whenGetClients_thenReturnEmptyList() {
        
        webTestClient.get()
                .uri("/api/v1/clients")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(GetClientDto.class)
                .hasSize(0);
    }

    @Test
    void givenInvalidClientId_whenGetClientById_thenReturn404() {
        
        UUID nonExistentId = UUID.randomUUID();
        
        webTestClient.get()
                .uri("/api/v1/clients/{id}", nonExistentId)
                .exchange()
                .expectStatus().isNotFound();
    }

    private Client createTestClient(String name, String identification) {
        Client client = new Client();
        client.setId(UUID.randomUUID());
        client.setName(name);
        client.setIdentification(identification);
        client.setGender(Gender.MALE);
        client.setPassword("password123");
        client.setStatus(Status.ACTIVATE);
        client.setPhone("0996389473");
        client.setAddress("Address Test");
        return client;
    }
}