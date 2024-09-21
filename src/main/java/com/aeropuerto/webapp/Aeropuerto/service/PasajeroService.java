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
    public Boolean guardarPasajero(Pasajero pasajero) {
        if (!verificarNumeroEnDato(pasajero)) {
            pasajeroRepository.save(pasajero);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void eliminarPasajero(Pasajero pasajero) {
        pasajeroRepository.delete(pasajero);
    }

    @Override
    public Boolean verificarNumeroEnDato(Pasajero nuevoPasajero) {
        Boolean flag = false;
        String [] numeros = {"1", "2", "3", "4", "5", "6", "7", "8", "9"}; //Crea un Array de tipo String con los números.

        for (String numero : numeros) { //for each recorre cada número en el arreglo para verificar si se encuentra en el nombre o el apellido.
            if (nuevoPasajero.getNombre().contains(numero) || nuevoPasajero.getApellido().contains(numero)) { //El contains verifica si el numero está dentro del nombre o el apellido, si es así, retorna un true.
                flag = true;
            }
        }
        return flag;
    }
}
