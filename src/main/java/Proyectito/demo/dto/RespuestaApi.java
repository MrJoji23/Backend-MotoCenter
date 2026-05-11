package Proyectito.demo.dto;

import java.time.LocalDateTime;

public class RespuestaApi {

    private int status;
    private String mensaje;
    private Object data;
    private LocalDateTime timestamp;

    // Constructor para respuestas con datos
    public RespuestaApi(int status, String mensaje, Object data) {
        this.status = status;
        this.mensaje = mensaje;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    // Constructor para respuestas sin datos (delete, por ejemplo)
    public RespuestaApi(int status, String mensaje) {
        this.status = status;
        this.mensaje = mensaje;
        this.data = null;
        this.timestamp = LocalDateTime.now();
    }

    // Getters
    public int getStatus() { return status; }
    public String getMensaje() { return mensaje; }
    public Object getData() { return data; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
