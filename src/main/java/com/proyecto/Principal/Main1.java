
package com.proyecto.Principal;

import com.proyecto.Ventanas.Home;

public class Main1 {

    public static void main(String[] args) {
        clearConsole();
        Home hom=new Home();
        hom.setVisible(true);
        hom.setLocationRelativeTo(null);
        
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("No se pudo limpiar la consola.");
        }
    }

}
