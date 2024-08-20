package com.aeropuerto.webapp.Aeropuerto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aeropuerto.webapp.Aeropuerto.model.Aerolinea;

public interface AerolineaRepository extends JpaRepository<Aerolinea, Long> {
    
}
