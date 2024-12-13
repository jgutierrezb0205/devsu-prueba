package com.devsu.prueba.entities;

import com.devsu.prueba.entities.enums.AccountType;
import com.devsu.prueba.entities.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "account")
public class Account {

    @Id
    private UUID id;

    @NonNull
    private String number;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private float initialBalance;

    private float balance;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "clientId", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Movement> movements;
}
