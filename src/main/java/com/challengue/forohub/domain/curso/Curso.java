package com.challengue.forohub.domain.curso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public Curso(DatosNuevoCurso datosNuevoCurso) {
        this.nombre = datosNuevoCurso.nombre();
        this.categoria = datosNuevoCurso.categoria();
    }

    public void ActualizarDatos(DatosActualizarCurso datosActualizarCurso) {
        this.nombre = datosActualizarCurso.nombre();
        this.categoria = datosActualizarCurso.categoria();
    }
}
