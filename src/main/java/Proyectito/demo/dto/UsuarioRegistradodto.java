package Proyectito.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import Proyectito.demo.model.Rol;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO para registro de nuevo usuario")
public class UsuarioRegistradodto {
    
    @Schema(description = "Nombre(s) del usuario", example = "Juan Carlos", required = true)
    @NotBlank(message = "El nombre es obligatorio dese backend")
    private String nombre;
    
    @Schema(description = "Apellido(s) del usuario", example = "Pérez González", required = true)
    @NotBlank(message = "El apellido es obligatorio tambien dese backend    ")
    private String apellido;
    
    @Schema(description = "Correo electrónico válido", example = "jperez@empresa.com", format = "email")
    private String correo;
    
    @Schema(description = "Nombre de usuario único para login", example = "jperez2024", required = true)
    private String usuario;

    @Schema(description = "Contraseña segura (mínimo 8 caracteres)", example = "MiClaveSegura123!", 
            format = "password", writeOnly = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Schema(description = "Roles asignados al usuario", example = "[\"USER\",\"ADMIN\"]")
    private List<Rol> roles;

}
