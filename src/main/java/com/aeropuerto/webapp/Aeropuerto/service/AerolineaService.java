package com.aeropuerto.webapp.Aeropuerto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeropuerto.webapp.Aeropuerto.model.Aerolinea;
import com.aeropuerto.webapp.Aeropuerto.repository.AerolineaRepository;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
        if (verificarNombreAerolinea(aerolinea)) {
            aerolineaRepository.save(aerolinea);
            return true;
        } else {
            mostrarAlerta("Aerolinea no válida", "Aerolinea no válida", "El Nombre De Aerolinea Ya Existe");
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
        
        for (Aerolinea aerolinea : aerolineas) {
            if (nuevaAerolinea.getNombre().equalsIgnoreCase(aerolinea.getNombre()) 
                    && !aerolinea.getId().equals(nuevaAerolinea.getId())) {
                return false; 
            }
        }
        return true; 
    }


     private void mostrarAlerta(String titulo, String cabecera, String contenido) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

}
