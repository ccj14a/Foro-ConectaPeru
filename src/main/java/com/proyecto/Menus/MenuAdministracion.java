/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Menus;

import javax.swing.JOptionPane;

public class MenuAdministracion {

    public static int menuAdminG(boolean  isAdminGeneral) {
        // Menú para Administrador General
        String menuGeneral = """
            MENU DE ADMINISTRACIÓN GENERAL
            
            Gestión de Usuarios y Administradores
            [1]. Gestionar Usuarios
            [2]. Gestionar Administradores 
            [3]. Agregar Administrador     
            
            Gestión del Foro:
            [4]. Ver Foro
            [5]. Borrar Tema
            [6]. Borrar Mensaje
            [7]. Borrar Comentario
            
            Sesión.
            [8]. Cerrar Sesión
    """;

        // Menú para Administrador Específico
        String menuEspecifico = """
    MENU DE ADMINISTRACIÓN 

            [1]. Gestionar Usuarios
            [2]. Ver Perfil
            [3]. Editar perfil
            [4]. Ver Foro
            [5]. Borrar Tema
            [6]. Borrar Mensaje
            [7]. Borrar Comentario
            [8]. Cerrar Sesión
    """;

        // Determinar el menú que se mostrará según el tipo de administrador
        String menu = isAdminGeneral ? menuGeneral : menuEspecifico;

        // Opciones de acuerdo al tipo de administrador
        String[] opciones = isAdminGeneral ? new String[]{"1", "2", "3", "4", "5", "6", "7", "8"}
                : new String[]{"1", "2", "3", "4", "5", "6", "7","8"};

        // Mostrar el diálogo de opciones
        int seleccion = JOptionPane.showOptionDialog(null, menu, "Menú de Administración",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        // Verificar si el usuario canceló
        if (seleccion == JOptionPane.CLOSED_OPTION) {
            System.exit(0); // Salir si se cierra el diálogo
        }

        // Devolver la opción seleccionada (sumando 1 para que sea igual a la opción del menú)
        return seleccion + 1; // Convertir el índice a opción (1-8 o 1-7)

    }

}
