package com.jlcastaneda.market.web.controller.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtil {

    private static final String KEY = "curso";

    public String generateToken(UserDetails userDetails){
        //SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())  //fecha de creación
                .setExpiration(new Date(System.currentTimeMillis() + 1000 *60 * 60 * 10)) //feccha de expitación
                .signWith(SignatureAlgorithm.HS256,KEY)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        return userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpired(token);
    }

    //Extraer el usuario
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    //Verificamos si ya expiro el token
    public boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }

    //verificamos la firma
    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }

}
