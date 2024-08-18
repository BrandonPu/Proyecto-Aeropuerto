package com.aeropuerto.webapp.Aeropuerto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aeropuerto.webapp.Aeropuerto.model.Vuelo;
import com.aeropuerto.webapp.Aeropuerto.service.VueloService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RestController
@RequestMapping(value = "")
public class VueloController {
    
    @Autowired
    VueloService vueloService;

    @GetMapping("/vuelos")
    public List<Vuelo> listarVuelos(){
        return vueloService.listarVuelos();
    }

    @GetMapping("/vuelo")
    public ResponseEntity<Vuelo> buscarVueloPorId(@RequestParam Long id){
        try {
            return ResponseEntity.ok(vueloService.buscarVueloPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/vuelo")
    public ResponseEntity<Map<String, String>> guardarVuelo(@RequestBody Vuelo vuelo){
        Map<String, String> response = new HashMap<>();
        try {
            vueloService.guardarVuelo(vuelo);
            response.put("Message", "Se agrego el vuelo con éxito.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al agregar el vuelo.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/vuelo")
    public ResponseEntity<Map<String, String>> editarVuelo(@RequestParam Long id, @RequestBody Vuelo newVuelo){
        Map<String, String> response = new HashMap<>();
        try {
            Vuelo vuelo = vueloService.buscarVueloPorId(id);
            vuelo.setNumeroVuelo(newVuelo.getNumeroVuelo());
            vuelo.setEstadoVuelo(newVuelo.getEstadoVuelo());
            vuelo.setFechaSalida(newVuelo.getFechaSalida());
            vuelo.setAerolinea(newVuelo.getAerolinea());
            vuelo.setEmpleados(newVuelo.getEmpleados());
            vueloService.guardarVuelo(vuelo);
            response.put("Message", "Se edito el vuelo con éxito.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al editar el vuelo.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/vuelo")
    public ResponseEntity<Map<String, String>> eliminarVuelo(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Vuelo vuelo = vueloService.buscarVueloPorId(id);
            vueloService.eliminarVuelo(vuelo);
            response.put("message", "El vuelo se elimino con éxito.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al eliminar el vuelo.");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
