package Proyectito.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import Proyectito.demo.model.Rol;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "DTO para autenticación (login/registro de credenciales)")
public class UsuarioAuthdto {
    
    @Schema(description = "Nombre de usuario", example = "jperez2024", required = true)
    private String usuario;

    @Schema(description = "Contraseña del usuario", example = "MiClaveSegura123!", 
            format = "password", writeOnly = true, required = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Schema(description = "Roles del usuario (solo para registro)", hidden = true)
    private List<Rol> roles;
}
