package com.fiap.synthia.empresa.token;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class TokenService {

    public Token createToken(String username){
        var expirationAt = LocalDateTime.now().plus(1, ChronoUnit.HOURS).toInstant(ZoneOffset.UTC);
        Algorithm algorithm = Algorithm.HMAC256("assinatura");
        String token = JWT.create()
                .withSubject(username)
                .withExpiresAt(expirationAt)
                .sign(algorithm);
        return new Token(token,username);
    }

}