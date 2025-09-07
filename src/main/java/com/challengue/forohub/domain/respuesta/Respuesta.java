package com.challengue.forohub.domain.respuesta;

import com.challengue.forohub.domain.topico.Topico;
import com.challengue.forohub.domain.topico.TopicoStatus;
import com.challengue.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    private boolean solucion;

    @PrePersist
    public void setearValoresPorDefecto() {
        this.fechaCreacion = LocalDateTime.now();
    }

    public Respuesta(Topico topico, Usuario autor, DatosRegistroRespuesta datosRegistroRespuesta) {
        this.mensaje = datosRegistroRespuesta.mensaje();
        this.topico = topico;
        this.autor = autor;
        this.solucion = datosRegistroRespuesta.solucion();
    }
}
