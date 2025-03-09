package com.exe.votaciones.Controller;

import com.exe.votaciones.DTO.CandidatoDTO;
import com.exe.votaciones.Entity.Administrador;
import com.exe.votaciones.Entity.Candidato;
import com.exe.votaciones.Service.AdministradorService;
import com.exe.votaciones.Service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/candidates")
public class CandidatoController {

    @Autowired
    private CandidatoService candidatoService;

    @Autowired
    private AdministradorService administradorService;

    @GetMapping("/list")
    public String listarCandidatos(Model model) {
        List<CandidatoDTO> candidatos = candidatoService.listarCandidatos()
                .stream()
                .map(c -> new CandidatoDTO(c.getIdCandidato(), c.getNombre(), c.getPropuesta()))
                .collect(Collectors.toList());
        model.addAttribute("candidates", candidatos);
        return "candidates/list";
    }

    @GetMapping("/register")
    public String mostrarFormularioRegistro(Model model) {
        CandidatoDTO candidato = new CandidatoDTO();
        model.addAttribute("candidate", candidato);
        return "candidates/register";
    }

    @PostMapping("/register")
    public String guardarCandidato(@ModelAttribute("candidate") CandidatoDTO candidatoDTO) {
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
        candidatoService.guardarCandidato(candidato);
        return "redirect:/candidates/list";
    }

    @GetMapping("/edit/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Integer id, Model model) {
        Optional<Candidato> candidatoOptional = candidatoService.obtenerCandidatoPorId(id);
        if (!candidatoOptional.isPresent()) {
            throw new IllegalArgumentException("Candidato no encontrado");
        }
        Candidato candidato = candidatoOptional.get();
        CandidatoDTO candidatoDTO = new CandidatoDTO(candidato.getIdCandidato(), candidato.getNombre(), candidato.getPropuesta());
        model.addAttribute("candidate", candidatoDTO);
        return "candidates/update";
    }

    @PostMapping("/update/{id}")
    public String actualizarCandidato(@PathVariable("id") Integer id, @ModelAttribute("candidate") CandidatoDTO candidatoDTO) {
        Optional<Candidato> candidatoOptional = candidatoService.obtenerCandidatoPorId(id);
        if (!candidatoOptional.isPresent()) {
            throw new IllegalArgumentException("Candidato no encontrado");
        }

        Candidato candidato = candidatoOptional.get();
        candidato.setNombre(candidatoDTO.getNombre());
        candidato.setPropuesta(candidatoDTO.getPropuesta());

        candidatoService.guardarCandidato(candidato);
        return "redirect:/candidates/list";
    }
}
