package com.exe.votaciones.Repository;

import com.exe.votaciones.Entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VotoRepository extends JpaRepository<Voto, Integer> {

    boolean existsByVotante_IdVotante(Integer idVotante);

    // Agregar la consulta para contar los votos por candidato
    @Query("SELECT v.candidato.idCandidato, COUNT(v) FROM Voto v GROUP BY v.candidato.idCandidato")
    List<Object[]> contarVotosPorCandidato();

}
