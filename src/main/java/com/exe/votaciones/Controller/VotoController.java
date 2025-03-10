package com.exe.votaciones.Controller;

import com.exe.votaciones.DTO.VotoDTO;
import com.exe.votaciones.Entity.Candidato;
import com.exe.votaciones.Entity.Votante;
import com.exe.votaciones.Entity.Voto;
import com.exe.votaciones.Service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/votes")
public class VotoController {

    @Autowired
    private VotoService votoService;

    @PostMapping
    public ResponseEntity<String> registrarVoto(@RequestBody VotoDTO votoDTO) {
        boolean exito = votoService.registrarVoto(votoDTO.getIdVotante(), votoDTO.getIdCandidato());
        return exito ? ResponseEntity.ok("Voto registrado exitosamente.") : ResponseEntity.badRequest().body("El votante ya ha votado.");
    }

    @GetMapping
    public List<VotoDTO> listarVotos() {
        return votoService.listarVotos().stream()
                .map(voto -> new VotoDTO(voto.getIdVoto(), voto.getVotante().getIdVotante(), voto.getCandidato().getIdCandidato(), voto.getFechaVoto()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VotoDTO> obtenerVotoPorId(@PathVariable Integer id) {
        Optional<Voto> votoOpt = votoService.obtenerVotoPorId(id);
        return votoOpt.map(voto -> ResponseEntity.ok(new VotoDTO(voto.getIdVoto(), voto.getVotante().getIdVotante(), voto.getCandidato().getIdCandidato(), voto.getFechaVoto())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VotoDTO> actualizarVoto(@PathVariable Integer id, @RequestBody VotoDTO votoDTO) {
        Optional<Voto> votoOpt = votoService.obtenerVotoPorId(id);
        if (votoOpt.isPresent()) {
            Voto voto = votoOpt.get();
            Votante votante = new Votante();
            votante.setIdVotante(votoDTO.getIdVotante());
            Candidato candidato = new Candidato();
            candidato.setIdCandidato(votoDTO.getIdCandidato());
            voto.setVotante(votante);
            voto.setCandidato(candidato);
            voto.setFechaVoto(votoDTO.getFechaVoto());
            Voto votoActualizado = votoService.registrarVoto(voto);
            VotoDTO votoActualizadoDTO = new VotoDTO(votoActualizado.getIdVoto(), votoActualizado.getVotante().getIdVotante(), votoActualizado.getCandidato().getIdCandidato(), votoActualizado.getFechaVoto());
            return ResponseEntity.ok(votoActualizadoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVoto(@PathVariable Integer id) {
        Optional<Voto> votoOpt = votoService.obtenerVotoPorId(id);
        if (votoOpt.isPresent()) {
            votoService.eliminarVoto(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/resultados")
    public List<Object[]> obtenerResultados() {
        return votoService.contarVotosPorCandidato();
    }
}
