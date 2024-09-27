package com.aeropuerto.webapp.Aeropuerto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeropuerto.webapp.Aeropuerto.model.NivelAcceso;
import com.aeropuerto.webapp.Aeropuerto.repository.NivelAccesoRepository;

@Service
public class NivelAccesoService implements INivelAccesoService {

  @Autowired
  NivelAccesoRepository nivelAccesoRepository;

  @Override
  public List<NivelAcceso> listarNivelesAcceso() {
    return nivelAccesoRepository.findAll();
  }

  @Override
  public NivelAcceso buscarNivelAccesoPorId(Long id) {
    return nivelAccesoRepository.findById(id).orElse(null);
  }

  @Override
  public NivelAcceso guardarNivelAcceso(NivelAcceso nivelAcceso) {
    return nivelAccesoRepository.save(nivelAcceso);
  }

  @Override
  public void eliminarNivelAcceso(NivelAcceso nivelAcceso) {
    nivelAccesoRepository.delete(nivelAcceso);
  }

}
