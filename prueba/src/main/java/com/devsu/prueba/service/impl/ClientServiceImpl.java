package com.devsu.prueba.service.impl;

import com.devsu.prueba.exception.DevsuFailureException;
import com.devsu.prueba.exception.DevsuNotFoundException;
import com.devsu.prueba.repository.ClientRepository;
import com.devsu.prueba.service.ClientService;
import com.devsu.prueba.service.dto.GetClientDto;
import com.devsu.prueba.service.dto.PostClientDto;
import com.devsu.prueba.service.dto.PutClientDto;
import com.devsu.prueba.service.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public Flux<GetClientDto> getClients() {
        return Flux.fromIterable(clientRepository.findAll())
                .switchIfEmpty(Mono.error(new DevsuNotFoundException("Not found clients")))
                .map(clientMapper::toGetClientDto);
    }

    public Mono<GetClientDto> getClientById(UUID id) {
        return Mono.justOrEmpty(clientRepository.findById(id))
                .switchIfEmpty(Mono.error(new DevsuNotFoundException("Not found client: " + id)))
                .map(clientMapper::toGetClientDto);
    }

    public Mono<Void> postClient(PostClientDto postClientDto) {
        return Mono.just(postClientDto)
                .map(clientMapper::toClient)
                .map(clientRepository::save)
                .doOnError(throwable -> log.error("Error saved client: {}", throwable.getMessage()))
                .onErrorResume(throwable -> Mono.error(new DevsuFailureException("Error saved client")))
                .then();
    }

    public Mono<Void> putClient(PutClientDto putClientDto, UUID id) {
        return Mono.justOrEmpty(clientRepository.findById(id))
                .switchIfEmpty(Mono.error(new DevsuNotFoundException("Not found client: " + id)))
                .map(client -> {
                    client.setName(putClientDto.getName());
                    client.setPhone(putClientDto.getPhone());
                    client.setStatus(putClientDto.getStatus());
                    client.setAddress(putClientDto.getAddress());
                    client.setPassword(putClientDto.getPassword());
                    client.setIdentification(putClientDto.getIdentification());

                    return clientRepository.save(client);
                })
                .doOnError(throwable -> log.error("Error updated client: {}", throwable.getMessage()))
                .onErrorResume(throwable -> Mono.error(new DevsuFailureException("Error updated client")))
                .then();
    }

    public Mono<Void> deleteClient(UUID id) {
        return Mono.justOrEmpty(clientRepository.findById(id))
                .switchIfEmpty(Mono.error(new DevsuNotFoundException("Not found client: " + id)))
                .map(client -> {
                    clientRepository.delete(client);
                    return client;
                })
                .doOnError(throwable -> log.error("Error deleted client: {}", throwable.getMessage()))
                .onErrorResume(throwable -> Mono.error(new DevsuFailureException("Error deleted client")))
                .then();
    }
}
