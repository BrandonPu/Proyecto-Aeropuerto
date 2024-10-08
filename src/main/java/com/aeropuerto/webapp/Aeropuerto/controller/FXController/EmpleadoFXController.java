package com.aeropuerto.webapp.Aeropuerto.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aeropuerto.webapp.Aeropuerto.model.Empleado;
import com.aeropuerto.webapp.Aeropuerto.service.EmpleadoService;
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
public class EmpleadoFXController implements Initializable{
    @FXML
    TextField tfId, tfNombre, tfPosicion, tfDepartamento, tfBuscar;

    @FXML
    Button btnGuardar, btnLimpiar, btnRegresar, btnEliminar, btnBuscar;

    @FXML
    TableView tblEmpleados;

    @FXML
    TableColumn colId, colPosicion, colDepartamento, colNombre;

    @Setter
    private Main stage;

    @Autowired
    EmpleadoService empleadoService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnGuardar) {
            if(tfId.getText().isBlank()){
                guardarEmpleado();
            }else{
                editarEmpleado();
            }
        } else if (event.getSource() == btnLimpiar) {
            limpiarForm();
        } else if (event.getSource() == btnRegresar) {
            stage.indexView();
        } else if (event.getSource() == btnEliminar) {
            eliminarEmpleado();
        } else if (event.getSource() == btnBuscar) {
            tblEmpleados.getItems().clear();
            if (tfBuscar.getText().isBlank()) {
                cargarDatos();
            } else {
                tblEmpleados.getItems().add(buscarEmpleado());
                colId.setCellValueFactory(new PropertyValueFactory<Empleado, Long>("id"));
                colNombre.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombre"));
                colPosicion.setCellValueFactory(new PropertyValueFactory<Empleado, String>("posicion"));
                colDepartamento.setCellValueFactory(new PropertyValueFactory<Empleado, String>("departamento"));
            }

        }
    }

    public void cargarDatos() {
        tblEmpleados.setItems(listarEmpleados());
        colId.setCellValueFactory(new PropertyValueFactory<Empleado, Long>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombre"));
        colPosicion.setCellValueFactory(new PropertyValueFactory<Empleado, String>("posicion"));
        colDepartamento.setCellValueFactory(new PropertyValueFactory<Empleado, String>("departamento"));
    }

    public void cargarForm() {
        Empleado empleado = (Empleado) tblEmpleados.getSelectionModel().getSelectedItem();
        if (empleado != null) {
            tfId.setText(empleado.getId().toString());
            tfNombre.setText(empleado.getNombre());
            tfPosicion.setText(empleado.getPosicion());
            tfDepartamento.setText(empleado.getDepartamento());
        }
    }

    public void limpiarForm() {
        tfId.clear();
        tfNombre.clear();
        tfPosicion.clear();
        tfDepartamento.clear();
    }

    public ObservableList<Empleado> listarEmpleados() {
        return FXCollections.observableList(empleadoService.listarEmpleados());
    }

    public void guardarEmpleado() {
        Empleado empleado = new Empleado();
        empleado.setNombre(tfNombre.getText());
        empleado.setPosicion(tfPosicion.getText());
        empleado.setDepartamento(tfDepartamento.getText());
        empleadoService.guardarEmpleado(empleado);
        cargarDatos();
    }

    public void editarEmpleado() {
        Empleado empleado = empleadoService.buscarEmpleadoPorId(Long.parseLong(tfId.getText()));
        empleado.setNombre(tfNombre.getText());
        empleado.setPosicion(tfPosicion.getText());
        empleado.setDepartamento(tfDepartamento.getText());
        empleadoService.guardarEmpleado(empleado);
        cargarDatos();
    }

    public void eliminarEmpleado() {
        Empleado empleado = empleadoService.buscarEmpleadoPorId(Long.parseLong(tfId.getText()));
        empleadoService.eliminarEmpleado(empleado);
        cargarDatos();
    }

    public Empleado buscarEmpleado() {
        return empleadoService.buscarEmpleadoPorId(Long.parseLong(tfBuscar.getText()));
    }
}
