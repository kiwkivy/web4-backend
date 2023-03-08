package com.example.back.services;

import com.example.back.entity.User;
import com.example.back.interfaces.UserRepository;
import com.example.back.extra.MD5Class;
import com.example.back.authication.AuthenticationResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class LoginServ {

    private final UserRepository repository;

    private final TokenServ tokenServ;

    private final InMemoryUserDetailsManager detailsManager;

    public LoginServ(UserRepository repository, TokenServ tokenServ, InMemoryUserDetailsManager detailsManager) {
        this.repository = repository;
        this.tokenServ = tokenServ;
        this.detailsManager = detailsManager;
    }

    public ResponseEntity<AuthenticationResponseObject> service(String login, String password) {

        password = MD5Class.MD5(password);

        User user = repository.findByLogin(login);

        if (user == null) {
            throw new BadCredentialsException("Пользователь с таким логином не существует");
        }

        if (!user.getPassword().equals(password)) {
            throw new BadCredentialsException("Неверный логин или пароль! Повторите попытку");
        }

        if (!detailsManager.userExists(login)) {
            detailsManager.createUser(org.springframework.security.core.userdetails.User.withUsername(login)
                    .password("{noop}" + password)
                    .authorities("read")
                    .build());
        }

        return ResponseEntity.ok(
                new AuthenticationResponseObject(tokenServ.generateToken(login))
        );
    }
}
