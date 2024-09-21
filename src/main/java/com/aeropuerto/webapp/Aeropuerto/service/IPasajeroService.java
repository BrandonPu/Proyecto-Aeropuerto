package com.aeropuerto.webapp.Aeropuerto.service;

import java.util.List;

import com.aeropuerto.webapp.Aeropuerto.model.Pasajero;

public interface IPasajeroService {

    public List<Pasajero> listarPasajeros();

    public Pasajero buscarPasajeroPorId(Long id);

    public Boolean guardarPasajero(Pasajero pasajero);

    public void eliminarPasajero(Pasajero pasajero);

    public Boolean verificarNumeroEnDato(Pasajero nuevoPasajero);
}
