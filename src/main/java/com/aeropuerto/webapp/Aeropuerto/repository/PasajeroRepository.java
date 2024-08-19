package com.aeropuerto.webapp.Aeropuerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aeropuerto.webapp.Aeropuerto.model.Pasajero;

public interface PasajeroRepository extends JpaRepository<Pasajero, Long>{
    
}
