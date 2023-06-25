package com.pokeswap.api.repository;

import com.pokeswap.api.model.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CryptoRepository extends JpaRepository<Crypto, Long> {

    @Modifying
    @Query("UPDATE Operation u SET u.isActive = ?2 WHERE u.id = ?1")
    void updateCryptoStatus(Long id, boolean isActive);
}
