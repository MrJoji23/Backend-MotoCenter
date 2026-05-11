package Proyectito.demo.mapper;

import java.util.List;

import Proyectito.demo.dto.Usuariodto;
import Proyectito.demo.model.Usuario;

public interface UsuarioMapper {

    Usuario toUsuario(Usuariodto usuariodto); // Método para convertir de UsuarioDto a Usuario
    
    Usuariodto toDto(Usuario usuario); // Método para convertir de Usuario a UsuarioDto

    List <Usuariodto> todosList(List<Usuario> usuarios);// Listar todos los usuarios creados

    void updateUsuario(Usuariodto dto, Usuario usuario);//Actualizar un Usuario con los datos de dto

}
