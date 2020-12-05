package com.anercan.sorucevap.config;

import com.anercan.sorucevap.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static String getSecretKey(){
        return PropertyUtil.getStringValue("api.security.key", "SoruCevapWebApp") + "001"; // dbden gelen üzerine ekleme
    }

    public static String createJWT(User user) {
        long sessionTime = PropertyUtil.getLongValue("api.security.sessiontime", 1800000L);
        String subject = PropertyUtil.getStringValue("api.security.subject", "refresh");
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(getSecretKey());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("userId", user.getId() + "");
        //claims.put("role", user.getRole());

        JwtBuilder builder = Jwts.builder().setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + sessionTime))
                .setSubject(subject)
                .signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }

    public static boolean checkJWT(String jwt) {
        //This line will throw an exception if it is not a signed JWS (as expected)
        try {
            Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(getSecretKey()))
                    .parseClaimsJws(jwt).getBody();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

