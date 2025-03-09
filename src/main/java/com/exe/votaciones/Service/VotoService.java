package com.exe.votaciones.Service;

import com.exe.votaciones.Entity.Voto;

import java.util.List;
import java.util.Optional;

public interface VotoService {

    List<Voto> listarVotos();
    Optional<Voto> obtenerVotoPorId(Integer id);
    Voto registrarVoto(Voto voto);
    boolean registrarVoto(Integer idVotante, Integer idCandidato);
    void eliminarVoto(Integer id);
    List<Object[]> contarVotosPorCandidato(); // ← Agregar este método
}
