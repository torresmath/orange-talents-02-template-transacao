package com.zup.transactions.transaction;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String cardNumber;

    @NotNull
    @Positive
    @Column(columnDefinition = "DECIMAL(10,2)")
    private BigDecimal amount;

    @NotNull
    @CreationTimestamp
    private LocalDateTime transactionDate = LocalDateTime.now();

    /**
     * @deprecated hibernate
     */
    public Transaction() { }

    public Transaction(@NotNull @NotBlank String cardNumber, @NotNull @Positive BigDecimal amount) {
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
