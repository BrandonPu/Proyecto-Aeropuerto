package com.aeropuerto.webapp.Aeropuerto.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aeropuerto.webapp.Aeropuerto.model.Aerolinea;
import com.aeropuerto.webapp.Aeropuerto.service.AerolineaService;
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
public class AerolineaFXController implements Initializable {

    @FXML
    TextField tfId, tfCodigoAerolinea, tfNombre, tfPaisOrigen, tfBuscar;

    @FXML
    Button btnGuardar, btnVaciar, btnEliminar, btnRegresar, btnBuscar;

    @FXML
    TableView tblAerolineas;

    @FXML
    TableColumn colId, colCodigoAerolinea, colNombre, colPaisOrigen;


    @Setter
    private Main stage;

    @Autowired
    AerolineaService aerolineaService;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }

    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnGuardar){
            if(tfId.getText().isBlank()){
                agregarAerolinea();
            }else{
                editarAerolinea();
            }
        }else if(event.getSource() == btnVaciar){
            limpiarFormEditar();
        }else if(event.getSource() == btnEliminar){
            eliminarAerolinea();
        }else if(event.getSource() == btnBuscar){
            tblAerolineas.getItems().clear();
            if(tfBuscar.getText().isBlank()){
                cargarDatos();
            }else{
                tblAerolineas.getItems().add(buscarAerolinea());
                colId.setCellValueFactory(new PropertyValueFactory<Aerolinea, Long>("id"));
                colCodigoAerolinea.setCellValueFactory(new PropertyValueFactory<Aerolinea, String>("codigoAerolinea"));
                colNombre.setCellValueFactory(new PropertyValueFactory<Aerolinea, String>("nombre"));
                colPaisOrigen.setCellValueFactory(new PropertyValueFactory<Aerolinea, String>("paisOrigen"));
            }
        }else if(event.getSource() == btnRegresar){
            stage.indexView();
        }
    }

    public void cargarDatos(){
        tblAerolineas.setItems(listaAerolineas());
        colId.setCellValueFactory(new PropertyValueFactory<Aerolinea, Long>("id"));
        colCodigoAerolinea.setCellValueFactory(new PropertyValueFactory<Aerolinea, String>("codigoAerolinea"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Aerolinea, String>("nombre"));
        colPaisOrigen.setCellValueFactory(new PropertyValueFactory<Aerolinea, String>("paisOrigen"));
    }

    public void cargarFormEditar(){
        Aerolinea aerolinea = (Aerolinea)tblAerolineas.getSelectionModel().getSelectedItem();
        if(aerolinea != null){
            tfId.setText(Long.toString(aerolinea.getId()));
            tfCodigoAerolinea.setText(aerolinea.getCodigoAerolinea());
            tfNombre.setText(aerolinea.getNombre());
            tfPaisOrigen.setText(aerolinea.getPaisOrigen());
        }
    }

    public void limpiarFormEditar(){
        tfId.clear();
        tfCodigoAerolinea.clear();
        tfNombre.clear();
        tfPaisOrigen.clear();
    }

    public ObservableList<Aerolinea> listaAerolineas(){
        return FXCollections.observableList(aerolineaService.listarAerolineas());
    }

    public void agregarAerolinea(){
        Aerolinea aerolinea = new Aerolinea();
        aerolinea.setCodigoAerolinea(tfCodigoAerolinea.getText());
        aerolinea.setNombre(tfNombre.getText());
        aerolinea.setPaisOrigen(tfPaisOrigen.getText());
        aerolineaService.guardarAerolinea(aerolinea);
        cargarDatos();
    }

    public void editarAerolinea(){
        Aerolinea aerolinea = aerolineaService.buscarAerolineaPorId(Long.parseLong(tfId.getText()));
        aerolinea.setCodigoAerolinea(tfCodigoAerolinea.getText());
        aerolinea.setNombre(tfNombre.getText());
        aerolinea.setPaisOrigen(tfPaisOrigen.getText());
        aerolineaService.guardarAerolinea(aerolinea);
        cargarDatos();
    }

    public void eliminarAerolinea(){
        Aerolinea aerolinea = aerolineaService.buscarAerolineaPorId(Long.parseLong(tfId.getText()));
        aerolineaService.eliminarAerolinea(aerolinea);
        cargarDatos();
    }

    public Aerolinea buscarAerolinea(){
        return aerolineaService.buscarAerolineaPorId(Long.parseLong(tfBuscar.getText()));
    }
}