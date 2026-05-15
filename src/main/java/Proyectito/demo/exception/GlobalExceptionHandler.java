package Proyectito.demo.exception;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import Proyectito.demo.dto.RespuestaApi;



@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNoEncontrado.class)
    public ResponseEntity<String> handleNoEncontrado(RecursoNoEncontrado ex){
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ex.getMessage());
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<RespuestaApi> handleUsuarioException(UserException ex){
        RespuestaApi error = RespuestaApi.builder()
            .mensaje(ex.getMessage())
            .timestamp(LocalDateTime.now())
            .statusError(HttpStatus.BAD_REQUEST.value())
            .build();
            
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<RespuestaApi> handleAccesoDenegado(AccessDeniedException ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new RespuestaApi(403, "No tienes permisos para realizar esta acción", null, LocalDateTime.now() ));
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespuestaApi> handleGeneral(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new RespuestaApi(500, "Error interno del servidor", null, LocalDateTime.now()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<RespuestaApi> handleBadCredentials(BadCredentialsException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new RespuestaApi(401, "Usuario o contraseña incorrectos", null, LocalDateTime.now()));
    }

}
