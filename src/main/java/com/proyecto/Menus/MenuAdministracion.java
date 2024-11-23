/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Menus;

import javax.swing.JOptionPane;

public class MenuAdministracion {

    public static int menuAdmin() {
        String menu = """
                MENU DE ADMINISTRACIÓN GENERAL

                [1]. Gestionar Usuarios
                [2]. Gestionar administradores
                [3]. Agregar Administrador
                [4]. Temas creados
                [5]. Administrar Mensajes
                [6]. Cerrar Sesión
                """;

        String[] opciones = { "1", "2", "3", "4", "5", "6"}; // Opciones numeradas

        int seleccion = JOptionPane.showOptionDialog(null, menu, "Menu de Administración",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        // Verificar si el usuario canceló
        if (seleccion == JOptionPane.CLOSED_OPTION) {
            System.exit(0); // Salir si se cierra el diálogo
        }

        // Devolver la opción seleccionada (sumando 1 para que sea igual a la opción del
        // menú)
        return seleccion + 1; // Convertir el índice a opción (1-5)

    }
}
