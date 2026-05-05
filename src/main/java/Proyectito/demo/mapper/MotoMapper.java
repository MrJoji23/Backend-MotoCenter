package Proyectito.demo.mapper;

import java.util.List;

import Proyectito.demo.dto.Motodto;
import Proyectito.demo.model.Moto;

public interface MotoMapper {
    
    Moto toMoto(Motodto motodto);
    
    Motodto toMotodto (Moto moto);

    List <Motodto> todolist(List<Moto> moto);
    
    

}
