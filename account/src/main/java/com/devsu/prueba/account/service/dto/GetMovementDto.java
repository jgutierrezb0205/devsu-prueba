package com.devsu.prueba.account.service.dto;

import com.devsu.prueba.account.entities.enums.AccountType;
import com.devsu.prueba.account.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private float initialBalance;
    private Status status;
    private float movement;
    private float availableBalance;
}
