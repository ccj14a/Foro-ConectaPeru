/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Clases;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Mensaje implements Serializable {

    private static final long serialVersionUID = 1L;
    private String autor;
    private String contenido;
    private List<Mensaje> respuestas;
    private LocalDateTime fechaHora;
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private static final int ANCHO_MAXIMO = 100; // Ancho máximo deseado

    public Mensaje(String autor, String contenido) {
        this.autor = autor;
        this.contenido = contenido;
        this.respuestas = new ArrayList<>();
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

    public void addRespuesta(Mensaje respuesta) {
        respuestas.add(respuesta);
    }

    public List<Mensaje> getRespuestas() {
        return respuestas;
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

        sb.append("Comentarios").append("\n");

        // Comentarios (respuestas) con numeración solo en la respuesta
        int contadorRespuesta = 1; // Para numerar las respuestas
        for (Mensaje respuesta : respuestas) {
            sb.append(ANSI_YELLOW)
                    .append(String.format("(%d) User: @%-29s", contadorRespuesta++, respuesta.getAutor())) // Numeración
                                                                                                           // en el
                                                                                                           // usuario
                    .append(" Date: ")
                    .append(String.format("%-20s", respuesta.getFechaHora())) // Alinea la fecha
                    .append(ANSI_RESET).append("\n\n");

            sb.append(ajustarTexto(respuesta.getContenido(), ANSI_YELLOW, "    ")) // Ajusta el contenido del comentario
                    .append("\n\n");
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

            if (indiceFin < texto.length()) {
                sb.append("\n"); // Salto de línea si hay más texto
            }

            indiceInicio = indiceFin;
        }

        return sb.toString();
    }

}
