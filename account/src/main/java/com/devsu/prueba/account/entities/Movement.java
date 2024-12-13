package com.devsu.prueba.account.entities;

import com.devsu.prueba.account.entities.enums.MovementType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "movement")
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NonNull
    private LocalDateTime date;

    @NonNull
    @Enumerated(EnumType.STRING)
    private MovementType movementType;

    private float value;

    private float balance;

    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;
}