package com.challengue.forohub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroRespuesta(

        @NotNull
        Long autorId,

        @NotBlank
        String mensaje,

        boolean solucion
) {
}
