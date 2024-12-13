package com.devsu.prueba.account.repository;

import com.devsu.prueba.account.entities.Movement;
import com.devsu.prueba.account.service.dto.GetMovementDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface MovementRepository extends JpaRepository<Movement, UUID> {
    @Query("SELECT new com.devsu.prueba.account.service.dto.GetMovementDto(m.date, c.name, a.number, a.accountType, a.initialBalance, a.status, m.value, m.balance) " +
            "FROM client c " +
            "JOIN c.accounts a " +
            "JOIN a.movements m " +
            "WHERE c.id = :clientId " +
            "AND m.date >= :initDate " +
            "AND m.date <= :endDate " +
            "ORDER BY m.date DESC")
    List<GetMovementDto> findMovementsByDateAndClientId(LocalDateTime initDate, LocalDateTime endDate, UUID clientId);
}
