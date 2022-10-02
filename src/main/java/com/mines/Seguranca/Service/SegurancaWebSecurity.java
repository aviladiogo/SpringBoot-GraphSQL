package com.mines.Seguranca.Service;

import java.util.Date;
import com.mines.Seguranca.Model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class SegurancaWebSecurity {
    
    long expirationtime = 1800000;
    String key = "159753";

    public String generateToken(Usuario usuario){

        String id = String.valueOf(usuario.getPessoaFisicaId().getId());
        String subject = String.valueOf(usuario.getPessoaFisicaId().getNome());

        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setId(id)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expirationtime))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
    
    public Claims decodeToken(String token){

        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }
    
}
