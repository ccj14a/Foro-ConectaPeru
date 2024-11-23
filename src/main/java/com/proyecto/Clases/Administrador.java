/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Clases;

import java.io.*;
import javax.swing.JOptionPane;

public class Administrador extends SuperUsuario {

    // atributo propio
    private double salario;
    private static Administrador[] administradoresNormales = new Administrador[100];
    public static int idAdmin = 1;
    private int idUnico;
    private static int contador = 0;

    public Administrador(double salario, String nombres, String usuario, String contra) {
        super(nombres, usuario, contra);
        setEstado(true);
        this.salario = salario;
        idUnico = idAdmin++;
    }

    @Override
    public String toString() {
        return "ID: " + idUnico + ", " + super.toString() + ", Salario: " + salario;
    }

    public void agregarAdministradorEspecifico(String user) {
        if (verificarAdmin(user) != null) {
            JOptionPane.showMessageDialog(null, "Ese usuario de administrador ya existe");
            return;
        }

        double sueldo = Double.parseDouble(JOptionPane.showInputDialog("Salario: "));
        String nombres = JOptionPane.showInputDialog("Nombres: ");
        String password = JOptionPane.showInputDialog("Contraseña: ");

        Administrador admin = new Administrador(sueldo, nombres, user, password);
        administradoresNormales[contador] = admin;
        contador++;
        guardarAdmin();
        JOptionPane.showMessageDialog(null, "Administrador agregado con éxito");

    }

    public int mostrarAdministradoresEnLista() {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Administradores Registrados:\n");

        for (int i = 0; i < contador; i++) {
            mensaje.append(administradoresNormales[i]).append("\n");
        }

        // Opciones del JOptionPane
        String[] opciones = {"Desactivar Administrador", "Reactivar Administardor", "Eliminar Administrador", "Atrás"};

        // Mostramos el JOptionPane con las opciones
        int seleccion = JOptionPane.showOptionDialog(null, mensaje.toString(), "Administardores Registrados",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

        return seleccion;
    }

    public Administrador verificarAdmin(String user) {
        for (Administrador admin : administradoresNormales) {
            if (admin != null && admin.getUsuario().equals(user)) {
                return admin;
            }
        }
        return null;

    }

    public boolean loginA(String user, String contra) {

        return user.equals(getUsuario()) && contra.equals(getContra());
    }

    public void guardarAdmin() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/administradoresNormales.ser"))) {
            oos.writeObject(administradoresNormales);
            oos.writeInt(idAdmin);
            //System.out.println("Arreglo de personas serializado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargaAdmin() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/administradoresNormales.ser"))) {
            administradoresNormales = (Administrador[]) ois.readObject();
            idAdmin = ois.readInt();
            //System.out.println("Arreglo de personas deserializado correctamente.");
            contador = 0;
            for (Administrador admin : administradoresNormales) {
                if (admin != null) {
                    contador++;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Archivo de Administradores no encontrado, se creará uno nuevo al guardar.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar Administradores: " + e.getMessage());
        }
    }

    public boolean adminVacio() {
        return contador == 0;
    }

    public static String getADMIN_GENERAL() {
        return ADMIN_GENERAL;
    }

    public static String getPASSWORD_GENERAL() {
        return PASSWORD_GENERAL;
    }

    public Administrador() {
        //idUnico = idAdmin++;

    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public boolean loginAG(String user, String contra) {

        return user.equalsIgnoreCase(getADMIN_GENERAL()) && contra.equalsIgnoreCase(PASSWORD_GENERAL);
    }

    public Usuario activarUser(String usuario, Nodo cab) {
        Nodo nodoActual = cab; // Comienza desde el primer nodo (cabeza de la lista)

        while (nodoActual != null) {
            if (nodoActual.getUser().getUsuario().equals(usuario)) {
                nodoActual.getUser().setEstado(true); // Desactiva el usuario
                return nodoActual.getUser(); // Retorna el usuario desactivado
            }
            nodoActual = nodoActual.getNext(); // Avanza al siguiente nodo
        }
        return null; // No se encontró el usuario, retorna null

    }

    public Usuario desactivarUser(String usuario, Nodo cab) {
        Nodo nodoActual = cab; // Comienza desde el primer nodo (cabeza de la lista)

        while (nodoActual != null) {
            if (nodoActual.getUser().getUsuario().equals(usuario)) {
                nodoActual.getUser().setEstado(false); // Desactiva el usuario
                return nodoActual.getUser(); // Retorna el usuario desactivado
            }
            nodoActual = nodoActual.getNext(); // Avanza al siguiente nodo
        }
        return null; // No se encontró el usuario, retorna null

    }

    public Usuario EliminarUser(String user, Nodo cab) {
        Nodo nodoActual = cab; // Comienza desde el primer nodo (cabeza de la lista)
        Nodo nodoAnterior = null; // Este nodo servirá para mantener el nodo previo al actual

        // Si el primer nodo contiene el usuario a eliminar
        if (nodoActual != null && nodoActual.getUser().getUsuario().equals(user)) {
            cab = nodoActual.getNext(); // Mueve la cabeza de la lista al siguiente nodo
            return nodoActual.getUser(); // Retorna el usuario eliminado
        }

        // Recorre la lista buscando el usuario
        while (nodoActual != null && !nodoActual.getUser().getUsuario().equals(user)) {
            nodoAnterior = nodoActual; // Avanza al siguiente nodo
            nodoActual = nodoActual.getNext();
        }

        // Si el usuario no se encuentra
        if (nodoActual == null) {
            return null; // Retorna null si no se encontró el usuario
        }

        // Elimina el nodo de la lista
        nodoAnterior.setNext(nodoActual.getNext());

        return nodoActual.getUser(); // Retorna el usuario eliminado
    }
    private static final String ADMIN_GENERAL = "ADMIN";
    private static final String PASSWORD_GENERAL = "ADMIN";

}
