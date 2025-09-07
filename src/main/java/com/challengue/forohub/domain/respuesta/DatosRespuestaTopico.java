package com.challengue.forohub.domain.respuesta;

import com.challengue.forohub.domain.topico.DatosDetalleTopico;
import com.challengue.forohub.domain.usuario.DatosDetalleUsuario;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String mensaje,
        DatosDetalleTopico topico,
        LocalDateTime fechaCreacion,
        DatosDetalleUsuario usuario,
        Boolean solucion
) {
}
