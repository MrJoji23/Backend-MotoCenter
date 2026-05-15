package Proyectito.demo.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "usuarios_Auth")
public class UsuarioAuth {
    @Id
    private String id;
    private String user;
    private String pass;
    private Rol rol;

}
