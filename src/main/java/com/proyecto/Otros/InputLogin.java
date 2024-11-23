package com.proyecto.Otros;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class InputLogin {

    public static String pedirUsuario() {
        return JOptionPane.showInputDialog("Ingrese su nombre de usuario:");
    }

    public static String pedirContra() {
        JPasswordField passwordField = new JPasswordField();
        int option = JOptionPane.showConfirmDialog(null, passwordField, "Ingrese su contraseña:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            return new String(passwordField.getPassword());
        } else {
            return null; // Cancelado por el usuario
        }
    }
}
