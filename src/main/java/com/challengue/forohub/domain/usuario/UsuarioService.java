package com.challengue.forohub.domain.usuario;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario nuevoUsuario(DatosRegistroUsuario datosRegistroUsuario) {
        boolean correoElectronico = usuarioRepository.existsByCorreoElectronico(datosRegistroUsuario.correoElectronico());
        if (correoElectronico) {
            throw new ValidationException("El usuario ya se encuentra registrado");
        }
        String contrasenaEncriptada = passwordEncoder.encode(datosRegistroUsuario.contrasena());
        Usuario Usuario = new Usuario(datosRegistroUsuario, contrasenaEncriptada);
        return usuarioRepository.save(Usuario);
    }
}
