package com.aeropuerto.webapp.Aeropuerto.system;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.aeropuerto.webapp.Aeropuerto.AeropuertoApplication;
import com.aeropuerto.webapp.Aeropuerto.controller.FXController.AerolineaFXController;
import com.aeropuerto.webapp.Aeropuerto.controller.FXController.AeropuertoFXController;
import com.aeropuerto.webapp.Aeropuerto.controller.FXController.EmpleadoFXController;
import com.aeropuerto.webapp.Aeropuerto.controller.FXController.IndexController;
import com.aeropuerto.webapp.Aeropuerto.controller.FXController.LoginFXController;
import com.aeropuerto.webapp.Aeropuerto.controller.FXController.PasajeroFXController;
import com.aeropuerto.webapp.Aeropuerto.controller.FXController.TerminalFXController;
import com.aeropuerto.webapp.Aeropuerto.controller.FXController.VueloFXController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{

    private ConfigurableApplicationContext applicationContext;
    private Stage stage;
    private Scene scene;

    @Override
    public void init(){
        this.applicationContext = new SpringApplicationBuilder(AeropuertoApplication.class).run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        stage.setTitle("Aeropuerto");
        Image icon = new Image(Main.class.getResourceAsStream("/image/Logo.png"));
        stage.getIcons().add(icon);
        loginView();
        //indexView();
        stage.show();
    }

    public Initializable switchScene(String fxmlName, int width, int height) throws IOException{
        Initializable resultado = null;
        FXMLLoader loader = new FXMLLoader();

        loader.setControllerFactory(applicationContext::getBean);
        InputStream archivo = Main.class.getResourceAsStream("/templates/" + fxmlName);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource("/templates/" + fxmlName));

        scene = new Scene((AnchorPane) loader.load(archivo), width, height);
        stage.setScene(scene);
        stage.sizeToScene();

        resultado = (Initializable)loader.getController();

        return resultado;
    }

    public void loginView(){
        try {
            LoginFXController loginView = (LoginFXController)switchScene("LoginFXView.fxml", 1000, 600);
            loginView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void indexView(){
        try {
            IndexController indexView = (IndexController)switchScene("Index.fxml", 1000, 600);
            indexView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void aerolineaView(){
        try {
            AerolineaFXController aerolineaView = (AerolineaFXController)switchScene("AerolineaFXView.fxml", 1000, 600);
            aerolineaView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void aeropuertoView(){
        try {
            AeropuertoFXController aeropuertoView = (AeropuertoFXController)switchScene("AeropuertoFXView.fxml", 1000, 600);
            aeropuertoView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void empleadoView(){
        try {
            EmpleadoFXController empleadoView = (EmpleadoFXController)switchScene("EmpleadoFXView.fxml", 1000, 600);
            empleadoView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pasajeroView(){
        try {
            PasajeroFXController pasajeroView = (PasajeroFXController)switchScene("PasajeroFXView.fxml", 1000, 600);
            pasajeroView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void terminalView(){
        try {
            TerminalFXController terminalView = (TerminalFXController)switchScene("TerminalFXView.fxml", 1000, 600);
            terminalView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void vueloView(){
        try {
            VueloFXController vueloView = (VueloFXController)switchScene("VueloFXView.fxml", 1000, 600);
            vueloView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
