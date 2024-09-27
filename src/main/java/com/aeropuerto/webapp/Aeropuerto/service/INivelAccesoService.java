package com.aeropuerto.webapp.Aeropuerto.service;

import java.util.List;

import com.aeropuerto.webapp.Aeropuerto.model.NivelAcceso;

public interface INivelAccesoService {

    public List<NivelAcceso> listarNivelesAcceso();

    public NivelAcceso buscarNivelAccesoPorId(Long id);

    public NivelAcceso guardarNivelAcceso(NivelAcceso nivelAcceso);

    public void eliminarNivelAcceso(NivelAcceso nivelAcceso);
  
}
