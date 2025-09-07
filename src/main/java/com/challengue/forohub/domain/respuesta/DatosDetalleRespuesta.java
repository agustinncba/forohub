package com.challengue.forohub.domain.respuesta;

import com.challengue.forohub.domain.topico.DatosDetalleTopico;
import com.challengue.forohub.domain.usuario.DatosDetalleUsuario;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(
        Long id,
        String mensaje,
        DatosDetalleTopico topico,
        LocalDateTime fechaCreacion,
        DatosDetalleUsuario usuario,
        Boolean solucion
) {
    public DatosDetalleRespuesta(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                new DatosDetalleTopico(respuesta.getTopico()),
                respuesta.getFechaCreacion(),
                new DatosDetalleUsuario(respuesta.getAutor()),
                respuesta.isSolucion()
        );
    }
}
