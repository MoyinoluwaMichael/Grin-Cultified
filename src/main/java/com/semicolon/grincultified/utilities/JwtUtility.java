package com.semicolon.grincultified.utilities;

import io.jsonwebtoken.Jwts;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

import static com.semicolon.grincultified.utilities.AppUtils.*;

public class JwtUtility {

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
