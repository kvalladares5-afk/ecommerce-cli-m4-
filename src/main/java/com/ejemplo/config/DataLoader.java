package com.ejemplo.config;

import com.ejemplo.modelo.Rol;
import com.ejemplo.modelo.Usuario;
import com.ejemplo.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.findByEmail("admin@tienda.com").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNombre("Karina");
            admin.setApellido("Admin");
            admin.setEmail("admin@tienda.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRol(Rol.ADMIN);
            usuarioRepository.save(admin);
            System.out.println("========== USUARIO ADMIN CREADO ==========");
            System.out.println("Email: admin@tienda.com | Pass: admin123");
        }
    }
}