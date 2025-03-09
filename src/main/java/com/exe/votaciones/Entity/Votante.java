package com.exe.votaciones.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "votantes")
@Data
public class Votante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_votante")
    private int idVotante;

    private String nombre_votante;

    private String correo;

    private String password;

    private boolean ha_votado;

    public Votante(int idVotante, String nombre_votante, String correo, String password, boolean ha_votado) {
        this.idVotante = idVotante;
        this.nombre_votante = nombre_votante;
        this.correo = correo;
        this.password = password;
        this.ha_votado = ha_votado;
    }

    public Votante() {
    }

    public int getIdVotante() {
        return idVotante;
    }

    public void setIdVotante(int idVotante) {
        this.idVotante = idVotante;
    }

    public String getNombre_vontante() {
        return nombre_votante;
    }

    public void setNombre_votante(String nombre_votante) {
        this.nombre_votante = nombre_votante;
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

    public boolean isHa_votado() {
        return ha_votado;
    }

    public void setHa_votado(boolean ha_votado) {
        this.ha_votado = ha_votado;
    }
}
