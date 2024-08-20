package com.aeropuerto.webapp.Aeropuerto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeropuerto.webapp.Aeropuerto.model.Aerolinea;
import com.aeropuerto.webapp.Aeropuerto.repository.AerolineaRepository;

@Service
public class AerolineaService implements IAerolineaService{

    @Autowired
    private AerolineaRepository aerolineaRepository;

    @Override
    public List<Aerolinea> listarAerolineas() {
        return aerolineaRepository.findAll();
    }

    @Override
    public Aerolinea buscarAerolineaPorId(Long id) {
        return aerolineaRepository.findById(id).orElse(null);
    }

    @Override
    public Aerolinea guardarAerolinea(Aerolinea aerolinea) {
        return aerolineaRepository.save(aerolinea);
    }

    @Override
    public void eliminarAerolinea(Aerolinea aerolinea) {
        aerolineaRepository.delete(aerolinea);
    }

}
