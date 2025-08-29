package com.challengue.forohub.controller;

import com.challengue.forohub.domain.curso.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    CursoService cursoService;

    @Transactional
    @PostMapping
    public ResponseEntity<DatosCurso> registrarCurso(@RequestBody @Valid DatosNuevoCurso datosNuevoCurso,
                                                     UriComponentsBuilder uriComponentsBuilder) {
        Curso curso = cursoService.registrarCurso(datosNuevoCurso);
        DatosCurso datosCurso = new DatosCurso(curso);
        URI url = uriComponentsBuilder.path("curso/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(datosCurso);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosCurso> buscarCurso(@PathVariable Long id) {
        DatosCurso curso = cursoService.buscarCurso(id);
        return ResponseEntity.ok(curso);
    }

    @GetMapping
    public ResponseEntity<List<DatosCurso>> listarCursos() {
        List<DatosCurso> cursos = cursoService.listarCursos();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/paginados")
    public ResponseEntity<Page<DatosCurso>> listarCursosPaginado(
            @PageableDefault(size = 5) Pageable pageable) {
        Page<DatosCurso> cursos = cursoService.listarCursosPaginados(pageable);
        return ResponseEntity.ok(cursos);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<DatosCurso> actualizarCurso(@PathVariable Long id,
                                                      @RequestBody @Valid DatosActualizarCurso datosActualizarCurso) {
        DatosCurso datosCurso = cursoService.actualizarCurso(id, datosActualizarCurso);
        return ResponseEntity.ok(datosCurso);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCurso(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
    }
}
