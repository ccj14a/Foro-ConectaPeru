
package com.proyecto.Clases;

import java.awt.Dimension;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Foro implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Tema> temas;

    public Foro() {
        this.temas = new ArrayList<>();
        cargarTemas(); // Cargar los temas al inicializar
    }

    public boolean crearTema(String titulo, String autor) {
        // Verificamos si ya existe un tema con el mismo título
        for (Tema tema : temas) {
            // Verificamos si el título del tema es null antes de compararlo
            if (tema.getTitulo() != null && tema.getTitulo().equalsIgnoreCase(titulo)) {
                JOptionPane.showMessageDialog(null, "El tema con el título \"" + titulo + "\" ya existe.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return false; // Salimos del método sin crear el tema
            }
        }

        // Si no existe tema con el mismo título, creamos el nuevo tema
        temas.add(new Tema(titulo, autor));
        guardarTemas();
        JOptionPane.showMessageDialog(null, "Tema creado exitosamente.");
        return true;
    }

    public List<Tema> getTemas() {
        return temas;
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

    public void responderMensaje(int idTema, int numMensaje, String autor, String contenido) {
        for (Tema tema : temas) {
            if (tema.getId() == idTema) {
                if (numMensaje > 0 && numMensaje <= tema.getMensajes().size()) {
                    Mensaje mensajeOriginal = tema.getMensajes().get(numMensaje - 1);
                    mensajeOriginal.addRespuesta(new Mensaje(autor, contenido));
                    guardarTemas();
                } else {
                    JOptionPane.showMessageDialog(null, "Número de mensaje no válido.");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Tema no encontrado: " + idTema);
    }

    public void mostrarForo() {
        for (Tema tema : temas) {
            System.out.println(tema);

        }
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
            if (tema.getAutor().equals(usuario)) {
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
            // Verificar si el mensaje especificado existe
            if (numMensaje > 0 && numMensaje <= tema.getMensajes().size()) {
                Mensaje mensaje = tema.getMensajes().get(numMensaje - 1); // Restamos 1 porque el índice es base 0

                // Verificar si la respuesta especificada existe
                if (numRespuesta > 0 && numRespuesta <= mensaje.getRespuestas().size()) {
                    Mensaje respuesta = mensaje.getRespuestas().get(numRespuesta - 1); // Restamos 1 porque el índice es
                                                                                       // base 0

                    // Verificar si el usuario actual es el autor de la respuesta
                    if (respuesta.getAutor().equals(usuarioActual)) {
                        // Eliminar la respuesta
                        mensaje.getRespuestas().remove(numRespuesta - 1); // Eliminar la respuesta especificada

                        // Actualizar los índices de las respuestas (esto sucede automáticamente porque
                        // Java maneja la reindexación)
                        JOptionPane.showMessageDialog(null, "Comentario eliminado con éxito", "Eliminar Respuesta",
                                JOptionPane.INFORMATION_MESSAGE);

                        guardarTemas(); // Guardar los cambios realizados
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(null, "No puedes eliminar comenatarios de otros usuarios",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Número de respuesta no válido", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Número de mensaje no válido", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Tema no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
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

    public void buscarPorPalabraClave(String palabraClave) {

        StringBuilder resultados = new StringBuilder();
        boolean encontrado = false;

        // Asegúrate de que la palabra clave no sea nula
        if (palabraClave == null || palabraClave.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una palabra clave.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String palabraClaveLower = palabraClave.toLowerCase();
        String palabraClaveHtml = "<b><font color='red'>" + palabraClave + "</font></b>";
        for (Tema tema : temas) {
            // Verificar si la palabra clave está en el título del tema
            if (tema.getTitulo().toLowerCase().contains(palabraClaveLower)) {
                resultados.append("Tema: ").append(tema.getTitulo()).append("<br>");
                resultados
                        .append("<b><font color='red'>"
                                + tema.getTitulo().replaceAll("(?i)" + palabraClave, palabraClaveHtml) + "</font></b>")
                        .append("<br>");
                encontrado = true;
            }

            for (Mensaje mensaje : tema.getMensajes()) {
                String mensajeContenido = mensaje.getContenido();
                if (mensajeContenido != null && mensajeContenido.toLowerCase().contains(palabraClaveLower)) {
                    resultados.append("Tema: ").append(tema.getTitulo()).append("<br>")
                            .append(resaltarPalabraClave(mensajeContenido, palabraClave)).append("<br><br>");
                    encontrado = true;
                }
                for (Mensaje respuesta : mensaje.getRespuestas()) {
                    String respuestaContenido = respuesta.getContenido();
                    if (respuestaContenido != null && respuestaContenido.toLowerCase().contains(palabraClaveLower)) {
                        resultados.append("Tema: ").append(tema.getTitulo()).append("<br>")
                                .append("Comentarios: ").append(resaltarPalabraClave(respuestaContenido, palabraClave))
                                .append("<br><br>");
                        encontrado = true;
                    }
                }
            }
        }

        if (encontrado) {
            mostrarMensajesEnPaginasF(resultados.toString(), "Resultados de la búsqueda");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron resultados para la palabra clave: " + palabraClave,
                    "Resultados de la búsqueda", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private String resaltarPalabraClave(String texto, String palabraClave) {
        return texto.replaceAll("(?i)" + palabraClave, "<b><font color='red'>" + palabraClave + "</font></b>");
    }

    private void mostrarMensajesEnPaginasF(String resultados, String titulo) {
        // Aquí se utiliza un JTextPane para mostrar los resultados con formato HTML
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText("<html>" + resultados + "</html>");
        textPane.setEditable(false);

        // Crear un JScrollPane y ajustar su tamaño preferido
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setPreferredSize(new Dimension(500, 400)); // Ajusta el tamaño según sea necesario

        JOptionPane.showMessageDialog(null, scrollPane, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean eliminarMensaje(int idTema, int numMensaje, String usuarioActual) {
        Tema tema = buscarTemaPorId(idTema);

        if (tema != null) {
            // Verificar si el mensaje a eliminar es el primero en la lista
            if (numMensaje == 1) {
                Mensaje mensajePrincipal = tema.getMensajes().get(0);
                if (mensajePrincipal.getAutor().equals(usuarioActual)) {
                    tema.getMensajes().remove(0); // Elimina el mensaje principal
                    JOptionPane.showMessageDialog(null, "Mensaje eliminado con éxito", "Eliminar Mensaje",
                            JOptionPane.INFORMATION_MESSAGE);
                    guardarTemas();
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "No puedes eliminar mensajes de otros usaurios", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }

            // Verificar si el mensaje a eliminar es uno de los mensajes posteriores
            if (numMensaje > 1 && numMensaje <= tema.getMensajes().size()) {
                Mensaje mensaje = tema.getMensajes().get(numMensaje - 1); // Restamos 1 porque la lista está indexada
                                                                          // desde 0
                if (mensaje.getAutor().equals(usuarioActual)) {
                    tema.getMensajes().remove(numMensaje - 1); // Eliminar el mensaje especificado
                    JOptionPane.showMessageDialog(null, "Mensaje eliminado con éxito", "Eliminar Mensaje",
                            JOptionPane.INFORMATION_MESSAGE);
                    guardarTemas();
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "No puedes eliminar mensajes de otros usuarios", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Tema no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

}
