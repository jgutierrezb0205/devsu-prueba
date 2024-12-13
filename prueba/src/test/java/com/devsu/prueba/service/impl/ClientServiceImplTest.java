package com.devsu.prueba.service.impl;

import com.devsu.prueba.entities.Client;
import com.devsu.prueba.entities.enums.Gender;
import com.devsu.prueba.entities.enums.Status;
import com.devsu.prueba.repository.ClientRepository;
import com.devsu.prueba.service.dto.GetClientDto;
import com.devsu.prueba.service.mapper.ClientMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    ClientMapper clientMapper;

    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    ClientServiceImpl clientService;

    @Test
    void getClients_Ok() {
        Mockito.when(clientRepository.findAll())
                .thenReturn(List.of(generateClient()));
        Mockito.when(clientMapper.toGetClientDto(any()))
                .thenReturn(generateGetClientDto());

        StepVerifier.create(clientService.getClients())
                .expectNextMatches(response -> !response.getAddress().isEmpty()).verifyComplete();
    }

    private static Client generateClient() {
        Client client = new Client();

        client.setId(UUID.randomUUID());
        client.setStatus(Status.ACTIVATE);
        client.setAddress("Address Test");
        client.setName("Juan Perez");
        client.setIdentification("0987654321");
        client.setPhone("0909090909");
        client.setPassword("password");
        client.setGender(Gender.MALE);

        return client;
    }

    private static GetClientDto generateGetClientDto() {
        GetClientDto getClientDto = new GetClientDto();

        getClientDto.setId(UUID.randomUUID());
        getClientDto.setStatus(Status.ACTIVATE);
        getClientDto.setAddress("Address Test");
        getClientDto.setName("Juan Perez");
        getClientDto.setIdentification("0987654321");
        getClientDto.setPhone("0909090909");
        getClientDto.setPassword("password");
        getClientDto.setGender(Gender.MALE);

        return getClientDto;
    }
}