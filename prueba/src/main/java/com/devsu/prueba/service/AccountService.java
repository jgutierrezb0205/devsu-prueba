package com.devsu.prueba.service;

import com.devsu.prueba.service.dto.GetAccountDto;
import com.devsu.prueba.service.dto.PostAccountDto;
import com.devsu.prueba.service.dto.PutAccountDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface AccountService {
    Flux<GetAccountDto> getAccounts();
    Mono<GetAccountDto> getAccountById(UUID id);
    Mono<Void> postAccount(PostAccountDto postAccountDto);
    Mono<Void> putAccount(PutAccountDto putAccountDto, UUID id);
}
