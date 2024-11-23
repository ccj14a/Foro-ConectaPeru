/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.Clases;

import java.io.*;
import javax.swing.JOptionPane;

public class Usuario extends SuperUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private int edad;
    private Nodo cab;
    private static int id; // contador global
    private int idUnico;

    public Usuario(String nombres, String usuario, String contra, int edad) {
        super(nombres, usuario, contra);
        setEstado(true);
        this.edad = edad;
        idUnico = id++;

    }

    public Usuario() {
        idUnico = id++;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Nodo getCab() {
        return cab;
    }

    public void setCab(Nodo cab) {
        this.cab = cab;
    }

    @Override
    public String toString() {
        return "Usuario{"
                + "id=" + idUnico + ", "
                + super.toString() + ", "
                + "edad=" + edad
                + '}';
    }

    // Medtodos para aregar Usuario
    public void addUser(Usuario user) {

        Nodo nodoActual = new Nodo(user);
        if (cab == null) {
            cab = nodoActual;

        } else {
            Nodo nodo = cab;
            while (nodo.getNext() != null) {
                nodo = nodo.getNext();
            }
            nodo.setNext(nodoActual);
            saveUser(); // la prsistencia

        }

    }

    public Usuario findUser(String user) {
        Nodo actual = cab;
        while (actual != null) {
            if (actual.getUser().getUsuario().equals(user)) {
                return actual.getUser();
            }
            actual = actual.getNext();
        }
        return null;

    }

    public boolean isListaVacia() {
        return cab == null;
    }

    public int mostrarUsuariosEnLista() {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Usuarios Registrados:\n");

        // Recorremos la lista enlazada para obtener todos los usuarios
        Nodo actual = cab;
        while (actual != null) {
            mensaje.append(actual.getUser()).append("\n");
            actual = actual.getNext();
        }

        // Opciones del JOptionPane
        String[] opciones = { "Desactivar Usuario", "Reactivar Usuario", "Eliminar Usuario", "Atrás" };

        // Mostramos el JOptionPane con las opciones
        int seleccion = JOptionPane.showOptionDialog(null, mensaje.toString(), "Usuarios Registrados",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

        return seleccion;
    }

    public void modificarAtributosUsuario(Usuario usuarioActual) {
        String[] opciones = { "Nombre", "Usuario", "Contraseña", "Edad", "Atrás" };
        int opcionSeleccionada;

        do {
            opcionSeleccionada = JOptionPane.showOptionDialog(
                    null,
                    "                                                                                        Cuenta: "
                            + usuarioActual
                                    .getEstadoComoTexto()
                            + "\n" +
                            "Seleccione el atributo que desea modificar:\nNombre: " + usuarioActual.getNombres()
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
                        if (findUser(nuevoUsuario) == null) {
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

    public Usuario login(String user, String password) {
        Nodo actual = cab;
        while (actual != null) {
            if (actual.getUser().getUsuario().equals(user.trim()) && actual.getUser().getContra().equals(password)
                    && actual.getUser().isEstado()) {
                return actual.getUser();
            }
            actual = actual.getNext();
        }

        return null;

    }

    public void saveUser() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data/usuarios.ser"))) {
            out.writeObject(this.cab); // Aquí se usa 'cab' sin 'this' porque no hay ambigüedad.
            out.writeInt(id);
        } catch (IOException e) {
            System.out.println("Error al guardar usuarios: " + e.getMessage());
        }
    }

    public void loadUser() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("data/usuarios.ser"))) {
            this.cab = (Nodo) in.readObject();
            id = in.readInt();
            System.out.println("Datos cargados exitosamente.");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de usuarios no encontrado, se creará uno nuevo al guardar.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar usuarios: " + e.getMessage());
        }
    }

}
