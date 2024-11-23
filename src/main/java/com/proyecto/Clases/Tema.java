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

public class Tema implements Serializable {

    private static final long serialVersionUID = 1L;
    private String titulo;
    private String autor;
    private List<Mensaje> mensajes;
    private LocalDateTime fechaHora;
    public static int siguienteId = 1; // Variable estática para asignar ID único a cada tema
    private int id; // ID único para cada tema

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public Tema(String titulo, String autor) {
        this.id = siguienteId++;
        this.titulo = titulo;
        this.autor = autor;
        this.mensajes = new ArrayList<>();
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

    public void addMensaje(Mensaje mensaje) {
        mensajes.add(mensaje);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_RESET = "\u001B[0m";
        StringBuilder sb = new StringBuilder();
        sb.append(ANSI_GREEN)
                .append(String.format("%-6s", "Tema (" + id + "): ")) // Cambié aquí para que se vea como Tema (1)
                .append(String.format("%-55s", titulo)) // Ancho fijo para el título
                .append(String.format("(User: @%-12s", autor)) // Ancho fijo para el usuario
                .append("Date: ")
                .append(String.format("%-20s", getFechaHora())) // Ancho fijo para la fecha
                .append(")").append(ANSI_RESET).append("\n\n");

        int index = 1;
        for (Mensaje mensaje : mensajes) {
            sb.append("[")
                    .append(index++).append("] ").append(mensaje).append("\n");
        }

        System.out.println(
                "__________________________________________________________________________________________________________________");

        return sb.toString();
    }

}
