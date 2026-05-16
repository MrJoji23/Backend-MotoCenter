package Proyectito.demo.security;



import java.security.Key;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;



@Service
public class JwtService {

    @Value("${application.security.jwt.secret}")
    private String secretKey;

    private Key objetenerclave(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generartoken(String usuario, String rol, String nombre, String apellido){
        return Jwts.builder()
            .setSubject(usuario)
            .claim("roles", rol)
            .claim("nombre", nombre)
            .claim("apellido", apellido)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 3600000))
            .signWith(objetenerclave(), SignatureAlgorithm.HS256)
            .compact();
    }

    public Claims obtenerClaims(String token){
        return Jwts.parserBuilder()
        .setSigningKey(objetenerclave())
        .build()
        .parseClaimsJws(token)
        .getBody();
    }


    public List<String> extraerRoles(String token){
        return obtenerClaims(token).get("roles", List.class);
    }

    public String extraerUsuario(String token){
        return obtenerClaims(token).getSubject();
    }


}
