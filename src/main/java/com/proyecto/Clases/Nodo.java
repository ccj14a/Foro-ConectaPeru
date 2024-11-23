/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Clases;

import java.io.Serializable;


public class Nodo implements Serializable{
    private static final long serialVersionUID = 1L;
    //encapsulamiento de datos
    private Usuario user;
    private Nodo next;

    public Nodo(Usuario user) {
        this.user = user;
        this.next = null;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Nodo getNext() {
        return next;
    }

    public void setNext(Nodo next) {
        this.next = next;
    }

}
