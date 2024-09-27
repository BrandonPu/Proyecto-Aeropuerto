package com.aeropuerto.webapp.Aeropuerto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeropuerto.webapp.Aeropuerto.model.Vuelo;
import com.aeropuerto.webapp.Aeropuerto.repository.VueloRepository;
import com.aeropuerto.webapp.Aeropuerto.util.AeropuertoAlert;

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
    public Boolean guardarVuelo(Vuelo vuelo) {
        if (!verificarDatoNull(vuelo)) {
            vueloRepository.save(vuelo);
            AeropuertoAlert.getInstance().mostrarAlerta(2010);
            return true;
        } else {
            AeropuertoAlert.getInstance().mostrarAlerta(2001);
            return false;
        }
    }

    @Override
    public void eliminarVuelo(Vuelo vuelo) {
        vueloRepository.delete(vuelo);
    }

    @Override
    public Boolean verificarDatoNull(Vuelo nuevoVuelo) {    
        Boolean flag = false;
        if (nuevoVuelo.getEstadoVuelo().isEmpty() || nuevoVuelo.getEstadoVuelo() == null) { //Verifica si la cadena está vacia o nula.
            flag = true;
        }
        return flag;
    }   
}
