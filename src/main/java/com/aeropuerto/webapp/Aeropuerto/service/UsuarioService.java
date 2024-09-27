package com.aeropuerto.webapp.Aeropuerto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeropuerto.webapp.Aeropuerto.model.Usuario;
import com.aeropuerto.webapp.Aeropuerto.repository.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Override
    public List<Usuario> listarUsuarios() {
       return usuarioRepository.findAll(); 
    }

    @Override
    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

}
