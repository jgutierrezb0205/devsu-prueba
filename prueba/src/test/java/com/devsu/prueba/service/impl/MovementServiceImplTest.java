package com.devsu.prueba.service.impl;

import com.devsu.prueba.clients.impl.WebClientAccountImpl;
import com.devsu.prueba.entities.enums.MovementType;
import com.devsu.prueba.service.dto.PostMovementDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovementServiceImplTest {
    
    @Mock
    WebClientAccountImpl webClientAccount;

    @InjectMocks
    MovementServiceImpl movementService;

    @Test
    void givenValidMovementData_whenPostMovement_thenSaveSuccessfully() {
        
        UUID accountId = UUID.randomUUID();
        PostMovementDto postMovementDto = generatePostMovementDto(accountId, MovementType.CREDIT, new BigDecimal("100.0"));
        
        when(webClientAccount.postMovement(any(PostMovementDto.class)))
                .thenReturn(Mono.empty());
                
        StepVerifier.create(movementService.postMovement(postMovementDto))
                .expectComplete()
                .verify();

        verify(webClientAccount, times(1)).postMovement(any(PostMovementDto.class));
    }

    @Test
    void givenWebclientError_whenPostMovement_thenPropagateError() {
        
        UUID accountId = UUID.randomUUID();
        PostMovementDto postMovementDto = generatePostMovementDto(accountId, MovementType.CREDIT, new BigDecimal("100.0"));
        RuntimeException expectedException = new RuntimeException("WebClient communication error");
        
        when(webClientAccount.postMovement(any(PostMovementDto.class)))
                .thenReturn(Mono.error(expectedException));
                
        StepVerifier.create(movementService.postMovement(postMovementDto))
                .expectErrorMatches(throwable -> 
                    throwable instanceof RuntimeException &&
                    throwable.getMessage().equals("WebClient communication error"))
                .verify();

        verify(webClientAccount, times(1)).postMovement(any(PostMovementDto.class));
    }

    private static PostMovementDto generatePostMovementDto(UUID accountId, MovementType movementType, BigDecimal value) {
        PostMovementDto postMovementDto = new PostMovementDto();
        postMovementDto.setMovementType(movementType);
        postMovementDto.setDate(LocalDateTime.of(2024, 12, 12, 8, 0, 0));
        postMovementDto.setValue(value);
        postMovementDto.setAccountId(accountId);
        return postMovementDto;
    }
}