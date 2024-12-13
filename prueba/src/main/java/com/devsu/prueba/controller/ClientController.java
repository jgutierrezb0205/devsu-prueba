package com.devsu.prueba.controller;

import com.devsu.prueba.entities.Client;
import com.devsu.prueba.repository.ClientRepository;
import com.devsu.prueba.service.ClientService;
import com.devsu.prueba.service.dto.GetClientDto;
import com.devsu.prueba.service.dto.PostClientDto;
import com.devsu.prueba.service.dto.PutClientDto;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<Flux<GetClientDto>> getClients() {
        return ResponseEntity.ok(clientService.getClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<GetClientDto>> getClientById(@PathVariable UUID id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @PostMapping
    public ResponseEntity<Mono<Void>> postClient(@Valid @RequestBody PostClientDto postClientDto) {
        return new ResponseEntity<>(clientService.postClient(postClientDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mono<Void>> putClient(@PathVariable UUID id, @RequestBody PutClientDto putClientDto) {
        return new ResponseEntity<>(clientService.putClient(putClientDto, id), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mono<Void>> deleteClient(@PathVariable UUID id) {
        return new ResponseEntity<>(clientService.deleteClient(id), HttpStatus.NO_CONTENT);
    }
}