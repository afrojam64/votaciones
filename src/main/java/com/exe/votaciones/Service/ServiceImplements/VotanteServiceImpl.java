package com.exe.votaciones.Service.ServiceImplements;

import com.exe.votaciones.Entity.Votante;
import com.exe.votaciones.Repository.VotanteRepository;
import com.exe.votaciones.Service.VotanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VotanteServiceImpl implements VotanteService {

    @Autowired
    private VotanteRepository votanteRepository;

    @Override
    public List<Votante> listarVotantes() {
        return votanteRepository.findAll();
    }

    @Override
    public Optional<Votante> obtenerVotantePorId(Integer id) {
        return votanteRepository.findById(id);
    }

    @Override
    public Votante guardarVotante(Votante votante) {
        return votanteRepository.save(votante);
    }

    @Override
    public Votante actualizarVotante(Integer id, Votante votante) {
        if (votanteRepository.existsById(id)) {
            votante.setIdVotante(id);
            return votanteRepository.save(votante);
        }
        return null;
    }

    @Override
    public void eliminarVotante(Integer id) {
        votanteRepository.deleteById(id);
    }
}
