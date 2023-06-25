package com.pokeswap.api.repository;

import com.pokeswap.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndIsActiveIsTrue(String email);

    boolean existsByEmailAndIsActiveIsTrue(String email);
    @Modifying
    @Query("UPDATE User u SET u.isActive = ?2 WHERE u.id = ?1")
    void updateUserStatus(Long id, boolean isActive);
}
