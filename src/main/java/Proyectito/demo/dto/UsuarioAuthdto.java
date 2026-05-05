package Proyectito.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import Proyectito.demo.model.Rol;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioAuthdto {

    private String id;
    private String usuario;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    private List<Rol> roles;
}
