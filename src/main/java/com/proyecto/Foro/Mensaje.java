package com.proyecto.Foro;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.proyecto.Nodos.NodoRespuesta;


public class Mensaje implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String autor;
    private final String contenido;
    private static int siguienteId = 1; // Variable estática para ID
    private final int id; // ID único
    private NodoRespuesta cabeza; //Creamos en Nodo cabeza
    private LocalDateTime fechaHora;
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private static final int ANCHO_MAXIMO = 100; // Ancho máximo deseado

    public Mensaje(String autor, String contenido) {
        this.id = siguienteId++;
        this.autor = autor;
        this.contenido = contenido;
        this.cabeza = null; //Lo inicialozamos como nulo
        this.fechaHora = LocalDateTime.now();
    }

    public String getFechaHora() {
        return fechaHora.format(formatter);
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getAutor() {
        return autor;
    }

    public String getContenido() {
        return contenido;
    }
    
    public int getId() {
         return id;
    }

    public void setCabeza(NodoRespuesta cabeza) {
        this.cabeza = cabeza;
    }
    
    public void addRespuesta(Mensaje respuesta){
         NodoRespuesta nuevo = new NodoRespuesta(respuesta);
        if (cabeza == null) {
            cabeza = nuevo; // Primera respuesta
        } else {
            NodoRespuesta actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente(); 
            }
            actual.setSiguiente(nuevo); 
        }
    }
    
    //Retonar la cabeza de lista
    public NodoRespuesta getRespuestas() {
        return cabeza;
    }
    
    final String ANSI_RESET = "\u001B[0m";
    public String toString() {
        final String ANSI_PURPLE = "\u001B[35m";
        //final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_YELLOW = "\u001B[33m";
        StringBuilder sb = new StringBuilder();
        // Mensaje principal
        sb.append(ANSI_PURPLE)
                .append("Mensaje ")
                .append(String.format("| User: @%-20s", autor)) // Alinea el usuario a la izquierda con 15 caracteres
                .append("Date: ")
                .append(String.format("%-20s", getFechaHora())) // Alinea la fecha con 20 caracteres
                .append(ANSI_RESET).append("\n\n");

        sb.append(ajustarTexto(contenido, ANSI_PURPLE, "")) // Ajusta el contenido del mensaje
                .append("\n\n");

        

        // Recorrer respuestas directamente sin lista auxiliar
            if (cabeza != null) {
                sb.append("   Comentarios:").append("\n");
                NodoRespuesta actual = cabeza;
                int contadorRespuesta = 1;
                while (actual != null) {
                    Mensaje respuesta = actual.getRespuesta();
                    sb.append(ANSI_YELLOW).append("   ").
                      append(String.format("(%d) User: @%-100s", contadorRespuesta++, respuesta.getAutor()))
                      .append(" Date: ")
                      .append(String.format("%-20s", respuesta.getFechaHora()))
                      .append(ANSI_RESET)
                      .append("\n\n");

                    sb.append(ajustarTexto(respuesta.getContenido(), ANSI_YELLOW, ""))
                      .append("\n\n");

                    actual = actual.getSiguiente(); // Avanzar al siguiente nodo
                }
            }

        return sb.toString();

    }
    
   

    private String ajustarTexto(String texto, String color, String indent) {
        if (texto == null) {
            return ""; // O retornar un texto por defecto si se desea
        }
        StringBuilder sb = new StringBuilder();
        int indiceInicio = 0;

        // Se aplica el indentado a todas las líneas incluyendo las que están divididas
        while (indiceInicio < texto.length()) {
            int indiceFin = Math.min(indiceInicio + ANCHO_MAXIMO, texto.length());

            sb.append(indent) // Alineación uniforme para cada línea dividida
                    .append(color)
                    .append(texto.substring(indiceInicio, indiceFin))
                    .append(ANSI_RESET);

            

            indiceInicio = indiceFin;
        }

        return sb.toString();
    }

}
