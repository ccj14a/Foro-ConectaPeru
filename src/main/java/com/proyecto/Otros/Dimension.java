/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Otros;

import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author ALE
 */
public class Dimension {
       public static void showMessage(String message, String title) {
          // Crear un JOptionPane
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
        
        // Crear el JDialog a partir del JOptionPane
        JDialog dialog = optionPane.createDialog(title);
        
        // Obtener las dimensiones de la pantalla
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        // Obtener el tamaño de la ventana del JOptionPane
        int dialogWidth = dialog.getWidth();
        int dialogHeight = dialog.getHeight();
        
        // Calcular la ubicación para que el JOptionPane se ubique en la esquina superior derecha
         int x = (int) (screenSize.getWidth() - dialogWidth);  // Colocar en el borde derecho
        int y = (int) ((screenSize.getHeight() - dialogHeight) / 2); // Centrado en Y
        
        // Establecer la ubicación
        dialog.setLocation(x, y);
        
        // Mostrar el mensaje
        dialog.setVisible(true);
    }
    
}
