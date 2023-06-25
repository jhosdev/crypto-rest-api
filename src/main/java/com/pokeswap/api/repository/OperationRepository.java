package com.pokeswap.api.repository;

import com.pokeswap.api.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    @Modifying
    @Query("UPDATE Operation u SET u.isActive = ?2 WHERE u.id = ?1")
    void updateOperationStatus(Long id, boolean isActive);
}
