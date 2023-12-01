package com.example.picpaydesafio.services;

import com.example.picpaydesafio.domainuser.User;
import com.example.picpaydesafio.domainuser.UserType;
import com.example.picpaydesafio.dtos.UserDTO;
import com.example.picpaydesafio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception{
        if (sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Usuário não está autorizado a fazer transação!");
        }

        if (sender.getBalance().compareTo(amount) <= 0)
            throw new Exception("Saldo insuficiente!");
    }
    public User findUserById(Long id) throws Exception{
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado!"));
    }
    public User createUser(UserDTO user) {
        User newUser = new User(user);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {
        return this.repository.findAll();
    }
    public void saveUser(User user) {
        this.repository.save(user);
    }
}
