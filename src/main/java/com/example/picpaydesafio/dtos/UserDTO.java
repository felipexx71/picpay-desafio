package com.example.picpaydesafio.dtos;

import com.example.picpaydesafio.domainuser.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String email, String password, UserType userType) {
}
