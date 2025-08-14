package com.challengue.forohub.domain.topico;

import java.time.LocalDateTime;

public record DatosTopico(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        TopicoStatus status,
        String nombreAutor,
        String nombreCurso
) {
    public DatosTopico(Topico topico) {
        this(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}
