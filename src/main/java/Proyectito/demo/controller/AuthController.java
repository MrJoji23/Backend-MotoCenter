package Proyectito.demo.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Proyectito.demo.dto.UsuarioAuthdto;
import Proyectito.demo.model.Usuario;
import Proyectito.demo.model.UsuarioAuth;
import Proyectito.demo.repositories.UserAuthRepository;
import Proyectito.demo.repositories.UsuarioRepository;
import Proyectito.demo.security.JwtService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final UserAuthRepository uar;
    private final UsuarioRepository urepo;

    public AuthController(JwtService jwtService, AuthenticationManager authManager, UserAuthRepository uar, 
        UsuarioRepository urepo){
            this.jwtService = jwtService;
            this.authManager = authManager;
            this.uar = uar;
            this.urepo = urepo;
        }

        @GetMapping()
        public String getMethodName() {
            return new String("hola");
        }
        


        @PostMapping("/login")
        public ResponseEntity<Map<String, Object>> login(@RequestBody UsuarioAuthdto dto) {

            Authentication auth = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(dto.getUsuario(),
            dto.getPassword()));
        
        UsuarioAuth usuario = uar.findByUser(dto.getUsuario())
            .orElseThrow(()-> new RuntimeException("Perfil no encontrado"));

        Usuario perfil = urepo.findById(usuario.getId())
                                .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));

            List<String> roles = usuario.getRoles()
                .stream()
                .map(Enum::name)
                .toList();

                String token = jwtService.generartoken(usuario.getUser(), roles,  perfil.getNombre(), perfil.getApellido());

                Map<String, Object> respuesta = Map.of(
                    "timestamp", LocalDateTime.now(),
                    "status", 200,
                    "id", usuario.getId(),
                    "mensaje", "Login extioso",
                    "usuario", usuario.getUser(),
                    "nombreCompleto", perfil.getNombre() + " " + perfil.getApellido(),
                    "roles", roles,
                    "token", token);
                    return ResponseEntity.ok(respuesta);
                    
                }
}
