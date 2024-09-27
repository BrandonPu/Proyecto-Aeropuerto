package com.aeropuerto.webapp.Aeropuerto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeropuerto.webapp.Aeropuerto.model.Aerolinea;
import com.aeropuerto.webapp.Aeropuerto.repository.AerolineaRepository;
import com.aeropuerto.webapp.Aeropuerto.util.AeropuertoAlert;

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
    public Boolean guardarAerolinea(Aerolinea aerolinea) {
        if (!verificarNombreAerolinea(aerolinea)) {
            aerolineaRepository.save(aerolinea);
            AeropuertoAlert.getInstance().mostrarAlerta(2010);
            return true;
        } else {
            AeropuertoAlert.getInstance().mostrarAlerta(2002);
            return false;
        }
    }

    @Override
    public void eliminarAerolinea(Aerolinea aerolinea) {
        aerolineaRepository.delete(aerolinea);
    }

    @Override
    public Boolean verificarNombreAerolinea(Aerolinea nuevaAerolinea) {
        List<Aerolinea> aerolineas = listarAerolineas();
        Boolean flag = false;
        for (Aerolinea aerolinea : aerolineas) {
            if (nuevaAerolinea.getNombre().equalsIgnoreCase(aerolinea.getNombre()) && !aerolinea.getId().equals(nuevaAerolinea.getId())) {
                flag = true;
            }
        }
        return flag;
    }

}
