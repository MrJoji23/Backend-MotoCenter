package Proyectito.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO de respuesta con datos básicos del usuario")
public class Usuariodto {
    
    @Schema(description = "ID único del usuario (UUID)", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;
    
    @Schema(description = "Nombre(s) del usuario", example = "Juan Carlos")
    private String nombre;
    
    @Schema(description = "Apellido(s) del usuario", example = "Pérez González")
    private String apellido;
    
    @Schema(description = "Correo electrónico", example = "jperez@empresa.com", format = "email")
    private String correo;
    
}
