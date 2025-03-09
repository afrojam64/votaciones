package com.exe.votaciones.Repository;


import com.exe.votaciones.Entity.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatoRepository extends JpaRepository<Candidato, Integer> {

}
