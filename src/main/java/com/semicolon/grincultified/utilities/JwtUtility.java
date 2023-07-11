package com.semicolon.grincultified.utilities;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.semicolon.grincultified.exception.AuthenticationException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import static com.semicolon.grincultified.utilities.AppUtils.*;

@AllArgsConstructor
@Getter
public class JwtUtility {
    private final String secret;


    public Map<String, Claim> extractClaimsFrom(String token) throws AuthenticationException {
        DecodedJWT decodedJwt = validateToken(token);
        if (decodedJwt.getClaim(ROLES_VALUE)==null) throw new AuthenticationException(INVALID_TOKEN);
        return decodedJwt.getClaims();
    }

    private DecodedJWT validateToken(String token) {
        return JWT.require(Algorithm.HMAC512(secret))
                .build().verify(token);
    }

    public static String generateEncryptedLink(String userEmail) {
        return Jwts.builder()
                .claim(EMAIL_VALUE, userEmail)
                .setSubject(CULTIFY)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(SIXTY, ChronoUnit.MINUTES)))
                .compact().concat("/");

    }


}
