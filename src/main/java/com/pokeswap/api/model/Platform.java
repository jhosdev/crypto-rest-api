package com.pokeswap.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "platforms")
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "transaction_time", nullable = false)
    private LocalTime transactionTime;

    @Column(name = "transaction_fee", nullable = false)
    private Double transactionFee;

    @Column(name = "transaction_minimum_deposit", nullable = false, length = 10)
    private Double transactionMinimumDeposit;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @PrePersist
    public void prePersist() {
        this.isActive = true;
    }
}
