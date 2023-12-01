package com.example.picpaydesafio.services;

import com.example.picpaydesafio.controllers.TransactionController;
import com.example.picpaydesafio.domainuser.User;
import com.example.picpaydesafio.domainuser.transaction.Transaction;
import com.example.picpaydesafio.dtos.TransactionDTO;
import com.example.picpaydesafio.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class TransactionService {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private NotificationService notificationService;
    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.amount());

        if (this.authorizedTransaction(sender, transaction.amount())){
            throw new Exception("Trnasação não autorizada!");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.amount());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.amount()));
        receiver.setBalance(receiver.getBalance().add(transaction.amount()));

        this.transactionRepository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.sendNotification(sender, "Transacao realizada com sucesso");
        this.notificationService.sendNotification(receiver, "Transacao realizada com sucesso");

        return newTransaction;
    }

    public boolean authorizedTransaction(User sender, BigDecimal amount) {
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);

        if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
            String message = (String) authorizationResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        } else return false;
    }

    public List<Transaction> getAllTransactions() {
        return this.transactionRepository.findAll();
    }
}
