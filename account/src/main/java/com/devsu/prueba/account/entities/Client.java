package com.devsu.prueba.account.entities;

import com.devsu.prueba.account.entities.enums.Gender;
import com.devsu.prueba.account.entities.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "client")
public class Client {

    @Id
    private UUID id;

    @NonNull
    private String name;

    @NonNull
    private String identification;

    @NonNull
    private String address;

    @NonNull
    private String phone;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NonNull
    private String password;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Account> accounts;
}