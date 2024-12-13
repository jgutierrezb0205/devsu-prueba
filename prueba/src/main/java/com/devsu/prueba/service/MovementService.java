package com.devsu.prueba.service;

import com.devsu.prueba.service.dto.GetMovementDto;
import com.devsu.prueba.service.dto.PostMovementDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

public interface MovementService {
    Mono<Void> postMovement(PostMovementDto postMovementDto);
    Flux<GetMovementDto> getMovementsByDateAndClientId(
            LocalDateTime initDate,
            LocalDateTime endDate,
            UUID clientId
    );
}
