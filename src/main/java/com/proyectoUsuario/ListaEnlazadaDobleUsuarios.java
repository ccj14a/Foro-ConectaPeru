/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyectoUsuario;

import com.proyecto.Nodos.NodoUsuario;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.JOptionPane;

public class ListaEnlazadaDobleUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    private NodoUsuario cabeza, cola;

    public ListaEnlazadaDobleUsuarios() {
        cabeza = cola = null;
    }

    public void insertarUsuario(Usuario dato) {
        NodoUsuario nuevoNodo = new NodoUsuario(dato);
        if (cabeza == null) {
            cabeza = cola = nuevoNodo;
        } else {
            cola.siguiente = nuevoNodo;
            nuevoNodo.anterior = cola;
            cola = nuevoNodo;
        }
        saveUser();

    }

    public Usuario buscarUsuario(String nombreUsuario) {
        NodoUsuario actual = cabeza;
        while (actual != null) {
            if (actual.dato.getUsuario().equals(nombreUsuario)) {
                return actual.dato;
            }
            actual = actual.siguiente;
        }
        return null;

    }

    public NodoUsuario getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoUsuario cabeza) {
        this.cabeza = cabeza;
    }

    public NodoUsuario getCola() {
        return cola;
    }

    public void setCola(NodoUsuario cola) {
        this.cola = cola;
    }

    public String mostrarDatos() {
        NodoUsuario nuevo = cabeza;
        String m = "";
        while (nuevo != null) {
            m = m + nuevo.dato.toString() + "\n";
            nuevo = nuevo.siguiente;

        }
        return m;
    }

    public boolean cambiarEstado(String user) {
        NodoUsuario actual = cabeza;
        while (actual != null) {
            if (actual.dato.getUsuario().equals(user)) {
                actual.dato.setEstado(false);
                return true;
            }
            actual = actual.siguiente;
        }
        return false;

    }

    public boolean isVacia() {
        return cabeza == null;
    }

    public int mostrarUsuariosEnLista() {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Usuarios Registrados:\n");

        // Recorremos la lista enlazada para obtener todos los usuarios
        NodoUsuario actual = cabeza;
        while (actual != null) {
            mensaje.append(actual.dato).append("\n");
            actual = actual.siguiente;
        }

        // Opciones del JOptionPane
        String[] opciones = {"Desactivar Usuario", "Reactivar Usuario", "Eliminar Usuario", "Atrás"};

        // Mostramos el JOptionPane con las opciones
        int seleccion = JOptionPane.showOptionDialog(null, mensaje.toString(), "Usuarios Registrados",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

        return seleccion;
    }

    public void modificarAtributosUsuario(Usuario usuarioActual) {
        String[] opciones = {"Nombre", "Usuario", "Contraseña", "Edad", "Atrás"};
        int opcionSeleccionada;

        do {
            opcionSeleccionada = JOptionPane.showOptionDialog(
                    null,
                    "                                                                                        Cuenta: "
                    + usuarioActual
                            .getEstadoComoTexto()
                    + "\n"
                    + "Seleccione el atributo que desea modificar:\nNombre: " + usuarioActual.getNombres()
                    + ", Usuario: " + usuarioActual.getUsuario() + ", Contraseña: " + usuarioActual.getContra()
                    + ", Edad: " + usuarioActual.getEdad() + ", Estado " + usuarioActual.getEstadoComoTexto(),
                    "👤:  @" + usuarioActual.getUsuario(),
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            switch (opcionSeleccionada) {
                case 0: // Modificar Nombre
                    String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre:");
                    if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                        usuarioActual.setNombres(nuevoNombre);
                        saveUser();
                        JOptionPane.showMessageDialog(null, "Nombre actualizado correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Nombre no válido", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case 1: // Modificar Usuario
                    String nuevoUsuario = JOptionPane.showInputDialog("Ingrese el nuevo nombre de usuario:");

                    if (nuevoUsuario != null && !nuevoUsuario.trim().isEmpty()) {
                        // Verificar si el nuevo nombre de usuario ya existe
                        if (buscarUsuario(nuevoUsuario) == null) {
                            usuarioActual.setUsuario(nuevoUsuario);
                            saveUser();
                            JOptionPane.showMessageDialog(null, "Nombre de usuario actualizado correctamente");
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "El nombre de usuario ya está en uso. Intente con otro nombre.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nombre de usuario no válido", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case 2: // Modificar Contraseña
                    String nuevaContra = JOptionPane.showInputDialog("Ingrese la nueva contraseña:");
                    if (nuevaContra != null && !nuevaContra.trim().isEmpty()) {
                        usuarioActual.setContra(nuevaContra);
                        
                       saveUser();
                        JOptionPane.showMessageDialog(null, "Contraseña actualizada correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Contraseña no válida", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case 3:
                    String nuevaEdad = JOptionPane.showInputDialog("Ingrese la nueva edad:");
                    try {
                        int edad = Integer.parseInt(nuevaEdad);
                        if (edad > 0) {
                            usuarioActual.setEdad(edad);
                            saveUser();
                            JOptionPane.showMessageDialog(null, "Edad actualizada correctamente");
                        } else {
                            JOptionPane.showMessageDialog(null, "Edad no válida", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Debe ingresar un número válido para la edad.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 4:
                    // JOptionPane.showMessageDialog(null, "Modificación de usuario cancelada.");
                    break;

                default:
                    break;

            }
        } while (opcionSeleccionada != 4); // Repite hasta que se seleccione "Cancelar"
    }

    public void saveUser() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data/usuarios.ser"))) {
            out.writeObject(this.cabeza);

        } catch (IOException e) {
            System.out.println("Error al guardar usuarios: " + e.getMessage());
        }
    }

    public void loadUser() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("data/usuarios.ser"))) {
            this.cabeza = (NodoUsuario) in.readObject();
            NodoUsuario actual = cabeza;
            while (actual != null && actual.siguiente != null) {
                actual = actual.siguiente;
            }
            this.cola = actual;
            System.out.println("Datos cargados exitosamente.");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de usuarios no encontrado, se creará uno nuevo al guardar.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar usuarios: " + e.getMessage());
        }
    }

    public Usuario loginUsuario(String user, String password) {
        NodoUsuario actual = cabeza;
        while (actual != null) {
            if (actual.dato.getUsuario().equals(user.trim()) && actual.dato.getContra().equals(password)
                    && actual.dato.isEstado()) {
                return actual.dato;
            }
            actual = actual.siguiente;
        }

        return null;

    }

}
