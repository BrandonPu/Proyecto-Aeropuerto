package com.aeropuerto.webapp.Aeropuerto.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aeropuerto.webapp.Aeropuerto.model.Aeropuerto;
import com.aeropuerto.webapp.Aeropuerto.model.Vuelo;
import com.aeropuerto.webapp.Aeropuerto.service.AeropuertoService;
import com.aeropuerto.webapp.Aeropuerto.system.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class AeropuertoFXController implements Initializable {

    @Setter
    private Main stage;

    @Autowired
    AeropuertoService aeropuertoService;

    @FXML
    TextField tfId, tfIata, tfNombre, tfCiudad, tfPais,tfBuscar;

    @FXML
    Button btnGuardar, btnVaciar, btnEliminar, btnBuscar, btnRegresar;

    @FXML
    TableView tblAeropuertos;

    @FXML
    TableColumn colId, colIata, colNombre, colCiudad, colPais;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }

    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnGuardar) {
            if (tfId.getText().isBlank()) {
                agregarAeropuerto();
            } else {
                editarAeropuerto();
            }
        }else if(event.getSource() == btnVaciar){
            vaciarForm();
        }else if(event.getSource() == btnRegresar){
            stage.indexView();
        }else if(event.getSource() == btnEliminar){
            eliminarAeropuerto();
        }else if(event.getSource() == btnBuscar){
            tblAeropuertos.getItems().clear();
            if(tfBuscar.getText().isBlank()){
                cargarDatos();
            }else{
                tblAeropuertos.getItems().add(buscarAeropuerto());
                colId.setCellValueFactory(new PropertyValueFactory<Aeropuerto, Long>("id"));
                colIata.setCellValueFactory(new PropertyValueFactory<Aeropuerto, String>("iata"));
                colNombre.setCellValueFactory(new PropertyValueFactory<Aeropuerto, String>("nombre"));
                colCiudad.setCellValueFactory(new PropertyValueFactory<Aeropuerto, String>("ciudad"));
                colPais.setCellValueFactory(new PropertyValueFactory<Aeropuerto, String>("pais"));
            }
        }
    }

    public void cargarDatos() {
        tblAeropuertos.setItems(listarAeropuertos());
        colId.setCellValueFactory(new PropertyValueFactory<Aeropuerto, Long>("id"));
        colIata.setCellValueFactory(new PropertyValueFactory<Aeropuerto, String>("iata"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Aeropuerto, String>("nombre"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<Aeropuerto, String>("ciudad"));
        colPais.setCellValueFactory(new PropertyValueFactory<Aeropuerto, String>("pais"));
    }

    public ObservableList<Aeropuerto> listarAeropuertos() {
        return FXCollections.observableList(aeropuertoService.listarAeropuertos());
    }

    public void agregarAeropuerto() {
        Aeropuerto aeropuerto = new Aeropuerto();
        aeropuerto.setIata(tfIata.getText());
        aeropuerto.setNombre(tfNombre.getText());
        aeropuerto.setCiudad(tfCiudad.getText());
        aeropuerto.setPais(tfPais.getText());
        aeropuertoService.guardarAeropuerto(aeropuerto);
        cargarDatos();
    }

    public void cargarForm(){
        Aeropuerto aeropuerto = (Aeropuerto) tblAeropuertos.getSelectionModel().getSelectedItem();
        if (aeropuerto != null) {
            tfId.setText(Long.toString(aeropuerto.getId()));
            tfIata.setText(aeropuerto.getIata());
            tfNombre.setText(aeropuerto.getNombre());
            tfCiudad.setText(aeropuerto.getCiudad());
            tfPais.setText(aeropuerto.getPais());
        }
    }

    public void vaciarForm(){
        tfId.clear();
        tfIata.clear();
        tfNombre.clear();
        tfCiudad.clear();
        tfPais.clear();
    }

    public void editarAeropuerto(){
        Aeropuerto aeropuerto = aeropuertoService.buscarAeropuertoPorId(Long.parseLong(tfId.getText()));
        aeropuerto.setIata(tfIata.getText());
        aeropuerto.setNombre(tfNombre.getText());
        aeropuerto.setCiudad(tfCiudad.getText());
        aeropuerto.setPais(tfPais.getText());
        aeropuertoService.guardarAeropuerto(aeropuerto);
        cargarDatos();

    }

    public void eliminarAeropuerto(){
        Aeropuerto aeropuerto = aeropuertoService.buscarAeropuertoPorId(Long.parseLong(tfId.getText()));
        aeropuertoService.eliminarAeropuerto(aeropuerto);
        cargarDatos();
    }

    public Aeropuerto buscarAeropuerto(){
        return aeropuertoService.buscarAeropuertoPorId(Long.parseLong(tfBuscar.getText()));
    }

}
