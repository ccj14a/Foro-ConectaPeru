/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyectoUsuario;

import com.proyectoSuperUsuario.SuperUsuario;
import java.io.Serializable;

/**
 *
 * @author ALE
 */
public class Usuario extends SuperUsuario implements Serializable {

    private static final long serialVersionUID = 1L;

    public Usuario(String nombres, String usuario, String contra, int edad) {
        super(nombres, usuario, contra, edad);
        setEstado(true);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
