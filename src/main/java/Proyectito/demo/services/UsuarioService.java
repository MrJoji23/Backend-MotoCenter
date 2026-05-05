package Proyectito.demo.services;

import java.util.List;

import Proyectito.demo.dto.UsuarioRegistradodto;
import Proyectito.demo.dto.Usuariodto;

public interface UsuarioService  {

    Usuariodto create(Usuariodto usuariodto); // Crear usuario(Solo usarlo para guardar datos no tam importantes)

    List<Usuariodto> ListUsuarios(); // Listar todos los usuarios

    void delete(String id); // Borrar usuario

    Usuariodto UsuarioByGmail(String correo); // Buscar usuario por correo

    UsuarioRegistradodto crearUsuario(UsuarioRegistradodto dto);



}
