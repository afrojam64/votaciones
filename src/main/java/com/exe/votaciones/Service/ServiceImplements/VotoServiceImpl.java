package com.exe.votaciones.Service.ServiceImplements;

import com.exe.votaciones.Entity.Candidato;
import com.exe.votaciones.Entity.Votante;
import com.exe.votaciones.Entity.Voto;
import com.exe.votaciones.Repository.CandidatoRepository;
import com.exe.votaciones.Repository.VotanteRepository;
import com.exe.votaciones.Repository.VotoRepository;
import com.exe.votaciones.Service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VotoServiceImpl implements VotoService {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private VotanteRepository votanteRepository;

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Override
    public List<Voto> listarVotos() {
        return votoRepository.findAll();
    }

    @Override
    public Optional<Voto> obtenerVotoPorId(Integer id) {
        return votoRepository.findById(id);
    }

    @Override
    public Voto registrarVoto(Voto voto) {
        return votoRepository.save(voto);
    }

    @Override
    public boolean registrarVoto(Integer idVotante, Integer idCandidato) {
        if (votoRepository.existsByVotante_IdVotante(idVotante)) {
            return false; // Ya votó
        }

        Votante votante = votanteRepository.findById(idVotante).orElse(null);
        Candidato candidato = candidatoRepository.findById(idCandidato).orElse(null);

        if (votante == null || candidato == null) {
            return false; // No existen
        }

        Voto voto = new Voto();
        voto.setVotante(votante);
        voto.setCandidato(candidato);
        votoRepository.save(voto);

        return true;
    }

    @Override
    public List<Object[]> contarVotosPorCandidato() {
        return votoRepository.contarVotosPorCandidato(); // ← Llamando al método en el repositorio
    }

    @Override
    public void eliminarVoto(Integer id) {
        votoRepository.deleteById(id);
    }
}
