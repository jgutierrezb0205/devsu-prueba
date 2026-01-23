package com.devsu.prueba.account.service.dto;

import com.devsu.prueba.account.entities.enums.MovementType;
import lombok.*;

import java.math.BigDecimal;
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
    private BigDecimal value;
}