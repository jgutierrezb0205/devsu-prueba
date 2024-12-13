package com.devsu.prueba.service.mapper;

import com.devsu.prueba.entities.Client;
import com.devsu.prueba.service.dto.GetClientDto;
import com.devsu.prueba.service.dto.PostClientDto;
import com.devsu.prueba.service.dto.PutClientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    GetClientDto toGetClientDto(Client client);
    Client toClient(PostClientDto postClientDto);
    Client toClient(PutClientDto putClientDto);
}
