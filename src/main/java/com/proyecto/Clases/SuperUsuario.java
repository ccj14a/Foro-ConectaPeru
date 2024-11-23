/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Clases;

import java.io.Serializable;

public abstract class SuperUsuario implements Serializable {

    // encapsulamiento de datos
    private String nombres;
    private String usuario;
    private String contra;
    private boolean estado;

    public SuperUsuario(String nombres, String usuario, String contra) {
        this.nombres = nombres;
        this.usuario = usuario;
        this.contra = contra;
        this.estado = false; // valor x defecto
    }

    public SuperUsuario() {
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getEstadoComoTexto() {
        return isEstado() ? "Activa" : "Inactiva";
    }

    @Override
    public String toString() {
        return "" + "nombres=" + nombres + ", usuario=" + usuario + ", contra=" + contra + ", estado="
                + getEstadoComoTexto() + "";
    }

}
