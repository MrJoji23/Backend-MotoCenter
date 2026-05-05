package Proyectito.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import Proyectito.demo.model.Rol;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UsuarioRegistradodto {
    @NotBlank(message = "El nombre es obligatorio dese backend")
    private String nombre;
    @NotBlank(message = "El apellido es obligatorio tambien dese backend    ")
    private String apellido;
    private String correo;
    private String usuario;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private List<Rol> roles;

}
