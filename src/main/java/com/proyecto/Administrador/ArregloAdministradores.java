/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Administrador;

import javax.swing.JOptionPane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.proyecto.Otros.SeguridadAdminG;
import com.proyectoUsuario.*;
import com.proyecto.Nodos.NodoUsuario;
import java.io.Serializable;

/**
 *
 * @author ALE
 */
public class ArregloAdministradores implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Administrador[] administradores = new Administrador[100];
    private static int contador = 0;

    public ArregloAdministradores() {
    }

    public void agregarAdministrador(String user) {
        if (verificarAdmin(user) != null) {
            JOptionPane.showMessageDialog(null, "Ese usuario de administrador ya existe");
            return;
        }

        double sueldo = Double.parseDouble(JOptionPane.showInputDialog("Salario: "));
        String nombres = JOptionPane.showInputDialog("Nombres: ");
        String password = JOptionPane.showInputDialog("Contraseña: ");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("edad:"));

        Administrador admin = new Administrador(nombres, user, password, edad, sueldo);
        administradores[contador] = admin;
        contador++;
        guardarAdmin();
        JOptionPane.showMessageDialog(null, "Administrador agregado con éxito");

    }

    public boolean isVacio() {
        return contador == 0;
    }

    public Usuario eliminarUsuario(String nombreUsuario, ListaEnlazadaDobleUsuarios lista) {
        NodoUsuario cabeza = lista.getCabeza();
        NodoUsuario cola = lista.getCola();

        if (cabeza == null) {
            return null;
        }

        // Si el usuario a eliminar está en la cabeza
        if (cabeza.dato.getUsuario().equals(nombreUsuario)) {
            Usuario eliminado = cabeza.dato;
            cabeza = cabeza.siguiente;

            if (cabeza != null) {
                cabeza.anterior = null;
            } else {
                cola = null; // Si la lista queda vacía
            }

            // Actualizar la lista con la nueva cabeza y cola
            lista.setCabeza(cabeza);
            lista.setCola(cola);

            return eliminado;
        }

        NodoUsuario actual = cabeza;
        while (actual != null && !actual.dato.getUsuario().equals(nombreUsuario)) {
            actual = actual.siguiente;
        }

        if (actual == null) {
            return null; // No se encontró el usuario
        }

        // Si el usuario a eliminar está en la cola
        if (actual == cola) {
            cola = cola.anterior;
            if (cola != null) {
                cola.siguiente = null;
            } else {
                cabeza = null; // La lista se vació completamente
            }

            lista.setCabeza(cabeza);
            lista.setCola(cola);
            return actual.dato;
        }

        // Si el usuario está en el medio de la lista
        actual.anterior.siguiente = actual.siguiente;
        actual.siguiente.anterior = actual.anterior;

        return actual.dato;
    }

    public Administrador verificarAdmin(String user) {
        for (Administrador admin : administradores) {
            if (admin != null && admin.getUsuario().equals(user)) {
                return admin;
            }
        }
        return null;

    }

    public void guardarAdmin() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/administradoresNormales.ser"))) {
            oos.writeObject(administradores);
            //System.out.println("Arreglo de personas serializado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargaAdmin() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/administradoresNormales.ser"))) {
            administradores = (Administrador[]) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de Administradores no encontrado, se creará uno nuevo al guardar.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar Administradores: " + e.getMessage());
        }
    }

    public boolean loginAG(String user, String contra) {

        return user.equalsIgnoreCase(SeguridadAdminG.getADMIN_GENERAL()) && contra.equalsIgnoreCase(SeguridadAdminG
                .getPASSWORD_GENERAL());
    }

    public Administrador loginAE(String user, String contra) {

        for (Administrador admin : administradores) {
            if (admin != null && admin.getUsuario().equals(user) && admin.getContra().equals(contra)) {
                return admin;
            }
        }
        return null;
    }

    public Usuario activarUser(String usuario, NodoUsuario cab) {
        NodoUsuario nodoActual = cab; // Comienza desde el primer nodo (cabeza de la lista)

        while (nodoActual != null) {
            if (nodoActual.dato.getUsuario().equals(usuario)) {
                nodoActual.getDato().setEstado(true); // Desactiva el usuario
                return nodoActual.getDato(); // Retorna el usuario desactivado
            }
            nodoActual = nodoActual.siguiente; // Avanza al siguiente nodo
        }
        return null; // No se encontró el usuario, retorna null

    }

    public Usuario desactivarUser(String usuario, NodoUsuario cab) {
        NodoUsuario nodoActual = cab; // Comienza desde el primer nodo (cabeza de la lista)

        while (nodoActual != null) {
            if (nodoActual.dato.getUsuario().equals(usuario)) {
                nodoActual.getDato().setEstado(false); // Desactiva el usuario
                return nodoActual.dato; // Retorna el usuario desactivado
            }
            nodoActual = nodoActual.siguiente; // Avanza al siguiente nodo
        }
        return null; // No se encontró el usuario, retorna null

    }

    public int mostrarAdministradoresEnLista() {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Administradores Registrados:\n");

        for (int i = 0; i < contador; i++) {
            mensaje.append(administradores[i]).append("\n");
        }

        // Opciones del JOptionPane
        String[] opciones = {"Desactivar Administrador", "Reactivar Administardor", "Eliminar Administrador", "Atrás"};

        // Mostramos el JOptionPane con las opciones
        int seleccion = JOptionPane.showOptionDialog(null, mensaje.toString(), "Administardores Registrados",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

        return seleccion;
    }

}
