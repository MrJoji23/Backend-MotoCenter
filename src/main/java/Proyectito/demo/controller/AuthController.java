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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticación", description = "API para autenticación y gestión de tokens JWT")
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
        @Operation(
            summary = "Verificar estado del servicio",
            description = "Endpoint simple para verificar que el servicio de autenticación está activo",
            responses = {
                @ApiResponse(
                    responseCode = "200",
                    description = "Servicio activo",
                    content = @Content(
                        mediaType = "text/plain",
                        schema = @Schema(type = "string")
                    )
                )
            }
        )
        public String getMethodName() {
            return new String("hola");
        }
        


        @PostMapping("/login")
        @Operation(
            summary = "Iniciar sesión",
            description = "Autentica un usuario verificando sus credenciales. Retorna un token JWT para acceder a endpoints protegidos",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Credenciales del usuario",
                required = true,
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UsuarioAuthdto.class)
                )
            ),
            responses = {
                @ApiResponse(
                    responseCode = "200",
                    description = "Autenticación exitosa",
                    content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                            type = "object",
                            example = """
                                {
                                  "timestamp": "2026-05-10T22:30:00",
                                  "status": 200,
                                  "id": "123e4567-e89b-12d3-a456-426614174000",
                                  "mensaje": "Login extioso",
                                  "usuario": "jperez",
                                  "nombreCompleto": "Juan Perez",
                                  "roles": ["USER", "ADMIN"],
                                  "token": "eyJhbGciOiJIUzI1NiJ9..."
                                }
                              """
                        )
                    )
                ),
                @ApiResponse(
                    responseCode = "400",
                    description = "Credenciales mal formadas o incompletas"
                ),
                @ApiResponse(
                    responseCode = "401",
                    description = "Credenciales inválidas - Usuario o contraseña incorrectos"
                ),
                @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado en la base de datos"
                )
            }
        )
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
