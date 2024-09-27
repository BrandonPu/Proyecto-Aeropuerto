package com.aeropuerto.webapp.Aeropuerto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeropuerto.webapp.Aeropuerto.model.Empleado;
import com.aeropuerto.webapp.Aeropuerto.repository.EmpleadoRepository;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

@Service
public class EmpleadoService implements IEmpleadoService{
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado buscarEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean guardarEmpleado(Empleado empleado) {
        if (posicionPermitida(empleado)) {
            empleadoRepository.save(empleado);
            return true;
        } else {
            mostrarAlerta("Posición no válida", "Posición no válida", "La posición del empleado no es correcta. Solo Gerente, Desarrollador, Limpieza, Piloto, Copiloto y Asistente Son Validos");
            return false;
        }
    }

    @Override
    public void eliminarEmpleado(Empleado empleado) {
        empleadoRepository.delete(empleado);
    }

    @Override
    public Boolean posicionPermitida(Empleado nuevoEmpleado) {
        boolean flag = false;
        String [] posiciones = {"Gerente", "Desarrollador", "Limpieza", "Piloto", "Copiloto", "Asistente"}; //Crea un array de tipo String con las posiciones aceptadas.

        for (String posicion : posiciones) { // for each recorre cada parte del arreglo asignandole el valor a "posicion"
            if (nuevoEmpleado.getPosicion().equalsIgnoreCase(posicion)) { //verifica si la "posicion" está dentro de las posiciones aceptadas.
                flag = true;
            }
        }
        return flag;
    }

    private void mostrarAlerta(String titulo, String cabecera, String contenido) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}