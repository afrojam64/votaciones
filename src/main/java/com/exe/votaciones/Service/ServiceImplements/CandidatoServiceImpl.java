package com.exe.votaciones.Service.ServiceImplements;

import com.exe.votaciones.Entity.Candidato;
import com.exe.votaciones.Repository.CandidatoRepository;
import com.exe.votaciones.Service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatoServiceImpl implements CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Override
    public List<Candidato> listarCandidatos() {
        return candidatoRepository.findAll();
    }

    @Override
    public Optional<Candidato> obtenerCandidatoPorId(Integer id) {
        return candidatoRepository.findById(id);
    }

    @Override
    public Candidato guardarCandidato(Candidato candidato) {
        return candidatoRepository.save(candidato);
    }

    @Override
    public Candidato actualizarCandidato(Integer id, Candidato candidato) {
        if (candidatoRepository.existsById(id)) {
            candidato.setIdCandidato(id);
            return candidatoRepository.save(candidato);
        }
        return null;
    }

    @Override
    public void eliminarCandidato(Integer id) {
        candidatoRepository.deleteById(id);
    }
}
