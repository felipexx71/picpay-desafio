package com.example.picpaydesafio.repositories;

import com.example.picpaydesafio.domainuser.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
