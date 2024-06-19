package com.example.ClinicaORM.service;


import com.example.ClinicaORM.entity.Usuario;
import com.example.ClinicaORM.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    //aca necesitamos un metodo que nos devuelva la autenticación

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //como resolvemos?
        Optional<Usuario> usuarioBuscado= usuarioRepository.findByEmail(username);
        if(usuarioBuscado.isPresent()){
            return usuarioBuscado.get();
        }else{
            throw new UsernameNotFoundException("usuario inexistente: "+username);
        }

    }

}
