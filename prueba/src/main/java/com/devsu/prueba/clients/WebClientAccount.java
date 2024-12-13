package com.devsu.prueba.clients;

import com.devsu.prueba.service.dto.GetMovementDto;
import com.devsu.prueba.service.dto.PostMovementDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

public interface WebClientAccount {
    Flux<GetMovementDto> getMovement(LocalDateTime initDate, LocalDateTime endDate, UUID clientId);
    Mono<Void> postMovement(PostMovementDto postMovementDto);
}
