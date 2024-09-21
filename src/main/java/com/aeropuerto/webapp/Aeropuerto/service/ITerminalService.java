package com.aeropuerto.webapp.Aeropuerto.service;

import java.util.List;

import com.aeropuerto.webapp.Aeropuerto.model.Terminal;

public interface ITerminalService {
    public List<Terminal> listarTerminales();

    public Terminal buscarTerminalPorId(Long id);
    
    public Boolean guardarTerminal(Terminal terminal);

    public void eliminarTerminal(Terminal terminal);

    public Boolean verificarCapacidadMaxima(Terminal nuevaTerminal);
}
