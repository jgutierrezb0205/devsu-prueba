package com.devsu.prueba.service.dto;

import com.devsu.prueba.entities.enums.AccountType;
import com.devsu.prueba.entities.enums.Status;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAccountDto {
    private UUID id;
    private String number;
    private AccountType accountType;
    private BigDecimal initialBalance;
    private BigDecimal balance;
    private Status status;
}
