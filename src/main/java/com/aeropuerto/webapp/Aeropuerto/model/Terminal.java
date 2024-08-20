package com.aeropuerto.webapp.Aeropuerto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "Terminales")
public class Terminal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroTerminal;
    private String capacidad;
    private String serviciosDisponibles;
    @ManyToOne(fetch = FetchType.EAGER)
    private Aeropuerto aeropuerto;
}
