package com.devsu.prueba.service.impl;

import com.devsu.prueba.entities.Client;
import com.devsu.prueba.entities.enums.Gender;
import com.devsu.prueba.entities.enums.Status;
import com.devsu.prueba.exception.DevsuNotFoundException;
import com.devsu.prueba.repository.ClientRepository;
import com.devsu.prueba.service.dto.GetClientDto;
import com.devsu.prueba.service.dto.PostClientDto;
import com.devsu.prueba.service.mapper.ClientMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    ClientMapper clientMapper;

    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    ClientServiceImpl clientService;

    @Test
    void givenExistingClients_whenGetClients_thenReturnClientsList() {

        Client client = generateClient();
        GetClientDto expectedDto = generateGetClientDto();
        
        when(clientRepository.findAll()).thenReturn(List.of(client));
        when(clientMapper.toGetClientDto(any(Client.class))).thenReturn(expectedDto);

        StepVerifier.create(clientService.getClients())
                .assertNext(response -> {
                    assertThat(response).isNotNull();
                    assertThat(response.getAddress()).isNotEmpty();
                    assertThat(response.getName()).isEqualTo("Juan Perez");
                    assertThat(response.getIdentification()).isEqualTo("0987654321");
                })
                .verifyComplete();

        verify(clientRepository, times(1)).findAll();
        verify(clientMapper, times(1)).toGetClientDto(any(Client.class));
    }

    @Test
    @DisplayName("Given no clients When getClients is called Then return empty flux")
    void givenNoClients_whenGetClients_thenReturnEmptyFlux() {
        // Arrange
        when(clientRepository.findAll()).thenReturn(Collections.emptyList());

        // Act & Assert
        StepVerifier.create(clientService.getClients())
                .verifyComplete();

        verify(clientRepository, times(1)).findAll();
        verify(clientMapper, never()).toGetClientDto(any());
    }

    @Test
    void givenInvalidClientId_whenGetClientById_thenThrowNotFoundException() {
        
        UUID nonExistentId = UUID.randomUUID();
        when(clientRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        StepVerifier.create(clientService.getClientById(nonExistentId))
                .expectErrorMatches(throwable -> 
                    throwable instanceof DevsuNotFoundException &&
                    throwable.getMessage().contains("Not found client"))
                .verify();

        verify(clientRepository, times(1)).findById(nonExistentId);
        verify(clientMapper, never()).toGetClientDto(any());
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
}