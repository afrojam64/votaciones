package com.exe.votaciones.Controller;

import com.exe.votaciones.DTO.AdministradorDTO;
import com.exe.votaciones.Entity.Administrador;
import com.exe.votaciones.Service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @GetMapping
    public List<AdministradorDTO> listarAdministradores() {
        return administradorService.listarAdministradores()
                .stream()
                .map(admin -> new AdministradorDTO(admin.getIdAdministrador(), admin.getNombreAdmin(), admin.getEmail(), admin.getPassword()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministradorDTO> obtenerAdministrador(@PathVariable Integer id) {
        return administradorService.obtenerAdministradorPorId(id)
                .map(admin -> ResponseEntity.ok(new AdministradorDTO(admin.getIdAdministrador(), admin.getNombreAdmin(), admin.getEmail(), admin.getPassword())))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Administrador guardarAdministrador(@RequestBody AdministradorDTO administradorDTO) {
        Administrador administrador = new Administrador();
        administrador.setNombreAdmin(administradorDTO.getNombreAdmin());
        administrador.setEmail(administradorDTO.getEmail());
        administrador.setPassword(administradorDTO.getPassword());
        return administradorService.guardarAdministrador(administrador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrador> actualizarAdministrador(@PathVariable Integer id, @RequestBody AdministradorDTO administradorDTO) {
        Administrador administrador = new Administrador();
        administrador.setNombreAdmin(administradorDTO.getNombreAdmin());
        administrador.setEmail(administradorDTO.getEmail());
        administrador.setPassword(administradorDTO.getPassword());
        Administrador actualizado = administradorService.actualizarAdministrador(id, administrador);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAdministrador(@PathVariable Integer id) {
        administradorService.eliminarAdministrador(id);
        return ResponseEntity.noContent().build();
    }
}
