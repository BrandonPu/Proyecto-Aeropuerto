package com.aeropuerto.webapp.Aeropuerto.util;
 
import javafx.scene.control.Alert;
 
public class AeropuertoAlert {
   
    private static AeropuertoAlert instance;
 
    private AeropuertoAlert(){
 
    }
 
    public static AeropuertoAlert getInstance(){
        if (instance == null) {
            instance = new AeropuertoAlert();
        }
        return instance;
    }
 
    public void mostrarAlerta(int code){
        if(code == 2001){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Estado Vacio");
            alert.setHeaderText("Estado de Vuelo vacio.");
            alert.setContentText("El estado de vuelo no puede estar vacio, por favor rellenelo.");
            alert.showAndWait();
        }else if(code == 2002){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nombre duplicado");
            alert.setHeaderText("El nombre de la Aerolínea ya existe.");
            alert.setContentText("Esta Aerolínea ya existe, por favor crea una diferente.");
            alert.showAndWait();
        }else if(code == 2003){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("IATA Existente");
            alert.setHeaderText("IATA ya existente.");
            alert.setContentText("Esta IATA ya existe, por favor crea una diferente.");
            alert.showAndWait();
        }else if(code == 2010){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmación de Registro");
            alert.setHeaderText("Confirmación de Registro.");
            alert.setContentText("Se ha registrado correctamente.");
            alert.showAndWait();
        }
    }
}
 