package com.aeropuerto.webapp.Aeropuerto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeropuerto.webapp.Aeropuerto.model.Empleado;
import com.aeropuerto.webapp.Aeropuerto.repository.EmpleadoRepository;

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
}
