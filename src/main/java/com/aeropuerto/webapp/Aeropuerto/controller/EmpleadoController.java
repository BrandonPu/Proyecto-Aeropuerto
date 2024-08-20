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

import com.aeropuerto.webapp.Aeropuerto.model.Empleado;
import com.aeropuerto.webapp.Aeropuerto.service.EmpleadoService;

@Controller
@RestController
@RequestMapping(value = "")
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/empleados")
    public List<Empleado> listarVuelos(){
        return empleadoService.listarEmpleados();
    }

    @GetMapping("/empleado")
    public ResponseEntity<Empleado> buscarEmpleadoPorId(@RequestParam Long id){
        try {
            return ResponseEntity.ok(empleadoService.buscarEmpleadoPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/empleado")
    public ResponseEntity<Map<String, String>> guardarEmpleado(@RequestBody Empleado empleado){
        Map<String, String> response = new HashMap<>();
        try {
            empleadoService.guardarEmpleado(empleado);
            response.put("Message", "Se agrego el Empleado con éxito.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al agregar el Empleado.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/empleado")
    public ResponseEntity<Map<String, String>> editarEmpleado(@RequestParam Long id, @RequestBody Empleado newEmpleado){
        Map<String, String> response = new HashMap<>();
        try {
            Empleado empleado = empleadoService.buscarEmpleadoPorId(id);
            empleado.setNombre(newEmpleado.getNombre());
            empleado.setPosicion(newEmpleado.getPosicion());
            empleado.setDepartamento(newEmpleado.getDepartamento());
            empleadoService.guardarEmpleado(empleado);
            response.put("Message", "Se edito el Empleado con éxito.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al editar el Empleado.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/empleado")
    public ResponseEntity<Map<String, String>> eliminarEmpleado(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Empleado empleado = empleadoService.buscarEmpleadoPorId(id);
            empleadoService.eliminarEmpleado(empleado);
            response.put("message", "El Empleado se elimino con éxito.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al eliminar el Empleado.");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
