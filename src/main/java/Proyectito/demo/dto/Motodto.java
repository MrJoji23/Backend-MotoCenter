package Proyectito.demo.dto;

import Proyectito.demo.model.DescripcionMoto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Motodto {

    private String id;
    private String nombre;
    private String precio;
    private DescripcionMoto descripcion;
    private String modeloMoto;
}
