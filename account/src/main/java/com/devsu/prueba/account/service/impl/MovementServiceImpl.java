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
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovementServiceImpl implements MovementService {

    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final MovementMapper movementMapper;

    @Transactional
    public Mono<Void> postMovement(PostMovementDto postMovementDto) {
        return Mono.justOrEmpty(accountRepository.findById(postMovementDto.getAccountId()))
                .switchIfEmpty(Mono.error(new DevsuNotFoundException("Not found account: " + postMovementDto.getAccountId())))
                .map(account -> {
                    
                    BigDecimal currentBalance = account.getBalance();
                    BigDecimal movementValue = postMovementDto.getValue();
                    BigDecimal newBalance;
                    
                    if (postMovementDto.getMovementType().equals(MovementType.CREDIT)) {
                        newBalance = currentBalance.add(movementValue);
                    } else if (postMovementDto.getMovementType().equals(MovementType.DEBIT)) {

                        if (currentBalance.compareTo(movementValue) <= 0) {
                            throw new DevsuBadRequestException("Insufficient balance. Current balance: " + currentBalance + ", Required: " + movementValue);
                        }

                        newBalance = currentBalance.subtract(movementValue);
                    } else {
                        throw new DevsuBadRequestException("Invalid movement type");
                    }
                    
                    // Actualizar el balance de la cuenta
                    account.setBalance(newBalance.doubleValue());
                    accountRepository.save(account);

                    // Crear el movimiento
                    Movement movement = movementMapper.toMovement(postMovementDto);
                    movement.setMovementType(postMovementDto.getMovementType());
                    movement.setDate(postMovementDto.getDate());
                    movement.setValue(postMovementDto.getValue());
                    movement.setBalance(newBalance.doubleValue());
                    movement.setAccount(account);

                    movementRepository.save(movement);

                    return movement;
                })
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
