package com.fiap.synthia.empresa.user;

import org.springframework.stereotype.Service;
import com.fiap.synthia.empresa.token.Credentials;
import com.fiap.synthia.empresa.token.Token;
import com.fiap.synthia.empresa.token.TokenService;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    

    public UserService(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        
    }

    public Token login (Credentials credentials) {
        var user = userRepository.findByUsername(credentials.username())
                .orElseThrow( () -> new RuntimeException("Access denied") );

        return tokenService.createToken(credentials.username());
    }

}