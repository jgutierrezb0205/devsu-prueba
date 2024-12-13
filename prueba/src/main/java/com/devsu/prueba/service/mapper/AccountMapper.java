package com.devsu.prueba.service.mapper;

import com.devsu.prueba.entities.Account;
import com.devsu.prueba.service.dto.GetAccountDto;
import com.devsu.prueba.service.dto.PostAccountDto;
import com.devsu.prueba.service.dto.PutAccountDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    GetAccountDto toGetAccountDto(Account account);
    Account toAccount(PostAccountDto postAccountDto);
    Account toAccount(PutAccountDto putAccountDto);
}
