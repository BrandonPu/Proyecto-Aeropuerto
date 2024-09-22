package com.aeropuerto.webapp.Aeropuerto.controller.FXController;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aeropuerto.webapp.Aeropuerto.model.Aerolinea;
import com.aeropuerto.webapp.Aeropuerto.model.Empleado;
import com.aeropuerto.webapp.Aeropuerto.model.Vuelo;
import com.aeropuerto.webapp.Aeropuerto.service.AerolineaService;
import com.aeropuerto.webapp.Aeropuerto.service.EmpleadoService;
import com.aeropuerto.webapp.Aeropuerto.service.VueloService;
import com.aeropuerto.webapp.Aeropuerto.system.Main;

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
public class VueloFXController implements Initializable{

    @Setter
    private Main stage;

    @Autowired
    VueloService vueloService;

    @Autowired
    AerolineaService aerolineaService;

    @Autowired
    EmpleadoService empleadoService;

    @FXML
    TextField tfId, tfNumero, tfEstado, tfFechaSalida, tfBuscar;

    @FXML
    Button btnGuardar, btnVaciar, btnEliminar, btnBuscar, btnRegresar;

    @FXML
    ComboBox cmbAerolineas, cmbEmpleados;

    @FXML
    TableView tblVuelos;

    @FXML
    TableColumn colId, colNumero, colEstado, colFechaSalida, colAerolinea, colEmpleado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbAerolineas.setItems(FXCollections.observableList(aerolineaService.listarAerolineas()));
        cmbEmpleados.setItems(FXCollections.observableList(empleadoService.listarEmpleados()));
        cargarDatos();
    }

    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnGuardar){
            if(tfId.getText().isBlank()){
                agregarVuelo();
            }else{
                editarVuelo();
            }
        }else if(event.getSource() == btnVaciar){
            vaciarForm();
        }else if(event.getSource() == btnEliminar){
            eliminarVuelo();
        }else if(event.getSource() == btnBuscar){
            tblVuelos.getItems().clear();
            if(tfBuscar.getText().isBlank()){
                cargarDatos();
            }else{
                tblVuelos.getItems().add(buscarVuelo());
                colId.setCellValueFactory(new PropertyValueFactory<Vuelo, Long>("id"));
                colNumero.setCellValueFactory(new PropertyValueFactory<Vuelo, String>("numeroVuelo"));
                colEstado.setCellValueFactory(new PropertyValueFactory<Vuelo, String>("estadoVuelo"));
                colFechaSalida.setCellValueFactory(new PropertyValueFactory<Vuelo, String>("fechaSalida"));
                colAerolinea.setCellValueFactory(new PropertyValueFactory<Vuelo, String>("aerolinea"));
                colEmpleado.setCellValueFactory(new PropertyValueFactory<Vuelo, String>("empleados"));
            }
        }else if(event.getSource() == btnRegresar){
            stage.indexView();
        }
    }

    public void cargarDatos(){
        tblVuelos.setItems(listarVuelos());
        colId.setCellValueFactory(new PropertyValueFactory<Vuelo, Long>("id"));
        colNumero.setCellValueFactory(new PropertyValueFactory<Vuelo, String>("numeroVuelo"));
        colEstado.setCellValueFactory(new PropertyValueFactory<Vuelo, String>("estadoVuelo"));
        colFechaSalida.setCellValueFactory(new PropertyValueFactory<Vuelo, String>("fechaSalida"));
        colAerolinea.setCellValueFactory(new PropertyValueFactory<Vuelo, String>("aerolinea"));
        colEmpleado.setCellValueFactory(new PropertyValueFactory<Vuelo, String>("empleados"));
    }

    public void cargarForm(){
        Vuelo vuelo = (Vuelo) tblVuelos.getSelectionModel().getSelectedItem();
        if (vuelo != null) {
            tfId.setText(Long.toString(vuelo.getId()));
            tfNumero.setText(vuelo.getNumeroVuelo());
            tfEstado.setText(vuelo.getEstadoVuelo());
            tfFechaSalida.setText(vuelo.getFechaSalida().toString()); 
            cmbAerolineas.getSelectionModel().select(obtenerIndexAerolinea());
            cmbEmpleados.getSelectionModel().select(obtenerIndexEmpleado());
        }
    }

    public void vaciarForm(){
        tfId.clear();
        tfNumero.clear();
        tfEstado.clear();
        tfFechaSalida.clear();
        cmbAerolineas.getSelectionModel().clearSelection();
        cmbEmpleados.getSelectionModel().clearSelection();
    }

    public ObservableList<Vuelo> listarVuelos(){
        return FXCollections.observableList(vueloService.listarVuelos());
    }

    public void agregarVuelo() {
        Vuelo vuelo = new Vuelo();
        vuelo.setNumeroVuelo(tfNumero.getText());
        vuelo.setEstadoVuelo(tfEstado.getText());
        String fechaSalida = tfFechaSalida.getText();
        Date fecha = Date.valueOf(fechaSalida);
        vuelo.setFechaSalida(fecha);
        vuelo.setAerolinea((Aerolinea) cmbAerolineas.getSelectionModel().getSelectedItem());

        List<Empleado> empleados = new ArrayList<>();
        Empleado empleadoSeleccionado = (Empleado) cmbEmpleados.getSelectionModel().getSelectedItem();
        if(empleadoSeleccionado != null){
            empleados.add(empleadoSeleccionado);
        }
        vuelo.setEmpleados(empleados);
        vueloService.guardarVuelo(vuelo);
        cargarDatos();
    }

    public void editarVuelo(){
        Vuelo vuelo = vueloService.buscarVueloPorId(Long.parseLong((tfId.getText())));
        vuelo.setNumeroVuelo(tfNumero.getText());
        vuelo.setEstadoVuelo(tfEstado.getText());
        String fechaSalida = tfFechaSalida.getText();
        Date fecha = Date.valueOf(fechaSalida);
        vuelo.setFechaSalida(fecha);
        vuelo.setAerolinea((Aerolinea) cmbAerolineas.getSelectionModel().getSelectedItem());

        List<Empleado> empleados = new ArrayList<>();
        Empleado empleadoSeleccionado = (Empleado) cmbEmpleados.getSelectionModel().getSelectedItem();
        if(empleadoSeleccionado != null){
            empleados.add(empleadoSeleccionado);
        }
        vuelo.setEmpleados(empleados);
        vueloService.guardarVuelo(vuelo);
        cargarDatos();
    }

    public void eliminarVuelo(){
        Vuelo vuelo = vueloService.buscarVueloPorId(Long.parseLong(tfId.getText()));
        vueloService.eliminarVuelo(vuelo);
        cargarDatos();
    }

    public Vuelo buscarVuelo(){
        return vueloService.buscarVueloPorId(Long.parseLong(tfBuscar.getText()));
    }

    public int obtenerIndexAerolinea(){
        int index = 0;
        for (int i = 0; i < cmbAerolineas.getItems().size(); i++) {
            String aerolineaCmb = cmbAerolineas.getItems().get(i).toString();
            String vueloTbl = ((Vuelo) tblVuelos.getSelectionModel().getSelectedItem()).getAerolinea().toString();
            if (aerolineaCmb.equals(vueloTbl)) {
                index = 1;
                break;
            }
        }
        return index;
    }

    public int obtenerIndexEmpleado(){
        int index = 0;
        for (int i = 0; i < cmbEmpleados.getItems().size(); i++) {
            String empleadoCmb = cmbEmpleados.getItems().get(i).toString();
            String vueloTbl = ((Vuelo) tblVuelos.getSelectionModel().getSelectedItem()).getEmpleados().toString();
            if (empleadoCmb.equals(vueloTbl)) {
                index = 1;
                break;
            }
        }
        return index;
    }
}
