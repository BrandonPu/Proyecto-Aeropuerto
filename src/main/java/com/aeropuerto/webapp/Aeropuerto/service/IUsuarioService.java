package com.aeropuerto.webapp.Aeropuerto.service;

import java.util.List;

import com.aeropuerto.webapp.Aeropuerto.model.Usuario;

public interface IUsuarioService {

    public List<Usuario> listarUsuarios();

    public Usuario buscarUsuarioPorId(Long id);

    public Usuario guardarUsuario(Usuario usuario);

    public void eliminarUsuario(Usuario usuario);
    

}
