package com.aeropuerto.webapp.Aeropuerto.service;

import java.util.List;

import com.aeropuerto.webapp.Aeropuerto.model.Vuelo;

public interface IVueloService {

    public List<Vuelo> listarVuelos();
    
    public Vuelo buscarVueloPorId(Long id);

    public Boolean guardarVuelo(Vuelo vuelo);

    public void eliminarVuelo(Vuelo vuelo);

    public Boolean verificarDatoNull(Vuelo nuevoVuelo);
}
