package com.zup.transactions.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class TransactionEvent {

    @JsonProperty("id")
    private String cardNumber;

    @JsonProperty("valor")
    private BigDecimal amount;

    @Override
    public String toString() {
        return "TransactionEvent{" +
                "id='" + cardNumber + '\'' +
                ", amount=" + amount +
                '}';
    }

    public String getCardNumber() { return cardNumber; }

    public BigDecimal getAmount() { return amount; }

    public Transaction toModel() {
        return new Transaction(cardNumber, amount);
    }
}
