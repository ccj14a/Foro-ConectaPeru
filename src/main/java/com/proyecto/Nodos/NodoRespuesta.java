package com.proyecto.Nodos;

import com.proyecto.Foro.Mensaje;
import java.io.Serializable;

public class NodoRespuesta implements Serializable{
    
    private static final long serialVersionUID = 1L;
    Mensaje respuesta;
    NodoRespuesta siguiente;

    public NodoRespuesta(Mensaje respuesta) {
        this.respuesta = respuesta;
        this.siguiente = null;
    }

    public Mensaje getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Mensaje respuesta) {
        this.respuesta = respuesta;
    }

    public NodoRespuesta getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoRespuesta siguiente) {
        this.siguiente = siguiente;
    }

    
}