package Proyectito.demo.exception;

import java.nio.file.AccessDeniedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<RespuestaApi> handleAccesoDenegado(AccessDeniedException ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new RespuestaApi(403, "No tienes permisos para realizar esta acción"));
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespuestaApi> handleGeneral(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new RespuestaApi(500, "Error interno del servidor"));
    }

}
