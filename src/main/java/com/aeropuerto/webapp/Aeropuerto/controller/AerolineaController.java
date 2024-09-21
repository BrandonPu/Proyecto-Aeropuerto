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

import com.aeropuerto.webapp.Aeropuerto.model.Aerolinea;
import com.aeropuerto.webapp.Aeropuerto.service.AerolineaService;

@Controller
@RestController
@RequestMapping(value= "")
public class AerolineaController {

    @Autowired
    AerolineaService aerolineaService;

    @GetMapping("/aerolineas")
    public List<Aerolinea> listarAerolineas(){ //Listar
        return aerolineaService.listarAerolineas();
    }

    @GetMapping("/aerolinea")
    public ResponseEntity<Aerolinea> buscarAerolineaPorId(@RequestParam Long id){ // Buscar
        try{
            return ResponseEntity.ok(aerolineaService.buscarAerolineaPorId(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }

    }

    @PostMapping("/aerolinea")
    public ResponseEntity<Map<String, String>> guardarAerolinea(@RequestBody Aerolinea aerolinea){ // Agregar
        Map<String, String> response = new HashMap<>();
        try{
            if (aerolineaService.guardarAerolinea(aerolinea)) {
                response.put("message", "La aerolinea se creo con exito!");
                return ResponseEntity.ok(response);
            } else {
                response.put("err", "Hubo un error al crear la aerolinea! ");
                return ResponseEntity.badRequest().body(response);
            }
        }catch(Exception e){
            response.put("err", "Hubo un error al crear la aerolinea! ");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/aerolinea")
    public ResponseEntity<Map<String, String>> editarAerolinea(@RequestParam Long id, @RequestBody Aerolinea aerolineaNueva){ // Editar
        Map<String, String> response = new HashMap<>();
        try{
            Aerolinea aerolinea = aerolineaService.buscarAerolineaPorId(id);
            aerolinea.setCodigoAerolinea(aerolineaNueva.getCodigoAerolinea());
            aerolinea.setNombre(aerolineaNueva.getNombre());
            aerolinea.setPaisOrigen(aerolineaNueva.getPaisOrigen());
            aerolinea.setAeropuertos(aerolinea.getAeropuertos());
            if (aerolineaService.guardarAerolinea(aerolinea)) {
                response.put("message", "La aerolinea se edito con exito!");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Hubo un error al editar la aerolinea!");
                return ResponseEntity.badRequest().body(response);
            }         
        }catch(Exception e){
            response.put("message", "Hubo un error al editar la aerolinea!");
            return ResponseEntity.badRequest().body(response);
        }

    }

    @DeleteMapping("/aerolinea")
    public ResponseEntity<Map<String, String>> eliminarAerolinea(@RequestParam Long id){ // Eliminar
        Map<String, String> response = new HashMap<>();
        try{
            Aerolinea aerolinea = aerolineaService.buscarAerolineaPorId(id);
            aerolineaService.eliminarAerolinea(aerolinea);
            response.put("message", "La aerolinea se elimino con exito!");
            return ResponseEntity.ok(response);
        }catch(Exception e){
            response.put("message", "Hubo un error al eliminar la aerolinea!");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
