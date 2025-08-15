package com.challengue.forohub.domain.topico;

import com.challengue.forohub.domain.curso.Curso;
import com.challengue.forohub.domain.curso.CursoRepository;
import com.challengue.forohub.domain.usuario.Usuario;
import com.challengue.forohub.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicoService {

    @Autowired
    TopicoRepository topicoRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Transactional
    public Topico nuevoTopico(DatosRegistroTopico datos) {

        boolean topicoPorTitulYMensaje = topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());
        if (topicoPorTitulYMensaje == true) {
            throw new ValidationException("Ya existe un tópico con ese título y mensaje");
        }
        Optional<Usuario> autor = usuarioRepository.findById(datos.autorId());
        if (!autor.isPresent()) {
            throw new EntityNotFoundException("No se encontró el autor con id");
        }
        Optional<Curso> curso = cursoRepository.findById(datos.cursoId());
        if (!curso.isPresent()) {
            throw new EntityNotFoundException("No se encontró el curso con id");
        }

        Topico topico = new Topico();
        topico.setMensaje(datos.mensaje());
        topico.setTitulo(datos.titulo());
        topico.setAutor(autor.get());
        topico.setCurso(curso.get());

        topicoRepository.save(topico);
        return topico;
    }

    public List<DatosListadoTopico> listarTopicos() {
        return topicoRepository.findAll().stream()
                .map(DatosListadoTopico::new)
                .collect(Collectors.toList());
    }

    public List<DatosListadoTopico> listarTopicosPaginado(Pageable pageable) {
        Page<Topico> topicosPaginados = topicoRepository.findAll(pageable);
        return topicosPaginados.stream().map(DatosListadoTopico::new).toList();
    }

    public DatosTopico datosTopico(Long id) {
        return topicoRepository.findById(id)
                .map(topico -> new DatosTopico(topico))
                .orElseThrow(() -> new EntityNotFoundException("No se encontró un tópico con el ID: " + id));
    }

    @Transactional
    public DatosRespuestaTopico actualizarTopico(Long id, DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el tópico con el ID proporcionado."));

        if (topicoRepository.existsByTituloAndMensaje(datosActualizarTopico.titulo(), datosActualizarTopico.mensaje())) {
            throw new ValidationException("Ya existe un tópico con el mismo título y mensaje.");
        }
        topico.actualizarDatos(datosActualizarTopico);
        return new DatosRespuestaTopico(topico);
    }

    @Transactional
    public void eliminarTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new EntityNotFoundException("No se encontró un tópico con el ID proporcionado para eliminar.");
        }
        topicoRepository.deleteById(id);
    }
}
