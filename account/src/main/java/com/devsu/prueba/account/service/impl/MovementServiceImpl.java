package com.devsu.prueba.account.service.impl;

import com.devsu.prueba.account.entities.Movement;
import com.devsu.prueba.account.entities.enums.MovementType;
import com.devsu.prueba.account.exception.DevsuBadRequestException;
import com.devsu.prueba.account.exception.DevsuNotFoundException;
import com.devsu.prueba.account.repository.AccountRepository;
import com.devsu.prueba.account.repository.ClientRepository;
import com.devsu.prueba.account.repository.MovementRepository;
import com.devsu.prueba.account.service.MovementService;
import com.devsu.prueba.account.service.dto.GetMovementDto;
import com.devsu.prueba.account.service.dto.PostMovementDto;
import com.devsu.prueba.account.service.mapper.MovementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovementServiceImpl implements MovementService {

    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final MovementMapper movementMapper;

    public Mono<Void> postMovement(PostMovementDto postMovementDto) {
        return Mono.justOrEmpty(accountRepository.findById(postMovementDto.getAccountId()))
                .switchIfEmpty(Mono.error(new DevsuNotFoundException("Not found account: " + postMovementDto.getAccountId())))
                .map(account -> {
                    if(postMovementDto.getMovementType().equals(MovementType.CREDIT) && postMovementDto.getValue() < 0)
                        throw new DevsuBadRequestException("Credit movement cannot be negative");
                    else if(postMovementDto.getMovementType().equals(MovementType.DEBIT) && postMovementDto.getValue() > 0)
                        throw new DevsuBadRequestException("Debit movement cannot be positive");
                    else if (postMovementDto.getMovementType().equals(MovementType.DEBIT) && account.getInitialBalance() < postMovementDto.getValue()) {
                        throw new DevsuBadRequestException("Balance not available");
                    }

                    Movement movement = movementMapper.toMovement(postMovementDto);
                    account.setBalance(account.getInitialBalance() + postMovementDto.getValue());

                    movement.setMovementType(postMovementDto.getMovementType());
                    movement.setDate(postMovementDto.getDate());
                    movement.setValue(postMovementDto.getValue());
                    movement.setBalance(account.getInitialBalance() + postMovementDto.getValue());
                    movement.setAccount(account);

                    return movement;
                })
                .map(movementRepository::save)
                .then();
    }

    public Flux<GetMovementDto> getMovementsByDateAndClientId(
            LocalDateTime initDate,
            LocalDateTime endDate,
            UUID clientId
    ) {
        return Mono.justOrEmpty(clientRepository.findById(clientId))
                .switchIfEmpty(Mono.error(new DevsuNotFoundException("Not found client: " + clientId)))
                .flatMapMany(client -> Flux.fromIterable(movementRepository.findMovementsByDateAndClientId(
                                initDate,
                                endDate,
                                clientId
                        ))
                        .switchIfEmpty(Mono.error(new DevsuNotFoundException("Not found movements for client: " + clientId))));
    }
}
