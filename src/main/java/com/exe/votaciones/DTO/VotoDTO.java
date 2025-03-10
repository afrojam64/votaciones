package com.exe.votaciones.DTO;


import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
public class VotoDTO {

    private Integer idVoto;
    private Integer idVotante;
    private String nombreVotante;
    private Integer idCandidato;
    private String nombreCandidato;
    private LocalDateTime fechaVoto;

    public VotoDTO(Integer idVoto, Integer idVotante, String nombreVotante, Integer idCandidato, String nombreCandidato, LocalDateTime fechaVoto) {
        this.idVoto = idVoto;
        this.idVotante = idVotante;
        this.nombreVotante = nombreVotante;
        this.idCandidato = idCandidato;
        this.nombreCandidato = nombreCandidato;
        this.fechaVoto = fechaVoto;
    }

    public VotoDTO() {
    }

    public Integer getIdVoto() {
        return idVoto;
    }

    public void setIdVoto(Integer idVoto) {
        this.idVoto = idVoto;
    }

    public Integer getIdVotante() {
        return idVotante;
    }

    public void setIdVotante(Integer idVotante) {
        this.idVotante = idVotante;
    }

    public String getNombreVotante() {
        return nombreVotante;
    }

    public void setNombreVotante(String nombreVotante) {
        this.nombreVotante = nombreVotante;
    }

    public Integer getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(Integer idCandidato) {
        this.idCandidato = idCandidato;
    }

    public String getNombreCandidato() {
        return nombreCandidato;
    }

    public void setNombreCandidato(String nombreCandidato) {
        this.nombreCandidato = nombreCandidato;
    }

    public LocalDateTime getFechaVoto() {
        return fechaVoto;
    }

    public void setFechaVoto(LocalDateTime fechaVoto) {
        this.fechaVoto = fechaVoto;
    }
}
