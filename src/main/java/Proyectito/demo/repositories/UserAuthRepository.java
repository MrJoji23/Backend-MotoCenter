package Proyectito.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import Proyectito.demo.model.UsuarioAuth;

public interface UserAuthRepository extends MongoRepository<UsuarioAuth, String> {

    
}
