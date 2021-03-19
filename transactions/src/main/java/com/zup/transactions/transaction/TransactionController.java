package com.zup.transactions.transaction;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/credit-card/{number}")
    public ResponseEntity<TransactionListResponse> getTransactions(@PathVariable String number) {

        List<Transaction> transactions = manager.createQuery("select t from Transaction t where t.cardNumber = :number order by t.transactionDate DESC", Transaction.class)
                .setParameter("number", number)
                .setMaxResults(10)
                .getResultList();

        if (transactions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new TransactionListResponse(number, transactions));
    }
}
