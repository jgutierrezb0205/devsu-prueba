package com.devsu.prueba.service.dto;

import com.devsu.prueba.entities.enums.MovementType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostMovementDto {
    private UUID accountId;
    private LocalDateTime date;
    private MovementType movementType;
    private float value;
}