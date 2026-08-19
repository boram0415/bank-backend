package com.boram.account.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq", nullable = false)
    private User user;

    @Column(nullable = false, unique = true, length = 20)
    private String accountNumber;

    @Column(nullable = false)
    private String accountPasswordHash;

    @Column(nullable = false, length = 20)
    private String accountType;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal balance;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDt;

    @Builder
    public Account(User user, String accountNumber, String accountPasswordHash,
                   String accountType, BigDecimal balance, String status) {
        this.user = user;
        this.accountNumber = accountNumber;
        this.accountPasswordHash = accountPasswordHash;
        this.accountType = accountType;
        this.balance = balance;
        this.status = status;
    }

    @PrePersist
    private void prePersist() {
        this.createdDt = LocalDateTime.now();
    }
}
