package Proyectito.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescripcionMoto {

    private String cilindraje;
    private String potencia;
    private String torque;
    private String Peso;
}
