package Proyectito.demo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RespuestaApi {

    private int statusError;
    private String mensaje;
    private Object data;
    private LocalDateTime timestamp;

}
