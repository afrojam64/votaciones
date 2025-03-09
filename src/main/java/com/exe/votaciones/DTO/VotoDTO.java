package com.exe.votaciones.DTO;


import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
public class VotoDTO {

    private Integer idVoto;
    private Integer idVotante;
    private Integer idCandidato;
    private LocalDateTime fechaVoto;

    public VotoDTO(Integer idVoto, Integer idVotante, Integer idCandidato, LocalDateTime fechaVoto) {
        this.idVoto = idVoto;
        this.idVotante = idVotante;
        this.idCandidato = idCandidato;
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

    public Integer getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(Integer idCandidato) {
        this.idCandidato = idCandidato;
    }

    public LocalDateTime getFechaVoto() {
        return fechaVoto;
    }

    public void setFechaVoto(LocalDateTime fechaVoto) {
        this.fechaVoto = fechaVoto;
    }
}
