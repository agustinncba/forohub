package com.challengue.forohub.domain.curso;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    @Transactional
    public Curso registrarCurso(DatosNuevoCurso datosNuevoCurso) {
        if (cursoRepository.existsByNombre(datosNuevoCurso.nombre())) {
            throw new ValidationException("El nombre de curso ingresado, ya existe!");
        }
        Curso nuevoCurso = new Curso(datosNuevoCurso);
        return cursoRepository.save(nuevoCurso);
    }

    public DatosCurso buscarCurso(Long id) {
        return cursoRepository.findById(id)
                .map(DatosCurso::new)
                .orElseThrow(() -> new EntityNotFoundException("No se encontro curso con el ID: " + id));
    }

    public Page<DatosCurso> listarCursosPaginados(Pageable pageable) {
        return cursoRepository.findAll(pageable).map(DatosCurso::new);
    }

    public List<DatosCurso> listarCursos() {
        return cursoRepository.findAll().stream()
                .map(DatosCurso::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public DatosCurso actualizarCurso(Long id, DatosActualizarCurso datosActualizarCurso) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontro curso con el ID: " + id));
        if (cursoRepository.existsByNombreAndCategoria(datosActualizarCurso.nombre(), datosActualizarCurso.categoria())) {
            throw new ValidationException("Ya existe un curso con el nombre: " + datosActualizarCurso.nombre() + ", en la categoria: " + datosActualizarCurso.categoria());
        }
        curso.ActualizarDatos(datosActualizarCurso);
        return new DatosCurso(curso);
    }

    @Transactional
    public void eliminarCurso(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new ValidationException("El ID ingresado no existe!");
        }
        cursoRepository.deleteById(id);
    }
}
