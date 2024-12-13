package com.devsu.prueba.service;

import com.devsu.prueba.entities.Client;
import com.devsu.prueba.service.dto.GetClientDto;
import com.devsu.prueba.service.dto.PostClientDto;
import com.devsu.prueba.service.dto.PutClientDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ClientService {
    Flux<GetClientDto> getClients();
    Mono<GetClientDto> getClientById(UUID id);
    Mono<Void> postClient(PostClientDto postClientDto);
    Mono<Void> putClient(PutClientDto putClientDto, UUID id);
    Mono<Void> deleteClient(UUID id);
}
