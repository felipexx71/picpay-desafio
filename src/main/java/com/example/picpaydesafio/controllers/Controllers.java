package com.example.picpaydesafio.controllers;

import com.example.picpaydesafio.domainuser.User;
import com.example.picpaydesafio.dtos.UserDTO;
import com.example.picpaydesafio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/users")
public class Controllers {
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO user) {
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList =  this.userService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}


