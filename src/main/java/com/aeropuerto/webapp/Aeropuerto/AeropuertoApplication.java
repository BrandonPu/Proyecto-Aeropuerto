package com.aeropuerto.webapp.Aeropuerto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aeropuerto.webapp.Aeropuerto.system.Main;

import javafx.application.Application;

@SpringBootApplication
public class AeropuertoApplication {

	public static void main(String[] args) {

		Application.launch(Main.class, args);
		SpringApplication.run(AeropuertoApplication.class, args);
	}
}
