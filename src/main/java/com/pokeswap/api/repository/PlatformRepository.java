package com.pokeswap.api.repository;

import com.pokeswap.api.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PlatformRepository extends JpaRepository<Platform, Long> {

    @Modifying
    @Query("UPDATE Platform u SET u.isActive = ?2 WHERE u.id = ?1")
    void updatePlatformStatus(Long id, boolean isActive);
}
