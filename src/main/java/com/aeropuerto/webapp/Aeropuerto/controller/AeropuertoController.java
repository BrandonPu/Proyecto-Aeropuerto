package com.aeropuerto.webapp.Aeropuerto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aeropuerto.webapp.Aeropuerto.model.Aeropuerto;
import com.aeropuerto.webapp.Aeropuerto.service.AeropuertoService;

@Controller
@RestController
@RequestMapping("")
public class AeropuertoController {

    @Autowired
    AeropuertoService aeropuertoService;

    @GetMapping("/aeropuertos")
    public ResponseEntity<List<Aeropuerto>> listarAeropuertos() {
        try {
            return ResponseEntity.ok(aeropuertoService.listarAeropuertos());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/aeropuerto")
    public ResponseEntity<Aeropuerto> buscarAeropuertoPorId(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(aeropuertoService.buscarAeropuertoPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/aeropuerto")
    public ResponseEntity<Map<String, String>> agregarAeropuerto(@RequestBody Aeropuerto aeropuerto) {
        Map<String, String> response = new HashMap<>();
        try {
            aeropuertoService.guardarAeropuerto(aeropuerto);
            response.put("message", "El Aeropuerto se creo con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al crear el Aeropuerto");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/aeropuerto")
    public ResponseEntity<Map<String, String>> editarAeropuerto(@RequestParam Long id,
            @RequestBody Aeropuerto aeropuertoNuevo) {
        Map<String, String> response = new HashMap<>();
        try {
            Aeropuerto aeropuerto = aeropuertoService.buscarAeropuertoPorId(id);
            aeropuerto.setIata(aeropuertoNuevo.getIata());
            aeropuerto.setNombre(aeropuertoNuevo.getNombre());
            aeropuerto.setCiudad(aeropuertoNuevo.getCiudad());
            aeropuerto.setPais(aeropuertoNuevo.getPais());
            aeropuertoService.guardarAeropuerto(aeropuerto);
            response.put("message", "Aeropuerto modificado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al modificar el Aeropuerto");
            return ResponseEntity.badRequest().body(response);
        }
    }

    //eliminar
    @DeleteMapping("/aeropuerto")
    public ResponseEntity<Map<String, String>> eliminarAeropuerto(@RequestParam Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            Aeropuerto aeropuerto = aeropuertoService.buscarAeropuertoPorId(id);
            aeropuertoService.eliminarAeropuerto(aeropuerto);
            response.put("message", "Aeropuerto elimado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al Eliminar el Aeropuerto");
            return ResponseEntity.badRequest().body(response);
        }
    }
}