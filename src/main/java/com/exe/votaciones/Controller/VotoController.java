package com.exe.votaciones.Controller;

import com.exe.votaciones.DTO.VotoDTO;
import com.exe.votaciones.Service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votos")
public class VotoController {

    @Autowired
    private VotoService votoService;

    @PostMapping
    public ResponseEntity<String> registrarVoto(@RequestBody VotoDTO votoDTO) {
        boolean exito = votoService.registrarVoto(votoDTO.getIdVotante(), votoDTO.getIdCandidato());
        return exito ? ResponseEntity.ok("Voto registrado exitosamente.") : ResponseEntity.badRequest().body("El votante ya ha votado.");
    }

    @GetMapping("/resultados")
    public List<Object[]> obtenerResultados() {
        return votoService.contarVotosPorCandidato();
    }
}
