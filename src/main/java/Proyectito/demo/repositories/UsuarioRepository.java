package Proyectito.demo.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import Proyectito.demo.model.Usuario;

public interface UsuarioRepository extends MongoRepository <Usuario, String> {

    //Metodo para buscar por el correo
    Optional<Usuario> findByCorreo(String correo);

    boolean existsByCorreo(String correo);

}
