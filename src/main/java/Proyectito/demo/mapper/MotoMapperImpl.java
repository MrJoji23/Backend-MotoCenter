package Proyectito.demo.mapper;

import java.util.List;

import Proyectito.demo.dto.Motodto;
import Proyectito.demo.model.Moto;

public class MotoMapperImpl implements MotoMapper {// Método para convertir de UsuarioDto a Usuario

    @Override

    public Moto toMoto(Motodto motodto){
        if (motodto == null) 
            return null;
        return Moto.builder()
        .id(motodto.getId())
        .nombreMoto(motodto.getNombre())
        .precio(motodto.getPrecio())
        .descripcion(motodto.getDescripcion())
        .modeloMoto(motodto.getModeloMoto())
        .build();
    }

    @Override
    
    public Motodto toMotodto (Moto moto){// Método para convertir de Usuario a UsuarioDto
        if (moto == null)
        return null;
    return Motodto.builder()
    .id(moto.getId())
    .nombre(moto.getNombreMoto())
    .precio(moto.getPrecio())
    .descripcion(moto.getDescripcion())
    .modeloMoto(moto.getModeloMoto())
    .build();
    }

    @Override
    public List <Motodto> todolist(List<Moto> moto){
        if (moto == null)
            return null;
        return moto.stream()
            .map(this::toMotodto)
            .toList();
            
        
    }
    

}
