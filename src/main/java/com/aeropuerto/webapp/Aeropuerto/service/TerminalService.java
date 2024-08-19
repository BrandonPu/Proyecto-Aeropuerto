package com.aeropuerto.webapp.Aeropuerto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeropuerto.webapp.Aeropuerto.model.Terminal;
import com.aeropuerto.webapp.Aeropuerto.repository.TerminalRepository;

@Service
public class TerminalService implements ITerminalService{

    @Autowired
    private TerminalRepository terminalRepository;

    @Override
    public List<Terminal> listarTerminales() {
        return terminalRepository.findAll();
    }

    @Override
    public Terminal buscarTerminalPorId(Long id) {
        return terminalRepository.findById(id).orElse(null);
    }

    @Override
    public void guardarTerminal(Terminal terminal) {
        terminalRepository.save(terminal);
    }

    @Override
    public void eliminarTerminal(Terminal terminal) {
        terminalRepository.delete(terminal);
    }

}
