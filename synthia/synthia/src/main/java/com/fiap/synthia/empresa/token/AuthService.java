package com.fiap.synthia.empresa.token;


import org.springframework.stereotype.Service;

import com.fiap.synthia.empresa.user.UserModel;
import com.fiap.synthia.empresa.user.UserRepository;


@Service
public class AuthService {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    public AuthService(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }
   
    public void registerUser(UserModel user) {
        userRepository.save(user);
    }

    public Token login(Credentials credentials) {
        var user = userRepository.findByUsername(credentials.username())
                .orElseThrow(() -> new RuntimeException("Access denied"));

        if (!credentials.password().equals(user.getPassword())) {
            throw new RuntimeException("Access denied");
        }

        return tokenService.createToken(credentials.username());
    }
}
