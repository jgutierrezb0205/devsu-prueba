package com.devsu.prueba.service.dto;

import com.devsu.prueba.entities.enums.AccountType;
import com.devsu.prueba.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostAccountDto {
    private UUID id;
    private UUID clientId;
    private String number;
    private AccountType accountType;
    private float initialBalance;
    private float balance;
    private Status status;
}