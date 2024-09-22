package com.aeropuerto.webapp.Aeropuerto.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Aerolineas")
public class Aerolinea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigoAerolinea;
    private String nombre;
    private String paisOrigen;
    @ManyToMany
    @JoinTable(name = "aerolineas_aeropuertos", 
    joinColumns = @JoinColumn(name = "aerolineas_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "aeropuertos_id", referencedColumnName = "id"))
    private List<Aeropuerto> aeropuertos;

    @Override
    public String toString() {
        return "Id: " + id + " | " + nombre;
    }
}
