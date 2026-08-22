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
        name = "transfer_histories",
        indexes = {
                @Index(name = "idx_history_account_created", columnList = "account_seq, createdDt")
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransferHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_seq", nullable = false)
    private Account account;

    @Column(nullable = false, length = 20)
    private String counterpartyAccount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EntryType type;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal balanceAfter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDt;

    @Builder
    public TransferHistory(Account account, String counterpartyAccount, EntryType type,
                           BigDecimal amount, BigDecimal balanceAfter, Transaction transaction) {
        this.account = account;
        this.counterpartyAccount = counterpartyAccount;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.transaction = transaction;
    }

    @PrePersist
    private void prePersist() {
        this.createdDt = LocalDateTime.now();
    }
}