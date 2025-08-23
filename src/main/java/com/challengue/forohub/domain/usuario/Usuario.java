package com.challengue.forohub.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String correoElectronico;
    private String contrasena;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_perfiles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id")
    )
    private List<Perfil> perfiles;

    public Usuario(DatosRegistroUsuario datosRegistroUsuario, String contrasenaEncriptada) {
        this.nombre = datosRegistroUsuario.nombre();
        this.correoElectronico = datosRegistroUsuario.correoElectronico();
        this.contrasena = contrasenaEncriptada;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perfiles;
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return correoElectronico;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}