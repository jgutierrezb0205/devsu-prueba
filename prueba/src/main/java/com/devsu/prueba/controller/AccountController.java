package com.devsu.prueba.controller;

import com.devsu.prueba.service.AccountService;
import com.devsu.prueba.service.dto.GetAccountDto;
import com.devsu.prueba.service.dto.PostAccountDto;
import com.devsu.prueba.service.dto.PutAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<Flux<GetAccountDto>> getAccounts() {
        return ResponseEntity.ok(accountService.getAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<GetAccountDto>> getAccountById(@PathVariable UUID id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PostMapping
    public ResponseEntity<Mono<Void>> postAccount(@RequestBody PostAccountDto postAccountDto) {
        return new ResponseEntity<>(accountService.postAccount(postAccountDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mono<Void>> putAccount(@PathVariable UUID id, @RequestBody PutAccountDto putAccountDto) {
        return new ResponseEntity<>(accountService.putAccount(putAccountDto, id), HttpStatus.NO_CONTENT);
    }
}
