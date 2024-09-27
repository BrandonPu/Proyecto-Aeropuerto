package com.aeropuerto.webapp.Aeropuerto.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.aeropuerto.webapp.Aeropuerto.controller.UsuarioController;
import com.aeropuerto.webapp.Aeropuerto.model.Usuario;
import com.aeropuerto.webapp.Aeropuerto.service.NivelAccesoService;
import com.aeropuerto.webapp.Aeropuerto.service.UsuarioService;
import com.aeropuerto.webapp.Aeropuerto.system.Main;
import com.aeropuerto.webapp.Aeropuerto.util.PasswordUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.Setter;

@Component
public class LoginFXController implements Initializable {

    @Setter
    private Main stage;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    NivelAccesoService nivelAccesoService;

    @Autowired
    UsuarioController usuarioController;

    @FXML
    TextField tfUser;
    @FXML
    PasswordField tfPassword;
    @FXML
    Button btnIniciar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnIniciar) {
            ResponseEntity<Usuario> responseEntity = usuarioController.buscarUsuarioPorId(Long.parseLong(tfUser.getText()));
            Usuario usuario = responseEntity.getBody();
            if (usuario != null) {
                if (PasswordUtils.getInstance().checkPassword(tfPassword.getText(), usuario.getContrasenia())) {
                    System.out.println("Usuario autenticado correctamente");
                    stage.indexView();
                } else {
                    System.out.println("Contraseña incorrecta");
                }
            } else {
                System.out.println("Usuario no encontrado");
            }
        }
    }

}
