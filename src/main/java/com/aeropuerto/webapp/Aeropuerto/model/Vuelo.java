package com.aeropuerto.webapp.Aeropuerto.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Vuelos")
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroVuelo;
    private String estadoVuelo;
    private Date fechaSalida;
    @ManyToOne(fetch = FetchType.LAZY)
    private Aerolinea aerolinea;
    @ManyToMany
    @JoinTable(name = "vuelos_empleados",
    joinColumns = @JoinColumn(name = "vuelos_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "empleados_id", referencedColumnName = "id"))
    private List<Empleado> empleados;

}
