package com.proyecto.Nodos;

import com.proyecto.Foro.Mensaje;
import java.io.Serializable;


public class NodoArbol implements Serializable{
    
    private static final long serialVersionUID = 1L;
    public Mensaje mensaje;
    public NodoArbol izquierda, derecha;

    public NodoArbol(Mensaje mensaje) {
        this.mensaje= mensaje;
        this.izquierda = null;
        this.derecha = null;
    }

    public Mensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }

    public NodoArbol getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoArbol izquierda) {
        this.izquierda = izquierda;
    }

    public NodoArbol getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoArbol derecha) {
        this.derecha = derecha;
    }
    
}
