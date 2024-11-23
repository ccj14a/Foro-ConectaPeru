/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Menus;

import com.proyecto.Clases.Usuario;
import javax.swing.JOptionPane;

public class MenuUusario {

    public static int menuUsuario(Usuario user) {
        String menu = """

                MENU DE USUARIO
                [1]. Ir al foro
                [2]. Mi perfil
                [3]. Editar perfil
                [4]. Cerrar Sesión

                """;

        String[] opciones = { "1", "2", "3", "4" }; // Opciones numeradas

        int seleccion = JOptionPane.showOptionDialog(null,
                "                                Cuenta: " + user.getEstadoComoTexto() + "\n" + menu,
                " 👤:   @" + user.getUsuario(),
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        // Verificar si el usuario canceló
        if (seleccion == JOptionPane.CLOSED_OPTION) {
            System.exit(0); // Salir si se cierra el diálogo
        }

        // Devolver la opción seleccionada (sumando 1 para que sea igual a la opción del
        // menú)
        return seleccion + 1; // Convertir el índice a opción (1-3)

    }

}
