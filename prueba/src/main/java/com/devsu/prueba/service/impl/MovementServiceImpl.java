package com.devsu.prueba.service.impl;

import com.devsu.prueba.clients.impl.WebClientAccountImpl;
import com.devsu.prueba.service.MovementService;
import com.devsu.prueba.service.dto.GetMovementDto;
import com.devsu.prueba.service.dto.PostMovementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovementServiceImpl implements MovementService {
    private final WebClientAccountImpl webClientAccount;

    public Mono<Void> postMovement(PostMovementDto postMovementDto) {
        return webClientAccount.postMovement(postMovementDto);
    }

    public Flux<GetMovementDto> getMovementsByDateAndClientId(
            LocalDateTime initDate,
            LocalDateTime endDate,
            UUID clientId
    ) {
        return webClientAccount.getMovement(initDate,endDate,clientId);
    }
}
