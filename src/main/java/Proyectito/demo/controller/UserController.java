package Proyectito.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Proyectito.demo.dto.RespuestaApi;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/api-v1")
@Tag(name = "Usuarios", description = "API para gestión de usuarios del sistema")
public class UserController {

    private final UsuarioService usuarioService;

    public UserController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registrar")
    @Operation(
        summary = "Registrar nuevo usuario",
        description = "Crea un nuevo usuario en el sistema con su perfil y credenciales de autenticación",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos completos del usuario a registrar",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = UsuarioRegistradodto.class)
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "Usuario creado exitosamente",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UsuarioRegistradodto.class)
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Datos de entrada inválidos o incompletos"
            ),
            @ApiResponse(
                responseCode = "409",
                description = "El correo o nombre de usuario ya está registrado"
            )
        }
    )
    public ResponseEntity<RespuestaApi> registrar(@Valid @RequestBody UsuarioRegistradodto dto) {
        UsuarioRegistradodto creado = usuarioService.crearUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(new RespuestaApi(201, "Usuario creado exitosamente", creado));
    }

    @GetMapping("/email/{correo}")
    @Operation(
        summary = "Buscar usuario por correo electrónico",
        description = "Recupera los datos de un usuario específico mediante su dirección de correo electrónico",
        parameters = {
            @Parameter(
                name = "correo",
                description = "Dirección de correo electrónico del usuario",
                required = true,
                example = "juan.perez@ejemplo.com"
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Usuario encontrado",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Usuariodto.class)
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "No se encontró ningún usuario con ese correo electrónico",
                content = @Content(
                    mediaType = "text/plain",
                    schema = @Schema(type = "string")
                )
            )
        }
    )
    public ResponseEntity<RespuestaApi> userEmail(@PathVariable String correo) {
        try {
            Usuariodto usuario = usuarioService.UsuarioByGmail(correo);
            return ResponseEntity.ok(new RespuestaApi(200, "Usuario encontrado", usuario));
        } catch(RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new RespuestaApi(404, ex.getMessage()));
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar usuario",
        description = "Elimina permanentemente un usuario del sistema por su identificador único",
        security = @SecurityRequirement(name = "bearerAuth"),
        parameters = {
            @Parameter(
                name = "id",
                description = "Identificador único del usuario",
                required = true,
                example = "123e4567-e89b-12d3-a456-426614174000"
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "204",
                description = "Usuario eliminado exitosamente"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Usuario no encontrado con el ID proporcionado"
            ),
            @ApiResponse(
                responseCode = "403",
                description = "No tiene permisos para eliminar usuarios"
            )
        }
    )
    public ResponseEntity<RespuestaApi> eliminar(@PathVariable String id){
    usuarioService.delete(id);
    return ResponseEntity.ok(new RespuestaApi(200, "Usuario eliminado exitosamente"));
}
    
    @GetMapping
    @Operation(
        summary = "Listar todos los usuarios",
        description = "Obtiene una lista completa de todos los usuarios registrados en el sistema",
        security = @SecurityRequirement(name = "bearerAuth"),
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Lista de usuarios obtenida exitosamente",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Usuariodto.class),
                    array = @ArraySchema(schema = @Schema(implementation = Usuariodto.class))
                )
            ),
            @ApiResponse(
                responseCode = "403",
                description = "No tiene permisos para ver la lista de usuarios"
            )
        }
    )
    public ResponseEntity<RespuestaApi> todos() {
    List<Usuariodto> usuarios = usuarioService.ListUsuarios();
    return ResponseEntity.ok(new RespuestaApi(200, "Usuarios obtenidos exitosamente", usuarios));
}

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar datos de usuario",
        description = "Actualiza campos específicos de un usuario existente. Solo se pueden actualizar: nombre, apellido, correo",
        security = @SecurityRequirement(name = "bearerAuth"),
        parameters = {
            @Parameter(
                name = "id",
                description = "Identificador único del usuario a actualizar",
                required = true,
                example = "123e4567-e89b-12d3-a456-426614174000"
            )
        },
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Campos a actualizar (solo nombre, apellido, correo)",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(
                    type = "object",
                    example = "{ \"nombre\": \"Juan Carlos\", \"apellido\": \"Pérez\", \"correo\": \"jcperez@nuevo.com\" }"
                )
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Usuario actualizado exitosamente",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Usuariodto.class)
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Formato de correo inválido o datos mal formados"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Usuario no encontrado"
            ),
            @ApiResponse(
                responseCode = "409",
                description = "El nuevo correo ya está en uso por otro usuario"
            )
        }
    )
    public ResponseEntity<RespuestaApi> actualizar(@PathVariable String id, @RequestBody Map<String, Object> campos) {
        Usuariodto actualizado = usuarioService.update(id, campos);
        return ResponseEntity.ok(new RespuestaApi(200, "Usuario actualizado exitosamente", actualizado));
    }
    
}
