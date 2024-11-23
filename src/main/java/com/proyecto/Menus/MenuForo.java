/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Menus;

import com.proyecto.Clases.Usuario;
import javax.swing.JOptionPane;

public class MenuForo {

    public static int menuForo(Usuario user) {
        String menu = """

                FORO CONECTA PERU
                [1]. Crear tema
                [2]. Añadir mensaje
                [3]. Realizar un comentario
                [4]. Buscar en el foro
                [5]. Filtrar por autor
                [6]. Eliminar tema
                [7]. Eliminar mensaje
                [8]. Eliminar comentario
                [9]. Salir del foro

                """;

        String[] opciones = { "1", "2", "3", "4", "5", "6", "7", "8", "9" }; // Opciones numeradas

        int seleccion = JOptionPane.showOptionDialog(null,
                "                                    Cuenta:" + user.getEstadoComoTexto() + "\n" + menu,
                "👤:  @" + user.getUsuario(),
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        // Verificar si el usuario canceló
        if (seleccion == JOptionPane.CLOSED_OPTION) {
            System.exit(0); // Salir si se cierra el diálogo
        }

        // Devolver la opción seleccionada (sumando 1 para que sea igual a la opción del
        // menú)
        return seleccion + 1; // Convertir el índice a opción (1-7)

    }

}
