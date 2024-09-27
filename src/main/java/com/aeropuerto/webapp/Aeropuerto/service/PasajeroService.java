package com.aeropuerto.webapp.Aeropuerto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeropuerto.webapp.Aeropuerto.model.Pasajero;
import com.aeropuerto.webapp.Aeropuerto.repository.PasajeroRepository;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
            mostrarAlerta("Carácter no válida", "Carácter no válida", "El carácter del nombre que ingresó no es correcta. No se permiten números en el nombre o apellido del Pasajero.");
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

    private void mostrarAlerta(String titulo, String cabecera, String contenido) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

}
