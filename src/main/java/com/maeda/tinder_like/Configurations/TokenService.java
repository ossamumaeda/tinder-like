package com.maeda.tinder_like.Configurations;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.maeda.tinder_like.Domain.User.User;

@Service
public class TokenService {
    
    private String secret = "teste";

    public String generateToken(User user){

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);   
            String token = JWT.create()
                .withIssuer("auth-api")
                .withSubject(user.getLogin())
                .withExpiresAt(generateExpirationDate())
                .sign(algorithm);  
            
                return token;
        } catch (Exception e) {
            throw new RuntimeException("Generate token error: ", e);
        }
    }

    public String validateToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(secret); 
        try{
            return JWT.require(algorithm)
                .withIssuer("auth-api")
                .build()
                .verify(token)
                .getSubject();
                
        } catch (Exception e) {
            return "";
        }

    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
