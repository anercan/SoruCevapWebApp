package com.anercan.sorucevap.config;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

import io.jsonwebtoken.*;

import java.util.Date;

public class SecurityConfig {

    public static String createJWT(String userId) {
        long sessionTime = PropertyUtil.getLongValue("api.security.key", 1800000L);
        String subject = PropertyUtil.getStringValue("api.security.subject", "refresh");
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(PropertyUtil.getStringValue("api.security.key", "SoruCevapWebApp"));
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(userId)
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, signingKey);

        Date exp = new Date(nowMillis + sessionTime);
        builder.setExpiration(exp);

        return builder.compact();
    }

    public static Boolean checkJWT(String jwt) {
        //This line will throw an exception if it is not a signed JWS (as expected)
        try {
            Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(PropertyUtil.getStringValue("api.security.key", "SoruCevapWebApp")))
                .parseClaimsJws(jwt).getBody();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println();
        checkJWT(SecurityConfig.createJWT("5"));
    }
}

