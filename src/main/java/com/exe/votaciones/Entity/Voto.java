package com.exe.votaciones.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "voto")
@Getter
@Setter
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVoto;

    @OneToOne
    @JoinColumn(name = "id_votante", unique = true, nullable = false)
    private Votante votante;

    @ManyToOne
    @JoinColumn(name = "id_candidato", referencedColumnName = "idCandidato")
    private Candidato candidato;

    @Column(nullable = false, updatable = false)
    private java.time.LocalDateTime fechaVoto = java.time.LocalDateTime.now();

    public Voto(Integer idVoto, Votante votante, Candidato candidato, LocalDateTime fechaVoto) {
        this.idVoto = idVoto;
        this.votante = votante;
        this.candidato = candidato;
        this.fechaVoto = fechaVoto;
    }

    public Voto() {
    }

    public Integer getIdVoto() {
        return idVoto;
    }

    public void setIdVoto(Integer idVoto) {
        this.idVoto = idVoto;
    }

    public Votante getVotante() {
        return votante;
    }

    public void setVotante(Votante votante) {
        this.votante = votante;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public LocalDateTime getFechaVoto() {
        return fechaVoto;
    }

    public void setFechaVoto(LocalDateTime fechaVoto) {
        this.fechaVoto = fechaVoto;
    }
}
