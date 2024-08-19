package com.aeropuerto.webapp.Aeropuerto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aeropuerto.webapp.Aeropuerto.model.Terminal;
import com.aeropuerto.webapp.Aeropuerto.service.TerminalService;

import jakarta.servlet.http.HttpServlet;

@Controller
@RestController
@RequestMapping(value = "terminal")
public class TerminalController extends HttpServlet{
    @Autowired
    TerminalService terminalService;

    @GetMapping("/terminales")
    public ResponseEntity<List<Terminal>> listarTerminal(){
        try {
            return ResponseEntity.ok(terminalService.listarTerminales());
        } catch (Exception e) {
           return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/terminal")
    public ResponseEntity<Map<String, String>> agregarTerminal(@RequestBody Terminal terminal){
        Map<String, String> response = new HashMap<>();
        try {
            terminalService.guardarTerminal(terminal);
            response.put("message", "La terminal se creo correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("err", "Error al crear la terminal");
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Terminal> buscarTerminalPorId(@PathVariable Long id){
        try {
            return ResponseEntity.ok(terminalService.buscarTerminalPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/terminal")
    public ResponseEntity<Map<String, String>> editarTerminal(@RequestBody Long id, @RequestBody Terminal terminalNueva){
        Map<String, String> response = new HashMap<>();
        try {
            Terminal terminal = terminalService.buscarTerminalPorId(id);
            terminal.setAeropuerto(terminalNueva.getAeropuerto());
            terminal.setCapacidad(terminalNueva.getCapacidad());
            terminal.setNumeroTerminal(terminalNueva.getNumeroTerminal());
            terminal.setServiciosDisponibles(terminalNueva.getServiciosDisponibles());
            response.put("message", "La terminal se edito correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("err", "La terminal no se pudo editar");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/terminal")
    public ResponseEntity<Map<String, String>> eliminarTerminal(@RequestBody Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Terminal terminal = terminalService.buscarTerminalPorId(id);
            terminalService.eliminarTerminal(terminal);
            response.put("message", "La terminal se elimino correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("err", "No se pudo eliminar la terminal");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
