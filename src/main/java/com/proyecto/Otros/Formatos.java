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
    //Elimina los caracteres de escape y códigos de color
    contenido = contenido.replaceAll("\u001B\\[[;\\d]*m", "");

    // Reemplazar las etiquetas <br> por saltos de línea estándar
    contenido = contenido.replaceAll("<br>", "\n");

    //Formato adecuado para tabulaciones
    //contenido = contenido.replaceAll("(?m)^", "\t");  // Agregar tabulación al inicio de cada línea

    // Crear el JTextArea con todo el contenido
    JTextArea textArea = new JTextArea(contenido);
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    textArea.setCaretPosition(0);  // Asegurarse de que el texto se muestre desde el principio
    textArea.setEditable(false);  // El texto no debe ser editable

    // Crear JScrollPane para permitir el desplazamiento del texto
    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setPreferredSize(new Dimension(650, 400));  // Ajusta el tamaño según sea necesario

    // Mostrar el contenido en un solo cuadro de diálogo sin paginación
    JOptionPane.showMessageDialog(null, scrollPane, titulo, JOptionPane.INFORMATION_MESSAGE);
}

    
}
