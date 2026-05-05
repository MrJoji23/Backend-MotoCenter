package Proyectito.demo.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
}
