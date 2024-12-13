package com.devsu.prueba.service.dto;

import com.devsu.prueba.entities.enums.Gender;
import com.devsu.prueba.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PutClientDto {
    private String name;
    private String identification;
    private String address;
    private String phone;
    private Gender gender;
    private String password;
    private Status status;
}
