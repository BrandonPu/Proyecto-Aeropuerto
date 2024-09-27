package com.aeropuerto.webapp.Aeropuerto.controller.FXController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.hibernate.annotations.FetchProfile.FetchOverride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aeropuerto.webapp.Aeropuerto.model.Aerolinea;
import com.aeropuerto.webapp.Aeropuerto.model.Empleado;
import com.aeropuerto.webapp.Aeropuerto.model.Pasajero;
import com.aeropuerto.webapp.Aeropuerto.model.Vuelo;
import com.aeropuerto.webapp.Aeropuerto.service.PasajeroService;
import com.aeropuerto.webapp.Aeropuerto.service.VueloService;
import com.aeropuerto.webapp.Aeropuerto.system.Main;

import ch.qos.logback.core.joran.action.Action;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class PasajeroFXController implements Initializable{

    @Setter
    private Main stage;

    @Autowired
    PasajeroService pasajeroService;

    @Autowired
    VueloService vueloService;

    @FXML
    TextField tfId, tfNombre, tfApellido, tfNacionalidad, tfBuscar;

    @FXML
    Button btnGuardar, btnEliminar, btnVaciar, btnRegresar, btnBuscar;

    @FXML
    ComboBox cmbVuelo;

    @FXML
    TableView tblPasajeros;

    @FXML
    TableColumn colId, colNombre, colApellido, colNacionalidad, colVuelo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbVuelo.setItems(FXCollections.observableList(vueloService.listarVuelos()));
    } 

    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnGuardar){
            if(tfId.getText().isBlank()){
                    agregarPasajero();
                }else{
                    editarPasajero();
                }
            }else if(event.getSource() == btnVaciar){
                vaciarForm();
            }else if(event.getSource() == btnEliminar){
                eliminarPasajero();
            }else if(event.getSource() == btnBuscar){
                tblPasajeros.getItems().clear();
                if(tfBuscar.getText().isBlank()){
                    cargarDatos();
                }else{
                    tblPasajeros.setItems(listarPasajeros());
                    colId.setCellValueFactory(new PropertyValueFactory<Pasajero, Long>("id"));
                    colNombre.setCellValueFactory(new PropertyValueFactory<Pasajero, String>("nombre"));
                    colApellido.setCellValueFactory(new PropertyValueFactory<Pasajero, String>("apellido"));
                    colNacionalidad.setCellValueFactory(new PropertyValueFactory<Pasajero, String>("nacionalidad"));
                    colVuelo.setCellValueFactory(new PropertyValueFactory<Pasajero, String>("vuelo"));
                }
            }else if(event.getSource() == btnRegresar){
                stage.indexView();
            }
        

    }

    public void cargarDatos(){
        tblPasajeros.setItems(listarPasajeros());
        colId.setCellValueFactory(new PropertyValueFactory<Pasajero, Long>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Pasajero, String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Pasajero, String>("apellido"));
        colNacionalidad.setCellValueFactory(new PropertyValueFactory<Pasajero, String>("nacionalidad"));
        colVuelo.setCellValueFactory(new PropertyValueFactory<Pasajero, String>("vuelo"));
    }

    public void cargarForm(){
        Pasajero pasajero = (Pasajero) tblPasajeros.getSelectionModel().getSelectedItem();
        if (pasajero != null) {
            tfId.setText(Long.toString(pasajero.getId()));
            tfNombre.setText(pasajero.getNombre());
            tfApellido.setText(pasajero.getApellido());
            tfNacionalidad.setText(pasajero.getNacionalidad().toString()); 
            cmbVuelo.getSelectionModel().select(obtenerIndexVuelo());
        }
    }

    public void vaciarForm(){
        tfId.clear();
        tfNombre.clear();
        tfApellido.clear();
        tfNacionalidad.clear();
        cmbVuelo.getSelectionModel().clearSelection();
    }

    public ObservableList<Pasajero> listarPasajeros(){
        return FXCollections.observableList(pasajeroService.listarPasajeros());
    }

    public void agregarPasajero() {
        Pasajero pasajero = new Pasajero();
        pasajero.setNombre(tfNombre.getText());
        pasajero.setApellido(tfApellido.getText());
        pasajero.setNacionalidad(tfNacionalidad.getText());
        pasajero.setVuelo((Vuelo) cmbVuelo.getSelectionModel().getSelectedItem());
        cargarDatos();
    }

    public void editarPasajero(){
        Pasajero pasajero = pasajeroService.buscarPasajeroPorId(Long.parseLong((tfId.getText())));
        pasajero.setNombre(tfNombre.getText());
        pasajero.setApellido(tfApellido.getText());
        pasajero.setNacionalidad(tfNacionalidad.getText());
        pasajero.setVuelo((Vuelo) cmbVuelo.getSelectionModel().getSelectedItem());
        cargarDatos();
    }

    public void eliminarPasajero(){
        Pasajero pasajero = pasajeroService.buscarPasajeroPorId(Long.parseLong(tfId.getText()));
        pasajeroService.eliminarPasajero(pasajero);
        cargarDatos();
    }

    public Pasajero buscarPasajero(){
        return pasajeroService.buscarPasajeroPorId(Long.parseLong(tfBuscar.getText()));
    }

    public int obtenerIndexVuelo(){
        int index = 0;
        for (int i = 0; i < cmbVuelo.getItems().size(); i++) {
            String vueloCmb = cmbVuelo.getItems().get(i).toString();
            String pasajeroTbl = ((Pasajero) tblPasajeros.getSelectionModel().getSelectedItem()).getVuelo().toString();
            if (vueloCmb.equals(pasajeroTbl)) {
                index = 1;
                break;
            }
        }
        return index;
    }
}
