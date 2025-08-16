package com.challengue.forohub.controller;

import com.challengue.forohub.domain.topico.*;
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
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos,
                                                                UriComponentsBuilder uriComponentsBuilder) {
        Topico topicoCreado = topicoService.nuevoTopico(datos);
        DatosRespuestaTopico datosRespuesta = new DatosRespuestaTopico(topicoCreado);
        URI url = uriComponentsBuilder.path("topicos/{id}").buildAndExpand(topicoCreado.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @GetMapping
    public ResponseEntity<List<DatosDetalleTopico>> listarTopicos() {
        List<DatosDetalleTopico> topicos = topicoService.listarTopicos();
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<DatosDetalleTopico>> listarTopicosPaginados(
            @PageableDefault(size = 5, sort = {"fechaCreacion"}) Pageable pageable) {
        Page<DatosDetalleTopico> topicosPaginados = topicoService.listarTopicosPaginado(pageable);
        return ResponseEntity.ok(topicosPaginados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> datosTopico(@PathVariable Long id) {
        DatosDetalleTopico DatosDetalleTopico = topicoService.datosTopico(id);
        return ResponseEntity.ok(DatosDetalleTopico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@PathVariable Long id,
                                                                 @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        DatosRespuestaTopico datosRespuestaTopico = topicoService.actualizarTopico(id, datosActualizarTopico);
        return ResponseEntity.ok(datosRespuestaTopico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
    }
}
