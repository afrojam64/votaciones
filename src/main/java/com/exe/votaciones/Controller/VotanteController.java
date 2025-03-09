package com.exe.votaciones.Controller;

import com.exe.votaciones.DTO.VotanteDTO;
import com.exe.votaciones.Entity.Votante;
import com.exe.votaciones.Service.VotanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/votantes")
public class VotanteController {

    @Autowired
    private VotanteService votanteService;

    @GetMapping
    public List<VotanteDTO> listarVotantes() {
        return votanteService.listarVotantes()
                .stream()
                .map(v -> new VotanteDTO(v.getIdVotante(), v.getNombre_vontante(), v.getCorreo(), v.getPassword()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public Votante guardarVotante(@RequestBody VotanteDTO votanteDTO) {
        Votante votante = new Votante();
        votante.setNombre_votante(votanteDTO.getNombre());
        votante.setCorreo(votanteDTO.getCorreo());
        votante.setPassword(votanteDTO.getPassword());
        return votanteService.guardarVotante(votante);
    }
}
