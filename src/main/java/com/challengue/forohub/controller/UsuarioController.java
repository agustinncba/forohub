package com.challengue.forohub.controller;

import com.challengue.forohub.domain.usuario.DatosDetalleUsuario;
import com.challengue.forohub.domain.usuario.DatosRegistroUsuario;
import com.challengue.forohub.domain.usuario.Usuario;
import com.challengue.forohub.domain.usuario.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetalleUsuario> registroUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario,
                                                                UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuarioCreado = usuarioService.nuevoUsuario(datosRegistroUsuario);
        DatosDetalleUsuario datosDetalleUsuario = new DatosDetalleUsuario(usuarioCreado);
        URI url = uriComponentsBuilder.path("usuarios/{id}").buildAndExpand(usuarioCreado.getId()).toUri();
        return ResponseEntity.created(url).body(datosDetalleUsuario);
    }
}
