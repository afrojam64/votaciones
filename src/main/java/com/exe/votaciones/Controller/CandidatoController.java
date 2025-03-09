package com.exe.votaciones.Controller;

import com.exe.votaciones.DTO.CandidatoDTO;
import com.exe.votaciones.Entity.Candidato;
import com.exe.votaciones.Service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/candidatos")
public class CandidatoController {

    @Autowired
    private CandidatoService candidatoService;

    @GetMapping
    public List<CandidatoDTO> listarCandidatos() {
        return candidatoService.listarCandidatos()
                .stream()
                .map(c -> new CandidatoDTO(c.getIdCandidato(), c.getNombre(), c.getPropuesta()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public Candidato guardarCandidato(@RequestBody CandidatoDTO candidatoDTO) {
        Candidato candidato = new Candidato();
        candidato.setNombre(candidatoDTO.getNombre());
        candidato.setPropuesta(candidatoDTO.getPropuesta());
        return candidatoService.guardarCandidato(candidato);
    }
}
