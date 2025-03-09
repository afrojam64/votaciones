package com.exe.votaciones.Service;

import com.exe.votaciones.Entity.Candidato;
import java.util.List;
import java.util.Optional;

public interface CandidatoService {

    List<Candidato> listarCandidatos();
    Optional<Candidato> obtenerCandidatoPorId(Integer id);
    Candidato guardarCandidato(Candidato candidato);
    Candidato actualizarCandidato(Integer id, Candidato candidato);
    void eliminarCandidato(Integer id);
}
