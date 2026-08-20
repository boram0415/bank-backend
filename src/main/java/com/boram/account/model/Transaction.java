package com.boram.account.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "transactions",
        indexes = {
                @Index(name = "idx_tx_from", columnList = "fromAccount"),
                @Index(name = "idx_tx_to", columnList = "toAccount"),
                @Index(name = "idx_tx_created", columnList = "createdDt")
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String fromAccount;

    @Column(nullable = false, length = 20)
    private String toAccount;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TranStatus status;

    @Column(length = 200)
    private String failReason;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDt;

    private LocalDateTime completedAt;

    @Builder
    public Transaction(String fromAccount, String toAccount, BigDecimal amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.status = TranStatus.REQUESTED;
    }

    @PrePersist
    private void prePersist() {
        this.createdDt = LocalDateTime.now();
    }

    public void markSuccess() {
        this.status = TranStatus.SUCCESS;
        this.completedAt = LocalDateTime.now();
    }

    public void markFailed(String reason) {
        this.status = TranStatus.FAILED;
        this.failReason = reason;
        this.completedAt = LocalDateTime.now();
    }
}
