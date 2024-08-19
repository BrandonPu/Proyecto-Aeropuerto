package com.aeropuerto.webapp.Aeropuerto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeropuerto.webapp.Aeropuerto.model.Pasajero;
import com.aeropuerto.webapp.Aeropuerto.repository.PasajeroRepository;

@Service
public class PasajeroService implements IPasajeroService{

    @Autowired
    private PasajeroRepository pasajeroRepository;

    @Override
    public List<Pasajero> listarPasajeros(){
        return pasajeroRepository.findAll();
    }

    @Override
    public Pasajero buscarPasajeroPorId(Long id) {
        return pasajeroRepository.findById(id).orElse(null);
    }

    @Override
    public void guardarPasajero(Pasajero pasajero) {
        pasajeroRepository.save(pasajero);
    }

    @Override
    public void eliminarPasajero(Pasajero pasajero) {
        pasajeroRepository.delete(pasajero);
    }
}
