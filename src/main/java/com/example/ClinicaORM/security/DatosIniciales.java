package com.example.ClinicaORM.security;

import com.example.ClinicaORM.entity.Usuario;
import com.example.ClinicaORM.entity.UsuarioRole;
import com.example.ClinicaORM.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosIniciales implements ApplicationRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Crear usuario ADMIN si no existe
        if (usuarioRepository.findByEmail("admin@admin.com").isEmpty()) {
            String passSinCifrarAdmin = "admin";
            String passCifradoAdmin = passwordEncoder.encode(passSinCifrarAdmin);
            Usuario admin = new Usuario("Jorgito", "Pereryra", "admin@admin.com", passCifradoAdmin, UsuarioRole.ROLE_ADMIN);
            usuarioRepository.save(admin);
        }

        // Crear usuario USER si no existe
        if (usuarioRepository.findByEmail("user@user.com").isEmpty()) {
            String passSinCifrarUser = "user";
            String passCifradoUser = passwordEncoder.encode(passSinCifrarUser);
            Usuario user = new Usuario("Gina", "Gina", "user@user.com", passCifradoUser, UsuarioRole.ROLE_USER);
            usuarioRepository.save(user);
        }
    }
}
