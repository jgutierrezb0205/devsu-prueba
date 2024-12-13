package com.devsu.prueba.service.dto;

import com.devsu.prueba.entities.enums.Gender;
import com.devsu.prueba.entities.enums.Status;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetClientDto {
    private UUID id;
    private String name;
    private String identification;
    private String address;
    private String phone;
    private Gender gender;
    private String password;
    private Status status;
}
