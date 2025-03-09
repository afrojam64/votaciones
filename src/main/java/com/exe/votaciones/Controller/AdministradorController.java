package com.exe.votaciones.Controller;

import com.exe.votaciones.DTO.AdministradorDTO;
import com.exe.votaciones.Entity.Administrador;
import com.exe.votaciones.Service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @GetMapping("/list")
    public String listarAdministradores(Model model) {
        List<AdministradorDTO> admins = administradorService.listarAdministradores()
                .stream()
                .map(admin -> new AdministradorDTO(admin.getIdAdministrador(), admin.getNombreAdmin(), admin.getEmail(), admin.getPassword()))
                .collect(Collectors.toList());
        model.addAttribute("admins", admins);
        return "admin/list";
    }

    @GetMapping("/{id}")
    public String obtenerAdministrador(@PathVariable Integer id, Model model) {
        administradorService.obtenerAdministradorPorId(id)
                .ifPresent(admin -> model.addAttribute("admin", new AdministradorDTO(admin.getIdAdministrador(), admin.getNombreAdmin(), admin.getEmail(), admin.getPassword())));
        return "admin/register";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("admin", new AdministradorDTO());
        return "admin/register";
    }

    @PostMapping("/register")
    public String guardarAdministrador(@ModelAttribute AdministradorDTO administradorDTO) {
        Administrador administrador = new Administrador();
        administrador.setNombreAdmin(administradorDTO.getNombreAdmin());
        administrador.setEmail(administradorDTO.getEmail());
        administrador.setPassword(administradorDTO.getPassword());
        administradorService.guardarAdministrador(administrador);
        return "redirect:/admin/list";
    }

    @GetMapping("/edit/{id}")
    public String editarAdministrador(@PathVariable Integer id, Model model) {
        administradorService.obtenerAdministradorPorId(id)
                .ifPresent(admin -> model.addAttribute("admin", new AdministradorDTO(admin.getIdAdministrador(), admin.getNombreAdmin(), admin.getEmail(), admin.getPassword())));
        return "admin/update";
    }

    @PostMapping("/update/{id}")
    public String actualizarAdministrador(@PathVariable Integer id, @ModelAttribute AdministradorDTO administradorDTO) {
        Administrador administrador = new Administrador();
        administrador.setNombreAdmin(administradorDTO.getNombreAdmin());
        administrador.setEmail(administradorDTO.getEmail());
        administrador.setPassword(administradorDTO.getPassword());
        administradorService.actualizarAdministrador(id, administrador);
        return "redirect:/admin/list";
    }

    @GetMapping("/delete/{id}")
    public String eliminarAdministrador(@PathVariable Integer id) {
        administradorService.eliminarAdministrador(id);
        return "redirect:/admin/list";
    }
}
