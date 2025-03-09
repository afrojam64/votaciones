package com.exe.votaciones.Service;

import com.exe.votaciones.Entity.Votante;
import java.util.List;
import java.util.Optional;

public interface VotanteService {

    List<Votante> listarVotantes();
    Optional<Votante> obtenerVotantePorId(Integer id);
    Votante guardarVotante(Votante votante);
    Votante actualizarVotante(Integer id, Votante votante);
    void eliminarVotante(Integer id);
}
