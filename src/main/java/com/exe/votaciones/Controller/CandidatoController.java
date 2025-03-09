package com.exe.votaciones.Controller;

import com.exe.votaciones.DTO.CandidatoDTO;
import com.exe.votaciones.Entity.Administrador;
import com.exe.votaciones.Entity.Candidato;
import com.exe.votaciones.Service.AdministradorService;
import com.exe.votaciones.Service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/candidatos")
public class CandidatoController {

    @Autowired
    private CandidatoService candidatoService;

    @Autowired
    private AdministradorService administradorService;

    @GetMapping
    public List<CandidatoDTO> listarCandidatos() {
        return candidatoService.listarCandidatos()
                .stream()
                .map(c -> new CandidatoDTO(c.getIdCandidato(), c.getNombre(), c.getPropuesta()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public Candidato guardarCandidato(@RequestBody CandidatoDTO candidatoDTO) {
        // Encontrar el administrador por su ID
        Optional<Administrador> administradorOptional = administradorService.obtenerAdministradorPorId(candidatoDTO.getRegistradoPorId());

        // Validar que el administrador exista
        if (!administradorOptional.isPresent()) {
            throw new IllegalArgumentException("Administrador no encontrado");
        }

        Administrador administrador = administradorOptional.get();

        // Crear una nueva instancia de Candidato
        Candidato candidato = new Candidato();
        // Establecer el nombre del candidato
        candidato.setNombre(candidatoDTO.getNombre());
        // Establecer la propuesta del candidato
        candidato.setPropuesta(candidatoDTO.getPropuesta());
        // Establecer el administrador que registra al candidato
        candidato.setRegistradoPor(administrador);

        // Guardar la entidad candidato en la base de datos
        return candidatoService.guardarCandidato(candidato);
    }
}
