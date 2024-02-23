package com.example.focus_group.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JWTUtil {
    @Value("${jwt.secret}")
    private String secret;
    public String generateToken(String login, String password, String role) {
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(15).toInstant());

        return JWT.create()
                .withSubject("Данные пользователя")
                .withClaim("login", login)
                .withClaim("password", password)
                .withClaim("role", role)
                .withIssuedAt(new Date())
                .withIssuer("focus_group")
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveClaim(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("Данные пользователя")
                .withIssuer("focus_group")
                .build();

        DecodedJWT JWT = verifier.verify(token);
        return JWT.getClaim("login").asString() + " " + JWT.getClaim("password").asString() + " " + JWT.getClaim("role").asString();
    }
}
