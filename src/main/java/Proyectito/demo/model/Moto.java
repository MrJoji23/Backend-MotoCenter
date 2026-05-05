package Proyectito.demo.model;

import org.springframework.data.annotation.Id;

import com.mongodb.lang.NonNull;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Moto {

    @Id
    private String id;
    @NonNull
    private String nombreMoto;
    @NotBlank
    private String precio;
    @NotBlank
    private DescripcionMoto descripcion;
    @NotBlank
    private String modeloMoto;

}
