package com.exe.votaciones.DTO;


import lombok.*;

@Getter
@Setter
public class VotanteDTO {

    private Integer idVotante;
    private String nombre;
    private String correo;
    private String password;
    private Boolean haVotado;

    public VotanteDTO(Integer idVotante, String nombre, String correo, String password, Boolean haVotado) {
        this.idVotante = idVotante;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.haVotado = haVotado;
    }

    public VotanteDTO() {
    }

    public Integer getIdVotante() {
        return idVotante;
    }

    public void setIdVotante(Integer idVotante) {
        this.idVotante = idVotante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getHaVotado() {
        return haVotado;
    }

    public void setHaVotado(Boolean haVotado) {
        this.haVotado = haVotado;
    }
}
