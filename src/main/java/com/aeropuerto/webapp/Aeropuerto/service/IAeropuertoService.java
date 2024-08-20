package com.aeropuerto.webapp.Aeropuerto.service;

import java.util.List;

import com.aeropuerto.webapp.Aeropuerto.model.Aeropuerto;

public interface IAeropuertoService {

    public List<Aeropuerto> listarAeropuertos();

    public Aeropuerto buscarAeropuertoPorId(Long id);

    public Aeropuerto guardarAeropuerto(Aeropuerto aeropuerto);

    public void eliminarAeropuerto(Aeropuerto aeropuerto);

}
