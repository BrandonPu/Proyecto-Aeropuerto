package com.aeropuerto.webapp.Aeropuerto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeropuerto.webapp.Aeropuerto.model.Aeropuerto;
import com.aeropuerto.webapp.Aeropuerto.repository.AeropuertoRepository;
import com.aeropuerto.webapp.Aeropuerto.utils.AeropuertoAlert;

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
    public Boolean guardarAeropuerto(Aeropuerto aeropuerto) {
        if (!verificarIataDuplicado(aeropuerto)) {
            aeropuertoRepository.save(aeropuerto);
            AeropuertoAlert.getInstance().mostrarAlerta(2010);
            return true;
        } else {
            AeropuertoAlert.getInstance().mostrarAlerta(2003);
            return false;
        }
    }

    @Override
    public void eliminarAeropuerto(Aeropuerto aeropuerto) {
        aeropuertoRepository.delete(aeropuerto);
    }

    @Override
    public Boolean verificarIataDuplicado(Aeropuerto nuevoAeropuerto) {
        List<Aeropuerto> aeropuertos = listarAeropuertos();
        Boolean flag = false;
        for (Aeropuerto aeropuerto : aeropuertos) {
            if (nuevoAeropuerto.getIata().equalsIgnoreCase(aeropuerto.getIata()) && !aeropuerto.getId().equals(nuevoAeropuerto.getId())) {
                flag = true;
            }
        }
        return flag;
    }

}
