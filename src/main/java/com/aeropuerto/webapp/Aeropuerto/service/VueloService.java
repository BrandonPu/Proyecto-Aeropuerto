package com.aeropuerto.webapp.Aeropuerto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeropuerto.webapp.Aeropuerto.model.Vuelo;
import com.aeropuerto.webapp.Aeropuerto.repository.VueloRepository;

@Service
public class VueloService implements IVueloService {

    @Autowired
    private VueloRepository vueloRepository;

    @Override
    public List<Vuelo> listarVuelos() {
        return vueloRepository.findAll();
    }

    @Override
    public Vuelo buscarVueloPorId(Long id) {
        return vueloRepository.findById(id).orElse(null);
    }

    @Override
    public Vuelo guardarVuelo(Vuelo vuelo) {
        return vueloRepository.save(vuelo);
    }

    @Override
    public void eliminarVuelo(Vuelo vuelo) {
        vueloRepository.delete(vuelo);
    }   
}
