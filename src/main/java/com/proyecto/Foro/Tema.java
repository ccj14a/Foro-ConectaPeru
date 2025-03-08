package com.proyecto.Foro;

import com.proyecto.Nodos.NodoArbol;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Tema implements Serializable {

    private static final long serialVersionUID = 1L;
    private String titulo;
    private String autor;
    public NodoArbol raiz;
    private LocalDateTime fechaHora;
    public static int siguienteId = 1; // Variable estática para asignar ID único a cada tema
    private int id; // ID único para cada tema

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public Tema(String titulo, String autor) {
        this.id = siguienteId++;
        this.titulo = titulo;
        this.autor = autor;
        this.raiz = null;
        this.fechaHora = LocalDateTime.now();
    }

    public String getFechaHora() {
        return fechaHora.format(formatter);
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getTitulo() {

        return titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public NodoArbol getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
    }

    //Metodo para insertar un mensaje en el arbol
    public void addMensaje(Mensaje mensaje) {
        raiz = insertarEnArbol(raiz, mensaje);
    }

    public NodoArbol insertarEnArbol(NodoArbol nodo, Mensaje mensaje) {
        if (nodo == null) {
            return new NodoArbol(mensaje);
        }
        // Comparación basada en ID 
        if (mensaje.getId() < nodo.mensaje.getId()) {
            nodo.izquierda = insertarEnArbol(nodo.izquierda, mensaje);
        } else {
            nodo.derecha = insertarEnArbol(nodo.derecha, mensaje);
        }
        return nodo;
    }

    public void InOrdenArbol(NodoArbol nodo, StringBuilder sb, int nivel) {
        if (nodo == null) {
            return;
        }
        InOrdenArbol(nodo.izquierda, sb, nivel + 1);  // Recorre izquierda
        sb.append("".repeat(nivel)) // Imprime nodo con indentación
                .append("[")
                .append(nodo.mensaje.getId()).append("] ")
                .append(nodo.mensaje).append("\n");
        InOrdenArbol(nodo.derecha, sb, nivel + 1);  // Recorre derecha
    }

    public Mensaje buscarMensajePorPosicion(int posicion) {
        Contador contador = new Contador();
        return buscarMensajeInOrden(raiz, posicion, contador);
    }

    private Mensaje buscarMensajeInOrden(NodoArbol nodo, int posicion, Contador contador) {
        if (nodo == null) {
            return null;
        }

        // Buscar en el subárbol izquierdo
        Mensaje mensajeIzq = buscarMensajeInOrden(nodo.izquierda, posicion, contador);
        if (mensajeIzq != null) {
            return mensajeIzq;
        }

        // Verificar si este nodo es el que buscamos
        contador.valor++;
        if (contador.valor == posicion) {
            return nodo.mensaje;
        }

        // Buscar en el subárbol derecho
        return buscarMensajeInOrden(nodo.derecha, posicion, contador);
    }

    // Clase auxiliar para llevar la cuenta de la posición en el recorrido
    private static class Contador {

        int valor = 0;
    }

    public void eliminarMensaje(int idMensaje) {
        raiz = eliminarMensajeEnArbol(raiz, idMensaje);
    }

    public NodoArbol eliminarMensajeEnArbol(NodoArbol nodo, int idMensaje) {
        if (nodo == null) {
            return null;
        }

        if (idMensaje < nodo.mensaje.getId()) {
            nodo.izquierda = eliminarMensajeEnArbol(nodo.izquierda, idMensaje);
        } else if (idMensaje > nodo.mensaje.getId()) {
            nodo.derecha = eliminarMensajeEnArbol(nodo.derecha, idMensaje);
        } else {
            // Caso 1: Nodo sin hijos o con un solo hijo
            if (nodo.izquierda == null) {
                return nodo.derecha;
            }
            if (nodo.derecha == null) {
                return nodo.izquierda;
            }

            // Caso 2: Nodo con dos hijos (buscar sucesor in-order)
            NodoArbol sucesor = encontrarMinimo(nodo.derecha);
            nodo.mensaje = sucesor.mensaje;
            nodo.derecha = eliminarMensajeEnArbol(nodo.derecha, sucesor.mensaje.getId());
        }
        return nodo;
    }

    public NodoArbol encontrarMinimo(NodoArbol nodo) {
        while (nodo.izquierda != null) {
            nodo = nodo.izquierda;
        }
        return nodo;
    }

    public Mensaje buscarMensajeEnArbol(int idMensaje) {
        return buscarMensajeRec(raiz, idMensaje);
    }

    private Mensaje buscarMensajeRec(NodoArbol nodo, int idMensaje) {
        if (nodo == null) {
            return null;
        }
        if (idMensaje == nodo.getMensaje().getId()) {
            return nodo.getMensaje();
        } else if (idMensaje < nodo.getMensaje().getId()) {
            return buscarMensajeRec(nodo.getIzquierda(), idMensaje);
        } else {
            return buscarMensajeRec(nodo.getDerecha(), idMensaje);
        }
    }

    public List<Mensaje> obtenerMensajes() {
        List<Mensaje> mensajes = new ArrayList<>();
        obtenerMensajesInOrden(raiz, mensajes);
        return mensajes;
    }

    private void obtenerMensajesInOrden(NodoArbol nodo, List<Mensaje> mensajes) {
        if (nodo == null) {
            return;
        }
        obtenerMensajesInOrden(nodo.izquierda, mensajes);
        mensajes.add(nodo.mensaje);  // Agregar el mensaje en orden
        obtenerMensajesInOrden(nodo.derecha, mensajes);
    }

    public void filtrarMensajesPorAutor(String autor, StringBuilder sb) {
        obtenerMensajesPorAutor(raiz, autor, sb);
    }

    private void obtenerMensajesPorAutor(NodoArbol nodo, String autor, StringBuilder sb) {
        if (nodo == null) {
            return;
        }
        obtenerMensajesPorAutor(nodo.izquierda, autor, sb);  // Recorre izquierda

        if (nodo.mensaje.getAutor().equalsIgnoreCase(autor)) {
            sb.append(nodo.mensaje).append("\n"); // Agrega mensaje
        }

        obtenerMensajesPorAutor(nodo.derecha, autor, sb);  // Recorre derecha
    }

    @Override
    public String toString() {
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_RESET = "\u001B[0m";
        StringBuilder sb = new StringBuilder();
        sb.append(ANSI_GREEN)
                .append(String.format("%-11s", "Tema (" + id + "): ")) // Cambié aquí para que se vea como Tema (1)
                .append(String.format("%-90s", titulo)) // Ancho fijo para el título
                .append(String.format("(User: @%-12s", autor)) // Ancho fijo para el usuario
                .append("Date: ")
                .append(String.format("%-20s", getFechaHora())) // Ancho fijo para la fecha
                .append(")").append(ANSI_RESET).append("\n\n");

        int index = 1;
        InOrdenArbol(raiz, sb, 1);
        

        return sb.toString();

    }

}
