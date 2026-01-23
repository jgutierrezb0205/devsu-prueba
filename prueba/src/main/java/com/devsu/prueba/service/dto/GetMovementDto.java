package com.devsu.prueba.service.dto;

import com.devsu.prueba.entities.enums.AccountType;
import com.devsu.prueba.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetMovementDto {
    private LocalDateTime date;
    private String client;
    private String numberAccount;
    private AccountType accountType;
    private BigDecimal initialBalance;
    private Status status;
    private BigDecimal movement;
    private BigDecimal availableBalance;
}
