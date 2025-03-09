package com.exe.votaciones.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "candidato")
@Getter
@Setter
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCandidato;

    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String propuesta;

    @ManyToOne
    @JoinColumn(name = "registrado_por", nullable = false)
    private Administrador registradoPor;

    public Candidato(Integer idCandidato, String nombre, String propuesta, Administrador registradoPor) {
        this.idCandidato = idCandidato;
        this.nombre = nombre;
        this.propuesta = propuesta;
        this.registradoPor = registradoPor;
    }

    public Candidato() {
    }

    public Integer getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(Integer idCandidato) {
        this.idCandidato = idCandidato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPropuesta() {
        return propuesta;
    }

    public void setPropuesta(String propuesta) {
        this.propuesta = propuesta;
    }

    public Administrador getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(Administrador registradoPor) {
        this.registradoPor = registradoPor;
    }
}
