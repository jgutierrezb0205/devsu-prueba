package com.devsu.prueba.account.service.mapper;

import com.devsu.prueba.account.entities.Movement;
import com.devsu.prueba.account.service.dto.PostMovementDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovementMapper {

    Movement toMovement(PostMovementDto postMovementDto);
}
