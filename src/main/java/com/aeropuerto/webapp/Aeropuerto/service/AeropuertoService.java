package com.aeropuerto.webapp.Aeropuerto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeropuerto.webapp.Aeropuerto.model.Aeropuerto;
import com.aeropuerto.webapp.Aeropuerto.repository.AeropuertoRepository;

@Service
public class AeropuertoService implements IAeropuertoService{

    @Autowired
    private AeropuertoRepository aeropuertoRepository;

    @Override
    public List<Aeropuerto> listarAeropuertos() {
        return aeropuertoRepository.findAll();
    }

    @Override
    public Aeropuerto buscarAeropuertoPorId(Long id) {
        return aeropuertoRepository.findById(id).orElse(null);
    }

    @Override
    public void guardarAeropuerto(Aeropuerto aeropuerto) {
        aeropuertoRepository.save(aeropuerto);
    }

    @Override
    public void eliminarAeropuerto(Aeropuerto aeropuerto) {
        aeropuertoRepository.delete(aeropuerto);
    }

}
