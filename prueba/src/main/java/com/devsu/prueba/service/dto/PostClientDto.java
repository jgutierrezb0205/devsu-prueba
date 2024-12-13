package com.devsu.prueba.service.dto;

import com.devsu.prueba.entities.enums.Gender;
import com.devsu.prueba.entities.enums.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostClientDto {
    private UUID id;

    private String name;

    @NotNull(message = "The identification cannot be null")
    @Pattern(regexp = "^[0-9]+$", message = "The identification is invalid")
    @Size(min = 10, max = 20, message = "The identification is invalid")
    private String identification;

    @NotNull(message = "The address cannot be null")
    private String address;

    @NotNull(message = "The phone cannot be null")
    @Pattern(regexp = "^[0-9]+$", message = "The phone is invalid")
    private String phone;

    @NotNull(message = "The gender cannot be null")
    private Gender gender;

    @NotNull(message = "The password cannot be null")
    private String password;

    @NotNull(message = "The status cannot be null")
    private Status status;
}