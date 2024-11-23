/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Otros;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Formatos {
    public static String limpiarFormatoConsola(String texto) {
        // Eliminar caracteres de escape y códigos de color ANSI
        texto = texto.replaceAll("\u001B\\[[;\\d]*m", "");
        // Otros ajustes necesarios
        // Puedes realizar otras operaciones de limpieza según sea necesario
        return texto;
    }

    public static void mostrarMensajesEnPaginas(String contenido, String titulo) {
        // Elimina los caracteres de escape y códigos de color
        contenido = contenido.replaceAll("\u001B\\[[;\\d]*m", "");

        final int TAMANO_MAXIMO = 1000; // Ajusta este valor según sea necesario
        int inicio = 0;
        int totalLength = contenido.length();

        while (inicio < totalLength) {
            int fin = Math.min(inicio + TAMANO_MAXIMO, totalLength);
            String parte = contenido.substring(inicio, fin);

            JTextArea textArea = new JTextArea(parte);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setCaretPosition(0);  // Para asegurarse de que el texto se muestre desde el principio
            textArea.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(600, 300)); // Ajusta el tamaño del JScrollPane según sea necesario

            Object[] options = {"Anterior", "Siguiente", "Cerrar"};
            int opcion = JOptionPane.showOptionDialog(null, scrollPane, titulo,
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);

            if (opcion == JOptionPane.CLOSED_OPTION || opcion == 2) {
                break;
            } else if (opcion == 0 && inicio > 0) { // Anterior
                inicio = Math.max(0, inicio - TAMANO_MAXIMO);
            } else if (opcion == 1 && fin < totalLength) { // Siguiente
                inicio = fin;
            } else {
                break;
            }
        }
    }
    
}
