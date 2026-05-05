package Proyectito.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import Proyectito.demo.model.Moto;

public interface MotoRepository extends MongoRepository <Moto, String> {

}
