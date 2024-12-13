package com.devsu.prueba.service.impl;

import com.devsu.prueba.clients.impl.WebClientAccountImpl;
import com.devsu.prueba.entities.Account;
import com.devsu.prueba.entities.Movement;
import com.devsu.prueba.entities.enums.AccountType;
import com.devsu.prueba.entities.enums.MovementType;
import com.devsu.prueba.entities.enums.Status;
import com.devsu.prueba.repository.AccountRepository;
import com.devsu.prueba.service.dto.PostMovementDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovementServiceImplTest {
    @Mock
    WebClientAccountImpl webClientAccount;

    @InjectMocks
    MovementServiceImpl movementService;

    @Test
    void postMovement_Ok() {
        UUID accountId = UUID.randomUUID();

        when(webClientAccount.postMovement(any()))
                .thenReturn(Mono.empty());

        StepVerifier.create(movementService.postMovement(generatePostMovementDto(accountId)))
                .expectComplete()
                .verify();
    }

    private static PostMovementDto generatePostMovementDto(UUID accountId){
        PostMovementDto postMovementDto = new PostMovementDto();

        postMovementDto.setMovementType(MovementType.CREDIT);
        postMovementDto.setDate(LocalDateTime.of(2024,12,12,8,0,0));
        postMovementDto.setValue(100);
        postMovementDto.setAccountId(accountId);

        return postMovementDto;
    }
}