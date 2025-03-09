package com.exe.votaciones.Service.ServiceImplements;

import com.exe.votaciones.Entity.Administrador;
import com.exe.votaciones.Repository.AdministradorRepository;
import com.exe.votaciones.Service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdministradorServiceImpl implements AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Override
    public List<Administrador> listarAdministradores() {
        return administradorRepository.findAll();
    }

    @Override
    public Optional<Administrador> obtenerAdministradorPorId(Integer id) {
        return administradorRepository.findById(id);
    }

    @Override
    public Administrador guardarAdministrador(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    @Override
    public Administrador actualizarAdministrador(Integer id, Administrador administrador) {
        if (administradorRepository.existsById(id)) {
            administrador.setIdAdministrador(id);
            return administradorRepository.save(administrador);
        }
        return null;
    }

    @Override
    public void eliminarAdministrador(Integer id) {
        administradorRepository.deleteById(id);
    }
}