package com.aeropuerto.webapp.Aeropuerto.service;

import java.util.List;
import com.aeropuerto.webapp.Aeropuerto.model.Empleado;

public interface IEmpleadoService {

    public List<Empleado> listarEmpleados();

    public Empleado buscarEmpleadoPorId(Long id);

    public Boolean guardarEmpleado(Empleado empleado);

    public void eliminarEmpleado(Empleado empleado);

    public Boolean posicionPermitida(Empleado nuevoEmpleado);

}