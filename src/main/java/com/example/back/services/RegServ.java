package com.example.back.services;

import com.example.back.entity.User;
import com.example.back.interfaces.UserRepository;
import com.example.back.extra.MD5Class;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class RegServ {

    private final UserRepository repository;

    public RegServ(UserRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<?> service(String login, String password) {

        password = MD5Class.MD5(password);

        if (repository.findByLogin(login) != null) {
            throw new BadCredentialsException("Пользователь уже существует");
        }

        repository.save(new User(login, password));

        return ResponseEntity.ok().build();

    }

}
