package com.exe.votaciones.DTO;

import lombok.*;

@Getter
@Setter
public class AdministradorDTO {

    private Integer idAdministrador;
    private String nombreAdmin;
    private String email;
    @Setter
    @Getter
    private String password;

    public AdministradorDTO(Integer idAdministrador, String nombreAdmin, String email, String password) {
        this.idAdministrador = idAdministrador;
        this.nombreAdmin = nombreAdmin;
        this.email = email;
        this.password = password;
    }

    public AdministradorDTO(){

    }

    public Integer getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Integer idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getNombreAdmin() {
        return nombreAdmin;
    }

    public void setNombreAdmin(String nombreAdmin) {
        this.nombreAdmin = nombreAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
