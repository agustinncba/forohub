package com.challengue.forohub.domain.topico;

import com.challengue.forohub.domain.curso.Curso;
import com.challengue.forohub.domain.respuesta.Respuesta;
import com.challengue.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL)
    private List<Respuesta> respuestas;

    @Enumerated(EnumType.STRING)
    private TopicoStatus status;

    @PrePersist
    public void setearValoresPorDefecto() {
        this.fechaCreacion = LocalDateTime.now();
        this.status = TopicoStatus.NO_RESPONDIDO;
    }
}
