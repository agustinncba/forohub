package com.challengue.forohub.domain.respuesta;

import com.challengue.forohub.domain.topico.Topico;
import com.challengue.forohub.domain.topico.TopicoRepository;
import com.challengue.forohub.domain.usuario.Usuario;
import com.challengue.forohub.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RespuestaService {

    @Autowired
    RespuestaRepository respuestaRepository;

    @Autowired
    TopicoRepository topicoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Transactional
    public Respuesta registrarRespuesta(Long topicoId,
                                        DatosRegistroRespuesta datosRegistroRespuesta) {
        Topico topico = topicoRepository.findById(topicoId)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró un tópico con el ID: " + topicoId));

        Usuario usuario = usuarioRepository.findById(datosRegistroRespuesta.autorId())
                .orElseThrow(() -> new EntityNotFoundException("No se encontro un usuario con el ID: " + datosRegistroRespuesta.autorId()));

        Respuesta respuesta = new Respuesta(topico, usuario, datosRegistroRespuesta);
        topico.getRespuestas().add(respuesta);
        return respuestaRepository.save(respuesta);
    }
}
