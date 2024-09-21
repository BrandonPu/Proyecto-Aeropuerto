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
    public Boolean guardarTerminal(Terminal terminal) {
        if (!verificarCapacidadMaxima(terminal)) {
            terminalRepository.save(terminal);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void eliminarTerminal(Terminal terminal) {
        terminalRepository.delete(terminal);
    }

    @Override
    public Boolean verificarCapacidadMaxima(Terminal nuevaTerminal) {
        Boolean flag = false;
        if (Integer.parseInt(nuevaTerminal.getCapacidad()) > 100) { //Parsea el String y lo pasa a int para verificar si el dato es mayor a 100.
            flag = true;
        }
        return flag;
    }
}
