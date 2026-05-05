package Proyectito.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Usuariodto {

    private String id;
    private String nombre;
    private String apellido;
    private String correo;
    
}
