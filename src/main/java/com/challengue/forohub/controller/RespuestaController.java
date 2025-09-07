package com.challengue.forohub.controller;

import com.challengue.forohub.domain.respuesta.*;
import com.challengue.forohub.domain.topico.DatosDetalleTopico;
import com.challengue.forohub.domain.usuario.DatosDetalleUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos/{topicoId}/respuestas")
public class RespuestaController {

    @Autowired
    RespuestaService respuestaService;

    @Transactional
    @PostMapping
    public ResponseEntity<DatosDetalleRespuesta> registrarRespuesta(@PathVariable Long topicoId,
                                                                    @RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta,
                                                                    UriComponentsBuilder uriComponentsBuilder) {

        Respuesta respuesta = respuestaService.registrarRespuesta(topicoId, datosRegistroRespuesta);

        DatosDetalleRespuesta datosDetalleRespuesta = new DatosDetalleRespuesta(respuesta);
        URI url = uriComponentsBuilder.path("/topicos/{topicoId}/respuestas/{id}").buildAndExpand(topicoId, respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(datosDetalleRespuesta);
    }

//    @GetMapping
//    public ResponseEntity<List<DatosRespuestaTopico>> listarRespuestas(@PathVariable Long topicoId){
//
//    }
}
