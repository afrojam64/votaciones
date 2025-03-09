package com.exe.votaciones.Repository;

import com.exe.votaciones.Entity.Votante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotanteRepository extends JpaRepository<Votante, Integer> {

}
