package com.aeropuerto.webapp.Aeropuerto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aeropuerto.webapp.Aeropuerto.model.Usuario;
import com.aeropuerto.webapp.Aeropuerto.service.UsuarioService;
import com.aeropuerto.webapp.Aeropuerto.util.PasswordUtils;

@Controller
@RestController
@RequestMapping(value = "")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PasswordUtils passwordUtils;

    @GetMapping("/usuarios")
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/usuario")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/usuario")
    public ResponseEntity<Map<String, String>> guardarUsuario(@RequestBody Usuario usuario) {
        Map<String, String> response = new HashMap<>();
        try {
            // Encripta la contraseña del usuario antes de guardar
            String passwordEncriptada = passwordUtils.encrytedPassword(usuario.getContrasenia());
            usuario.setContrasenia(passwordEncriptada); // Establece la contraseña encriptada

            // Guarda el usuario en la base de datos
            usuarioService.guardarUsuario(usuario);
            response.put("message", "Usuario creado con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al agregar al Usuario.");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
