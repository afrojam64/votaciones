package com.exe.votaciones.Controller;

import com.exe.votaciones.DTO.VotoDTO;
import com.exe.votaciones.Entity.Candidato;
import com.exe.votaciones.Entity.Votante;
import com.exe.votaciones.Entity.Voto;
import com.exe.votaciones.Service.CandidatoService;
import com.exe.votaciones.Service.VotanteService;
import com.exe.votaciones.Service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/votes")
public class VotoController {

    @Autowired
    private VotoService votoService;

    @Autowired
    private CandidatoService candidatoService;

    @Autowired
    private VotanteService votanteService;

    @GetMapping("/list")
    public String listarVotos(Model model) {
        List<Voto> votos = votoService.listarVotos();
        model.addAttribute("votes", votos);
        return "votes/list";
    }

    @GetMapping("/cast/{voterId}")
    public String mostrarFormularioVotacion(@PathVariable Integer voterId, Model model) {
        model.addAttribute("candidates", candidatoService.listarCandidatos());
        model.addAttribute("voterId", voterId);
        return "votes/cast";
    }

    @PostMapping("/cast")
    public String procesarVoto(@RequestParam Integer voterId,
                               @RequestParam Integer candidateId) {
        boolean resultado = votoService.registrarVoto(voterId, candidateId);
        if (resultado) {
            return "redirect:/votes/success";
        }
        return "redirect:/votes/error?message=Ya has emitido tu voto";
    }

    @GetMapping("/results")
    public String mostrarResultados(Model model) {
        List<Object[]> resultados = votoService.contarVotosPorCandidato();
        model.addAttribute("results", resultados);
        return "votes/results";
    }

    @GetMapping("/success")
    public String mostrarExito() {
        return "votes/success";
    }

    @GetMapping("/error")
    public String mostrarError(@RequestParam String message, Model model) {
        model.addAttribute("errorMessage", message);
        return "votes/error";
    }

    @GetMapping("/{id}")
    public String mostrarVoto(@PathVariable Integer id, Model model) {
        Optional<Voto> votoOpt = votoService.obtenerVotoPorId(id);
        if (votoOpt.isPresent()) {
            model.addAttribute("vote", votoOpt.get());
            return "votes/view";
        }
        return "redirect:/votes/list";
    }

    @PostMapping("/update/{id}")
    public String actualizarVoto(@PathVariable Integer id, @ModelAttribute VotoDTO votoDTO) {
        Optional<Voto> votoOpt = votoService.obtenerVotoPorId(id);
        if (votoOpt.isPresent()) {
            Voto voto = votoOpt.get();
            Votante votante = new Votante();
            votante.setIdVotante(votoDTO.getIdVotante());
            Candidato candidato = new Candidato();
            candidato.setIdCandidato(votoDTO.getIdCandidato());
            voto.setVotante(votante);
            voto.setCandidato(candidato);
            votoService.registrarVoto(voto);
        }
        return "redirect:/votes/list";
    }

    @GetMapping("/delete/{id}")
    public String eliminarVoto(@PathVariable Integer id) {
        votoService.eliminarVoto(id);
        return "redirect:/votes/list";
    }
}
