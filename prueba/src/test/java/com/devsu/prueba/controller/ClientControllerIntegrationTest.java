package com.devsu.prueba.controller;

import com.devsu.prueba.entities.Client;
import com.devsu.prueba.entities.enums.Gender;
import com.devsu.prueba.entities.enums.Status;
import com.devsu.prueba.repository.ClientRepository;
import com.devsu.prueba.service.dto.GetClientDto;
import com.devsu.prueba.service.mapper.ClientMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ClientControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        clientRepository.deleteAll();
    }

    @Test
    void getClients() {

        Client client = new Client();
        client.setId(UUID.randomUUID());
        client.setName("Juan Perez");
        client.setIdentification("12345678");
        client.setGender(Gender.MALE);
        client.setPassword("12340");
        client.setStatus(Status.ACTIVATE);
        client.setPhone("0996389473");
        client.setAddress("Address Test");

        clientRepository.save(client);

        webTestClient.get().uri("/api/v1/clients")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(GetClientDto.class)
                .consumeWith(response -> {

                    List<GetClientDto> getClientDtos = response.getResponseBody();
                    assertThat(getClientDtos.get(0).getName(), is("Juan Perez"));
                    assertThat(getClientDtos.get(0).getIdentification(), is("12345678"));
                });
    }
}