package com.exe.votaciones.DTO;


import lombok.*;

@Getter
@Setter
public class CandidatoDTO {

    private Integer idCandidato;
    private String nombre;
    private String propuesta;
    private Integer registradoPorId;
    private String nombreAdmin;

    public CandidatoDTO(Integer idCandidato, String nombre, String propuesta, Integer registradoPorId, String nombreAdmin) {
        this.idCandidato = idCandidato;
        this.nombre = nombre;
        this.propuesta = propuesta;
        this.registradoPorId = registradoPorId;
        this.nombreAdmin = nombreAdmin;
    }

    public CandidatoDTO(){

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

    public Integer getRegistradoPorId() {
        return registradoPorId;
    }

    public void setRegistradoPorId(Integer registradoPorId) {
        this.registradoPorId = registradoPorId;
    }

    public String getNombreAdmin() {
        return nombreAdmin;
    }

    public void setNombreAdmin(String nombreAdmin) {
        this.nombreAdmin = nombreAdmin;
    }
}
