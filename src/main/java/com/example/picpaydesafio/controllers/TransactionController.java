package com.example.picpaydesafio.controllers;

import com.example.picpaydesafio.domainuser.transaction.Transaction;
import com.example.picpaydesafio.dtos.TransactionDTO;
import com.example.picpaydesafio.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception {
        Transaction newTransaction = this.transactionService.createTransaction(transactionDTO);
        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactionList = this.transactionService.getAllTransactions();
        return new ResponseEntity<>(transactionList,HttpStatus.OK);
    }
}
