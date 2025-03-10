package com.exe.votaciones.Controller;

import com.exe.votaciones.DTO.VotanteDTO;
import com.exe.votaciones.Entity.Votante;
import com.exe.votaciones.Service.VotanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/voters")
public class VotanteController {

    @Autowired
    private VotanteService votanteService;

    @GetMapping("/list")
    public String listarVotantes(Model model) {
        List<VotanteDTO> votantes = votanteService.listarVotantes()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        model.addAttribute("voters", votantes);
        return "voters/list";
    }

    @GetMapping("/register")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("voter", new VotanteDTO());
        return "voters/register";
    }

    @PostMapping("/register")
    public String guardarVotante(@ModelAttribute("voter") VotanteDTO votanteDTO) {
        Votante votante = new Votante();
        votante.setNombre_votante(votanteDTO.getNombre());
        votante.setCorreo(votanteDTO.getCorreo());
        votante.setPassword(votanteDTO.getPassword());
        votante.setHa_votado(false);
        votanteService.guardarVotante(votante);
        return "redirect:/voters/list";
    }

    @GetMapping("/update/{id}")
    public String mostrarFormularioEdicion(@PathVariable Integer id, Model model) {
        Optional<Votante> votanteOpt = votanteService.obtenerVotantePorId(id);
        if (votanteOpt.isPresent()) {
            model.addAttribute("voter", convertToDTO(votanteOpt.get()));
            return "voters/update";
        }
        return "redirect:/voters/list";
    }

    @PostMapping("/update/{id}")
    public String actualizarVotante(@PathVariable Integer id, @ModelAttribute("voter") VotanteDTO votanteDTO) {
        Optional<Votante> votanteOpt = votanteService.obtenerVotantePorId(id);
        if (votanteOpt.isPresent()) {
            Votante votante = votanteOpt.get();
            votante.setNombre_votante(votanteDTO.getNombre());
            votante.setCorreo(votanteDTO.getCorreo());
            votanteService.actualizarVotante(id, votante);
        }
        return "redirect:/voters/list";
    }

    @GetMapping("/delete/{id}")
    public String eliminarVotante(@PathVariable Integer id) {
        votanteService.eliminarVotante(id);
        return "redirect:/voters/list";
    }

    private VotanteDTO convertToDTO(Votante votante) {
        return new VotanteDTO(
                votante.getIdVotante(),
                votante.getNombre_votante(),    // Mantenemos el nombre original
                votante.getCorreo(),
                null, // No enviamos la contraseña
                votante.isHa_votado()          // Usamos el getter para boolean
        );
    }
}
