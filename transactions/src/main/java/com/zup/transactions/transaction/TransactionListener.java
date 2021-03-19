package com.zup.transactions.transaction;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class TransactionListener {

    @PersistenceContext
    private EntityManager manager;

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    @Transactional
    public void listen(TransactionEvent transactionEvent) {
        Transaction transaction = transactionEvent.toModel();
        manager.persist(transaction);

        System.out.println("transactionEvent = " + transactionEvent.getCardNumber());
    }
}
