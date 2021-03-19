package com.zup.transactions.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionListResponse {

    private final String cardNumber;

    private final List<TransactionResponse> transactions;

    public TransactionListResponse(String cardNumber, List<Transaction> transactions) {

        this.cardNumber = cardNumber;
        this.transactions = transactions.stream()
                .map(tr -> new TransactionResponse(tr.getId(), tr.getAmount(), tr.getTransactionDate()))
                .collect(Collectors.toList());
    }

    public String getCardNumber() { return cardNumber; }

    public List<TransactionResponse> getTransactions() { return transactions; }

    private static class TransactionResponse {
        private Long id;
        private BigDecimal amount;
        private LocalDateTime transactionDate;

        public TransactionResponse(Long id, BigDecimal amount, LocalDateTime transactionDate) {
            this.id = id;
            this.amount = amount;
            this.transactionDate = transactionDate;
        }

        public TransactionResponse() {
        }

        public Long getId() { return id; }

        public BigDecimal getAmount() { return amount; }

        public LocalDateTime getTransactionDate() { return transactionDate; }


    }
}
