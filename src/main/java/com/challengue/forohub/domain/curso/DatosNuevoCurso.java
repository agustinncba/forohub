package com.challengue.forohub.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosNuevoCurso(
        @NotBlank
        String nombre,

        @NotNull
        Categoria categoria
) {
}
