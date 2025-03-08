package com.proyecto.Foro;

import com.proyecto.Nodos.NodoArbol;
import com.proyecto.Nodos.NodoRespuesta;
import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import com.proyecto.Otros.SeguridadAdminG;

public class Foro implements Serializable {

    private static final long serialVersionUID = 1L;

    public boolean crearTema(String titulo, String autor) {
        // Verificamos si ya existe un tema con el mismo título
        for (Tema tema : temas) {
            // Verificamos si el título del tema es null antes de compararlo
            if (tema.getTitulo() != null && tema.getTitulo().equalsIgnoreCase(titulo)) {

                return false; // Salimos del método sin crear el tema
            }
        }

        // Si no existe tema con el mismo título, creamos el nuevo tema
        temas.add(new Tema(titulo, autor));
        guardarTemas();
        return true;
    }

    public void aMensaje(int id, String autor, String contenido) {
        for (Tema tema : temas) {
            if (tema.getId() == id) {
                tema.addMensaje(new Mensaje(autor, contenido));
                guardarTemas();
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Tema no encontrado: " + id);
    }

    public void guardarTemas() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data/temas.ser"))) {
            out.writeObject(temas);
            out.writeInt(Tema.siguienteId);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para reorganizar los IDs de los temas restantes
    private void reorganizarIds() {
        // Reasignar los IDs de los temas restantes
        for (int i = 0; i < temas.size(); i++) {
            temas.get(i).setId(i + 1); // Asignar IDs consecutivos, empezando desde 1
        }

        // Actualizar el siguiente ID
        if (!temas.isEmpty()) {
            Tema.siguienteId = temas.get(temas.size() - 1).getId() + 1; // El siguiente ID será el último ID + 1
        } else {
            Tema.siguienteId = 1; // Si la lista está vacía, el siguiente ID será 1
        }
    }

    public boolean deleteTema(int idTema, String usuario) {
        // Buscar el tema por su id
        Tema tema = buscarTemaPorId(idTema);

        if (tema != null) {
            // Verificar si el usuario actual tiene permiso para eliminar el tema
            if (tema.getAutor().equals(usuario) || usuario.equals(SeguridadAdminG
                    .getADMIN_GENERAL())) {
                // Eliminar el tema de la lista
                int indiceTema = temas.indexOf(tema); // Obtener el índice del tema a eliminar
                temas.remove(tema); // Eliminar el tema de la lista

                // Reorganizar la numeración de los temas y ajustar los IDs
                reorganizarIds(); // Este método reorganiza los IDs de todos los temas restantes

                JOptionPane.showMessageDialog(null, "Tema eliminado con éxito", "Eliminar Tema",
                        JOptionPane.INFORMATION_MESSAGE);

                guardarTemas(); // Guardar la lista actualizada
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No puedes eliminar temas de otros usuarios", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Tema no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean eliminarRespuesta(int idTema, int numMensaje, int numRespuesta, String usuarioActual) {
        Tema tema = buscarTemaPorId(idTema);

        if (tema != null) {
            Mensaje mensaje = tema.buscarMensajePorPosicion(numMensaje);

            if (mensaje != null) {
                NodoRespuesta cabeza = mensaje.getRespuestas();
                if (cabeza == null) {
                    JOptionPane.showMessageDialog(null, "Este mensaje no tiene respuestas.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

                // Verificar que la respuesta solicitada sea válida
                int contador = 1;
                NodoRespuesta actual = cabeza;
                NodoRespuesta previo = null;

                while (actual != null && contador < numRespuesta) {
                    previo = actual;
                    actual = actual.getSiguiente();
                    contador++;
                }

                if (actual == null) {
                    JOptionPane.showMessageDialog(null, "Número de respuesta no válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

                Mensaje respuesta = actual.getRespuesta();

                // Verificar permisos para eliminar
                if (!respuesta.getAutor().equals(usuarioActual) && !usuarioActual.equals(SeguridadAdminG.getADMIN_GENERAL())) {
                    JOptionPane.showMessageDialog(null, "No puedes eliminar comentarios de otros usuarios.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

                // Eliminar nodo de la lista enlazada
                if (previo == null) {
                    // Si es la primera respuesta
                    mensaje.setCabeza(actual.getSiguiente());
                } else {
                    // Si está en medio o al final
                    previo.setSiguiente(actual.getSiguiente());
                }

                JOptionPane.showMessageDialog(null, "Comentario eliminado con éxito.", "Eliminar Respuesta", JOptionPane.INFORMATION_MESSAGE);
                guardarTemas();
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Número de mensaje no válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Tema no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private Tema buscarTemaPorId(int idTema) {
        for (Tema tema : temas) {
            if (tema.getId() == idTema) {
                return tema;
            }
        }
        return null;
    }

    public void cargarTemas() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("data/temas.ser"))) {
            temas = (ArrayList<Tema>) in.readObject();
            Tema.siguienteId = in.readInt();
        } catch (IOException | ClassNotFoundException e) {
            temas = new ArrayList<>(); // Si no se encuentra el archivo, iniciar con una lista vacía
            Tema.siguienteId = 1;
        }
    }

    public boolean eliminarMensaje(int idTema, int numMensaje, String usuarioActual) {
        Tema tema = buscarTemaPorId(idTema);

        if (tema == null) {
            JOptionPane.showMessageDialog(null, "Tema no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Mensaje mensajeAEliminar = tema.buscarMensajePorPosicion(numMensaje);

        if (mensajeAEliminar == null) {
            JOptionPane.showMessageDialog(null, "Número de mensaje inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Verificar si el usuario tiene permiso para eliminar el mensaje
        if (!mensajeAEliminar.getAutor().equals(usuarioActual) && !usuarioActual.equals(SeguridadAdminG.getADMIN_GENERAL())) {
            JOptionPane.showMessageDialog(null, "No puedes eliminar mensajes de otros usuarios", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Eliminar el mensaje del árbol
        tema.raiz = tema.eliminarMensajeEnArbol(tema.raiz, mensajeAEliminar.getId());

        JOptionPane.showMessageDialog(null, "Mensaje eliminado con éxito", "Eliminar Mensaje", JOptionPane.INFORMATION_MESSAGE);
        guardarTemas();
        return true;
    }

    private void mostrarMensajesEnPaginasF(String resultados, String titulo) {
        // Aquí se utiliza un JTextPane para mostrar los resultados con formato HTML
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText("<html>" + resultados + "</html>");
        textPane.setEditable(false);

        // Crear un JScrollPane y ajustar su tamaño preferido
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setPreferredSize(new Dimension(700, 500)); // Ajusta el tamaño según sea necesario

        JOptionPane.showMessageDialog(null, scrollPane, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean responderMensaje(int idTema, int idMensaje, String autor, String contenido) {
        for (Tema tema : temas) {
            if (tema.getId() == idTema) {
                // Buscar el mensaje en el árbol binario
                Mensaje mensajeOriginal = tema.buscarMensajeEnArbol(idMensaje);

                if (mensajeOriginal != null) {
                    Mensaje respuesta = new Mensaje(autor, contenido);
                    mensajeOriginal.addRespuesta(respuesta);
                    guardarTemas();
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Mensaje no encontrado.");
                }
                return false;
            }
        }
        JOptionPane.showMessageDialog(null, "Tema no encontrado: " + idTema);
        return false;
    }

    public void buscarPorPalabraClave(String palabraClave) {
        if (palabraClave == null || palabraClave.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una palabra clave.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String palabraClaveLower = palabraClave.toLowerCase();
        StringBuilder resultados = new StringBuilder();
        boolean[] encontrado = {false}; // Para modificar dentro del método recursivo

        for (Tema tema : temas) {
            // Buscar en el título del tema
            if (contienePalabraClave(tema.getTitulo(), palabraClaveLower)) {
                resultados.append("<b>Tema: </b>").append(resaltarPalabraClave(tema.getTitulo(), palabraClave)).append("<br><br>");
                encontrado[0] = true;
            }

            // Buscar en el árbol binario de mensajes
            buscarMensajesEnArbol(tema.getRaiz(), palabraClaveLower, palabraClave, resultados, tema.getTitulo(), encontrado);
        }

        // Mostrar los resultados
        if (encontrado[0]) {
            JOptionPane.showMessageDialog(null, "<html>" + resultados.toString() + "</html>", "Resultados de búsqueda", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron coincidencias.", "Resultados", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private boolean contienePalabraClave(String texto, String palabraClaveLower) {
        return texto != null && texto.toLowerCase().contains(palabraClaveLower);
    }

    private String resaltarPalabraClave(String texto, String palabraClave) {
        String palabraClaveHtml = "<b><font color='red'>" + palabraClave + "</font></b>";
        return texto.replaceAll("(?i)" + palabraClave, palabraClaveHtml);
    }

    private void buscarMensajesEnArbol(NodoArbol nodo, String palabraClaveLower, String palabraClave, StringBuilder resultados, String titulo, boolean[] encontrado) {
        if (nodo == null) {
            return;
        }

        // Recorrer subárbol izquierdo
        buscarMensajesEnArbol(nodo.izquierda, palabraClaveLower, palabraClave, resultados, titulo, encontrado);

        // Buscar en el mensaje actual
        if (contienePalabraClave(nodo.mensaje.getContenido(), palabraClaveLower)) {
            resultados.append("<b>Tema: </b>").append(titulo).append("<br>")
                    .append(resaltarPalabraClave(nodo.mensaje.getContenido(), palabraClave))
                    .append("<br><br>");
            encontrado[0] = true;
        }

        // Buscar en respuestas del mensaje (si hay una lista enlazada de respuestas)
        NodoRespuesta actual = nodo.mensaje.getRespuestas();
        while (actual != null) {
            if (contienePalabraClave(actual.getRespuesta().getContenido(), palabraClaveLower)) {
                resultados.append("<b>Tema: </b>").append(titulo).append("<br>")
                        .append("<b>Comentario: </b>").append(resaltarPalabraClave(actual.getRespuesta().getContenido(), palabraClave))
                        .append("<br><br>");
                encontrado[0] = true;
            }
            actual = actual.getSiguiente();
        }

        // Recorrer subárbol derecho
        buscarMensajesEnArbol(nodo.derecha, palabraClaveLower, palabraClave, resultados, titulo, encontrado);
    }
    private List<Tema> temas;

    public Foro() {
        this.temas = new ArrayList<>();
        cargarTemas(); // Cargar los temas al inicializar
    }

    public List<Tema> getTemas() {
        return temas;
    }

    public void mostrarForo() {
        for (Tema tema : temas) {
            System.out.println(tema);
        }
    }

}
