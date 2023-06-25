package com.pokeswap.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pokeswap.api.enums.Op_Status;
import com.pokeswap.api.enums.Op_Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "operations")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private Op_Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    private Op_Type type;

    @Column(name = "from_crypto")
    private String fromCrypto;

    @Column(name="from_amount")
    private Double fromAmount;

    @Column(name = "to_crypto")
    private String toCrypto;

    @Column(name="to_amount")
    private Double toAmount;

    @Column(name = "operation_date", nullable = false)
    private LocalDateTime operationDate;

    @Column(name = "platform")
    private String platform;

    @Column(name="is_active", nullable = false)
    private Boolean isActive;

    @PrePersist
    public void prePersist() {
        this.operationDate = LocalDateTime.now();
        this.isActive = true;
    }

}
