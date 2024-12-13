package com.devsu.prueba.clients.impl;

import com.devsu.prueba.clients.WebClientAccount;
import com.devsu.prueba.config.PropertiesConfiguration;
import com.devsu.prueba.exception.DevsuNotFoundException;
import com.devsu.prueba.service.dto.GetMovementDto;
import com.devsu.prueba.service.dto.PostMovementDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebClientAccountImpl implements WebClientAccount {
    private final PropertiesConfiguration propertiesConfiguration;
    private final WebClient webClient;

    @Override
    public Flux<GetMovementDto> getMovement(
            LocalDateTime initDate,
            LocalDateTime endDate,
            UUID clientId
    ) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(propertiesConfiguration.getClientAccountMovementsPath())
                        .queryParam("init_date", initDate)
                        .queryParam("end_date", endDate)
                        .queryParam("client_id", clientId)
                        .build())
                .retrieve()
                .bodyToFlux(GetMovementDto.class)
                .doOnError(throwable -> log.error("WebClientAccountImpl -> Error obtained movements: {}", throwable.getMessage()))
                .onErrorResume(throwable -> Mono.error(new DevsuNotFoundException("Error obtained movements")));
    }

    @Override
    public Mono<Void> postMovement(PostMovementDto postMovementDto) {
        return webClient.post()
                .uri(propertiesConfiguration.getClientAccountMovementsPath())
                .bodyValue(postMovementDto)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnError(throwable -> log.error("Error saved movement: {}", throwable.getMessage()))
                .onErrorResume(throwable -> Mono.error(new DevsuNotFoundException("Error saved movements")));
    }
}