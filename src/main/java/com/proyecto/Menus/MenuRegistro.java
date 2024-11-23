/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Menus;

import javax.swing.JOptionPane;

public class MenuRegistro {

    public static int menuRegistro() {
        String menu = """
            BIENVENIDO A "PERÚ CONECTA"
            *Ingresar como:
            [1] Usuario
            [2] Administrador\n
            *No tienes una cuenta? Crear
            [3] Usuario
            [4] Cerrar Aplicación\n
            """;

        String[] opciones = {"1", "2", "3", "4"}; // Opciones numeradas

        int seleccion = JOptionPane.showOptionDialog(null, menu, "CONVERSA360",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        // Verificar si el usuario canceló
        if (seleccion == JOptionPane.CLOSED_OPTION) {
            System.exit(0); // Salir si se cierra el diálogo
        }

        // Devolver la opción seleccionada (sumando 1 para que sea igual a la opción del menú)
        return seleccion + 1; // Convertir el índice a opción (1-4)

    }

}
