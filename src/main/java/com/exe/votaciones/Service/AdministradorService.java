package com.exe.votaciones.Service;

import com.exe.votaciones.DTO.AdministradorDTO;
import com.exe.votaciones.Entity.Administrador;
import java.util.List;
import java.util.Optional;

public interface AdministradorService {

    List<Administrador> listarAdministradores();
    List<AdministradorDTO> listarAdministradoresActivos();
    Optional<Administrador> obtenerAdministradorPorId(Integer id);
    Administrador guardarAdministrador(Administrador administrador);
    Administrador actualizarAdministrador(Integer id, Administrador administrador);
    void eliminarAdministrador(Integer id);
}
