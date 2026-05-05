package Proyectito.demo.mapper;

import java.util.List;

import Proyectito.demo.dto.Usuariodto;
import Proyectito.demo.model.Usuario;

public class UsuarioMapperImpl implements UsuarioMapper {

    @Override

    public Usuario toUsuario(Usuariodto usuariodto) {// Método para convertir de UsuarioDto a Usuario
        if (usuariodto == null) 
            return null;
        return Usuario.builder()
            .id(usuariodto.getId())
            .nombre(usuariodto.getNombre())
            .apellido(usuariodto.getApellido())
            .correo(usuariodto.getCorreo())
            .build();
    }

    @Override
    public Usuariodto toDto(Usuario usuario) {// Método para convertir de Usuario a UsuarioDto
        if (usuario == null) 
            return null;
        return Usuariodto.builder()
        .id(usuario.getId())
        .nombre(usuario.getNombre())
        .apellido(usuario.getApellido())
        .correo(usuario.getApellido())
        .build();
        
    }

    @Override
    public List <Usuariodto> todosList(List<Usuario> usuarios){
        if (usuarios == null)
            return null;
        return usuarios.stream()
        .map(this::toDto)
        .toList();
    }
}
