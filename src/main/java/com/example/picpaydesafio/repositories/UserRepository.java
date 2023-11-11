package com.example.picpaydesafio.repositories;

import com.example.picpaydesafio.domainuser.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> opfindUserByDocument(String document);
    Optional<User> opfindUserById(Long id);



}
