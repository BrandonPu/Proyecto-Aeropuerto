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

import com.aeropuerto.webapp.Aeropuerto.model.Pasajero;
import com.aeropuerto.webapp.Aeropuerto.service.PasajeroService;

@Controller
@RestController
@RequestMapping(value = "")
public class PasajeroController {

    @Autowired
    PasajeroService pasajeroService;

    @GetMapping("/pasajeros")
    public ResponseEntity<List<Pasajero>> listarPasajeros() {
        try {
            return ResponseEntity.ok(pasajeroService.listarPasajeros());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/pasajero")
    public ResponseEntity<Pasajero> buscarPasajeroPorId(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(pasajeroService.buscarPasajeroPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/pasajero")
    public ResponseEntity<Map<String, String>> agregarPasajero(@RequestBody Pasajero pasajero) {
        Map<String, String> response = new HashMap<>();
        try {
            if (pasajeroService.guardarPasajero(pasajero)) {
                pasajeroService.guardarPasajero(pasajero);
                response.put("message", "El Pasajero se creo con exito!");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Error");
                response.put("err", "El nombre y el apellido no puede conterner números.");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al crear el Pasajero!");
            return ResponseEntity.badRequest().body(response);
        }
    }

    // editor
    @PutMapping("/pasajero")
    public ResponseEntity<Map<String, String>> editarPasajero(@RequestParam Long id, @RequestBody Pasajero pasajeroNuevo) {
        Map<String, String> response = new HashMap<>();
        try {
            Pasajero pasajero = pasajeroService.buscarPasajeroPorId(id);
            pasajero.setNombre(pasajeroNuevo.getNombre());
            pasajero.setApellido(pasajeroNuevo.getApellido());
            pasajero.setNacionalidad(pasajeroNuevo.getNacionalidad());
            pasajero.setVuelo(pasajeroNuevo.getVuelo());
            pasajeroService.guardarPasajero(pasajero);
            response.put("message", "El Pasajero se a modificado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al modificar el Pasajero");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/pasajero")
    public ResponseEntity<Map<String, String>> eliminarPasajero(@RequestParam Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            Pasajero pasajero = pasajeroService.buscarPasajeroPorId(id);
            pasajeroService.eliminarPasajero(pasajero);
            response.put("message", "El Pasajero a sido elimado con exito!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al Eliminar el Pasajero!");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
