package com.aeropuerto.webapp.Aeropuerto.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.aeropuerto.webapp.Aeropuerto.system.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import lombok.Setter;

@Component
public class IndexController implements Initializable{

    @Setter
    private Main stage;

    @FXML
    MenuItem btnAerolineas, btnAeropuertos, btnEmpleados, btnPasajeros, btnTerminales, btnVuelos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnAerolineas){
            stage.aerolineaView();
        }else if(event.getSource() == btnAeropuertos){
            stage.aeropuertoView();
        }else if(event.getSource() == btnEmpleados){
            stage.empleadoView();
        }else if(event.getSource() == btnPasajeros){
            stage.pasajeroView();
        }else if(event.getSource() == btnTerminales){
            stage.terminalView();
        }else if(event.getSource() == btnVuelos){
            stage.vueloView();
        }
    }  
}
