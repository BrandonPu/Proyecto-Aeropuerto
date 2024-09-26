package com.aeropuerto.webapp.Aeropuerto.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import com.aeropuerto.webapp.Aeropuerto.system.Main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lombok.Setter;

public class TerminalFXController implements Initializable{


    @FXML
    Button btnBuscar, btnRegresar, btnAgregar, btnEditar, btnEliminar, btnVaciar;
    @FXML
    TextField tfBuscar, tfId, tfNumeroDeTerminal, tfCapacidad, tfServiciosDisponibles;
    @FXML
    TableColumn colId, colNumeroDeTerminal, colCapacidad, colServiciosDisponibles, colAereopuerto;
    @FXML
    ComboBox cmbAereopuerto;
    @FXML
    TableView tblTerminales;
    @Setter
    private Main stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
