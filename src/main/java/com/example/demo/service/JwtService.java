package com.example.demo.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationTime;

    /**
     * Gera um token JWT assinado com HMAC256.
     *
     * @param username Nome do usuário (subject)
     * @param role     Papel do usuário (claim "role")
     * @return Token JWT
     */
    public String generateToken(String username, String role) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes(StandardCharsets.UTF_8));

        return JWT.create()
                .withSubject(username)
                .withClaim("role", role)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(algorithm);
    }

    /**
     * Valida o token JWT quanto à assinatura e expiração.
     *
     * @param token Token JWT
     * @return true se válido, false se inválido ou expirado
     */
    public boolean validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes(StandardCharsets.UTF_8));
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            System.err.println("❌ Erro na validação do token: " + e.getMessage());
            return false;
        }
    }

    /**
     * Extrai o 'subject' (username) do token.
     *
     * @param token Token JWT
     * @return Nome do usuário
     */
    public String getUsernameFromToken(String token) {
        return JWT.decode(token).getSubject();
    }

    /**
     * Extrai todas as claims do token em forma de Map<String, Object>.
     *
     * @param token Token JWT
     * @return Mapa com claims
     */
    public Map<String, Object> getAllClaimsFromToken(String token) {
        DecodedJWT decoded = JWT.decode(token);
        Map<String, Object> claims = new HashMap<>();

        decoded.getClaims().forEach((key, value) -> {
            if (value != null) {
                claims.put(key, value.as(Object.class));
            }
        });

        return claims;
    }
}
