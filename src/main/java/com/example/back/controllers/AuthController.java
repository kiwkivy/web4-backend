package com.example.back.controllers;

import com.example.back.entity.User;
import com.example.back.services.LoginServ;
import com.example.back.services.RegServ;
import com.example.back.authication.AuthenticationResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AuthController {

    private final RegServ regServ;
    private final LoginServ loginServ;

    public AuthController(RegServ regServ, LoginServ loginServ) {
        this.regServ = regServ;
        this.loginServ = loginServ;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (user.getLogin() == null || user.getPassword() == null) {
            throw new BadCredentialsException("Неверный логин или пароль! Повторите попытку");
        }

        return regServ.service(user.getLogin(), user.getPassword());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseObject> login(@RequestBody User user) {
        if (user.getLogin() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().build();
        }

        return loginServ.service(user.getLogin(), user.getPassword());
    }
}
