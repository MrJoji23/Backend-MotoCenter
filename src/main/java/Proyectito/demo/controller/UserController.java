package Proyectito.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Proyectito.demo.dto.UsuarioRegistradodto;
import Proyectito.demo.dto.Usuariodto;
import Proyectito.demo.services.UsuarioService;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api-v1")
public class UserController {

    private final UsuarioService usuarioService;

    public UserController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registrar")//Crear un nuevo usuario ingresando el Normal y el Auth
    public ResponseEntity<?> registrar(@Valid @RequestBody UsuarioRegistradodto dto) {
        UsuarioRegistradodto creado = usuarioService.crearUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping("/email/{correo}")//Investigar por el usuario
    public ResponseEntity<?> userEmail(@PathVariable String correo) {
        try{
            Usuariodto usuario = usuarioService.UsuarioByGmail(correo);
            return ResponseEntity.ok(usuario);
        }catch(RuntimeException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")//Eliminar el usuario
    public ResponseEntity<Void> eliminar(@PathVariable String id){
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping//Listar todos los usuarios
    public ResponseEntity<List<Usuariodto>> todos() {
        return ResponseEntity.ok(usuarioService.ListUsuarios());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuariodto> actualizar(@PathVariable String id, @RequestBody Map<String, Object> campos){
        return ResponseEntity.ok(usuarioService.update(id, campos));
    }
    
}
