package com.devsu.prueba.service.impl;

import com.devsu.prueba.entities.Account;
import com.devsu.prueba.exception.DevsuFailureException;
import com.devsu.prueba.exception.DevsuNotFoundException;
import com.devsu.prueba.repository.AccountRepository;
import com.devsu.prueba.repository.ClientRepository;
import com.devsu.prueba.service.AccountService;
import com.devsu.prueba.service.dto.*;
import com.devsu.prueba.service.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    public Flux<GetAccountDto> getAccounts() {
        return Flux.fromIterable(accountRepository.findAll())
                .switchIfEmpty(Mono.error(new DevsuNotFoundException("Not found accounts")))
                .map(accountMapper::toGetAccountDto);
    }

    public Mono<GetAccountDto> getAccountById(UUID id) {
        return Mono.justOrEmpty(accountRepository.findById(id))
                .switchIfEmpty(Mono.error(new DevsuNotFoundException("Not found account: " + id)))
                .map(accountMapper::toGetAccountDto);
    }

    public Mono<Void> postAccount(PostAccountDto postAccountDto) {
        return Mono.justOrEmpty(clientRepository.findById(postAccountDto.getClientId()))
                .switchIfEmpty(Mono.error(new DevsuNotFoundException("Not found client: " + postAccountDto.getClientId())))
                .map(client -> {
                    Account account = accountMapper.toAccount(postAccountDto);
                    account.setClient(client);
                    return account;
                })
                .map(accountRepository::save)
                .doOnError(throwable -> log.error("Error saved account: {}", throwable.getMessage()))
                .onErrorResume(throwable -> Mono.error(new DevsuFailureException("Error saved account")))
                .then();
    }

    public Mono<Void> putAccount(PutAccountDto putAccountDto, UUID id) {
        return Mono.justOrEmpty(accountRepository.findById(id))
                .switchIfEmpty(Mono.error(new DevsuNotFoundException("Not found account: " + id)))
                .map(account -> {
                    account.setNumber(putAccountDto.getNumber());
                    account.setAccountType(putAccountDto.getAccountType());
                    account.setInitialBalance(putAccountDto.getInitialBalance());
                    account.setStatus(putAccountDto.getStatus());

                    return accountRepository.save(account);
                })
                .doOnError(throwable -> log.error("Error updated account: {}", throwable.getMessage()))
                .onErrorResume(throwable -> Mono.error(new DevsuFailureException("Error updated account")))
                .then();
    }
}