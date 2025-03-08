
package com.proyecto.Nodos;

import com.proyectoUsuario.Usuario;
import java.io.Serializable;

/**
 * Representa un nodo en una lista doblemente enlazada de usuarios.
 * Cada nodo almacena un objeto {@code Usuario} y referencias a los nodos adyacentes.
 */
public class NodoUsuario implements Serializable {
    
    /**
     * Dato almacenado en el nodo, que corresponde a un objeto {@code Usuario}.
     */
    public Usuario dato;
    
    /**
     * Referencia al siguiente nodo en la lista.
     */
    public NodoUsuario siguiente;
    
    /**
     * Referencia al nodo anterior en la lista.
     */
    public NodoUsuario anterior;

    /**
     * Constructor que inicializa un nodo con un usuario.
     * 
     * @param dato el objeto {@code Usuario} que se almacenará en el nodo
     */
    public NodoUsuario(Usuario dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }

    /**
     * Obtiene el usuario almacenado en el nodo.
     * 
     * @return el objeto {@code Usuario} almacenado en el nodo
     */
    public Usuario getDato() {
        return dato;
    }

    /**
     * Establece un nuevo usuario en el nodo.
     * 
     * @param dato el nuevo objeto {@code Usuario} a almacenar en el nodo
     */
    public void setDato(Usuario dato) {
        this.dato = dato;
    }
}