package com.ejemplo.servicio;

import com.ejemplo.modelo.Rol;
import com.ejemplo.modelo.Usuario;
import com.ejemplo.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        
        if (usuario.getRol() == null) {
            usuario.setRol(Rol.CLIENT);
        }
        
        return usuarioRepository.save(usuario);
    }
}