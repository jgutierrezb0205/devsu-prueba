package com.devsu.prueba.controller;

import com.devsu.prueba.service.MovementService;
import com.devsu.prueba.service.dto.GetMovementDto;
import com.devsu.prueba.service.dto.PostMovementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/movements")
@RequiredArgsConstructor
public class MovementController {

    private final MovementService movementService;

    @PostMapping
    public ResponseEntity<Mono<Void>> postMovement(@RequestBody PostMovementDto postMovementDto) {
        return new ResponseEntity<>(movementService.postMovement(postMovementDto), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<Flux<GetMovementDto>> getMovements(
            @RequestParam(name = "init_date", required = true) LocalDateTime initDate,
            @RequestParam(name = "end_date", required = true) LocalDateTime endDate,
            @RequestParam(name = "client_id", required = true) UUID clientId
    ) {
        return ResponseEntity.ok(movementService.getMovementsByDateAndClientId(
                initDate,
                endDate,
                clientId
        ));
    }
}