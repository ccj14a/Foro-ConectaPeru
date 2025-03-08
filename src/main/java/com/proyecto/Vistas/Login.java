package com.proyecto.Vistas;

import com.proyecto.Administrador.*;
import com.proyectoUsuario.*;
import com.proyecto.Menus.*;
import com.proyecto.Otros.Formatos;
import com.proyectoUsuario.ListaEnlazadaDobleUsuarios;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JOptionPane;
import com.proyecto.Foro.Foro;
import com.proyecto.Foro.Tema;

import com.proyecto.Otros.SeguridadAdminG;

public class Login extends javax.swing.JFrame {

    ListaEnlazadaDobleUsuarios listaUsuarios = new ListaEnlazadaDobleUsuarios();
    ArregloAdministradores arregloAdministradores = new ArregloAdministradores();

    Foro foro = new Foro(); // Crear instancia de foro y cargar temas
    Usuario usuarioActual = null;
    Administrador adminActualNormal = null;

    public Login() {
        initComponents();
        listaUsuarios.loadUser(); // Cargar usuarios al inicio
        arregloAdministradores.cargaAdmin();// PARA CARGAR LOS ADMINISTARDORES ACTUALES REGISTRADOS
        this.setSize(new Dimension(1210, 731));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        campo_user = new javax.swing.JTextField();
        campo_contra = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        btn_crearCuenta = new javax.swing.JButton();
        btn_inciarSesion = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        btn_volver = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(36, 48, 60,150));

        jLabel1.setFont(new java.awt.Font("Arial Narrow", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Bienvenido");

        jLabel3.setFont(new java.awt.Font("Arial Narrow", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Usuario");

        jLabel6.setFont(new java.awt.Font("Arial Narrow", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Contraseña");

        campo_user.setBackground(new java.awt.Color(0, 0, 0));
        campo_user.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        campo_user.setForeground(new java.awt.Color(255, 255, 255));
        campo_user.setCaretColor(new java.awt.Color(255, 255, 255));

        campo_contra.setBackground(new java.awt.Color(0, 0, 0));
        campo_contra.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campo_contra.setForeground(new java.awt.Color(255, 255, 255));
        campo_contra.setCaretColor(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("No tienes una cuenta?");

        btn_crearCuenta.setBackground(new java.awt.Color(255, 153, 0));
        btn_crearCuenta.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        btn_crearCuenta.setForeground(new java.awt.Color(255, 255, 255));
        btn_crearCuenta.setText("Crea una");
        btn_crearCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_crearCuentaActionPerformed(evt);
            }
        });

        btn_inciarSesion.setBackground(new java.awt.Color(255, 153, 0));
        btn_inciarSesion.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        btn_inciarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btn_inciarSesion.setText("Iniciar Sesión");
        btn_inciarSesion.setAlignmentX(0.5F);
        btn_inciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inciarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_inciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_crearCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(150, 150, 150))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(campo_user, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                                .addComponent(campo_contra))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(140, 140, 140))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campo_user, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campo_contra, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btn_inciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(btn_crearCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(157, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 720));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protest.jpg"))); // NOI18N
        jLabel8.setText("jLabel8");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 220));

        btn_volver.setBackground(new java.awt.Color(153, 102, 255));
        btn_volver.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        btn_volver.setForeground(new java.awt.Color(255, 255, 255));
        btn_volver.setText("Volver al Inicio");
        btn_volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_volverActionPerformed(evt);
            }
        });
        jPanel4.add(btn_volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 0, 120, 50));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dina6.jpg"))); // NOI18N
        jLabel5.setText("jLabel5");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-60, 0, 1270, 720));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1210, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_crearCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_crearCuentaActionPerformed
        Registro reg = new Registro();
        reg.setVisible(true);
        reg.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_btn_crearCuentaActionPerformed

    private void btn_inciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inciarSesionActionPerformed
        String user = campo_user.getText();
        char[] passwordArray = campo_contra.getPassword();
        String pass = new String(passwordArray);
        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Rellene todos los campos");
            return;
        }
        usuarioActual = listaUsuarios.loginUsuario(user, pass);
        if (usuarioActual != null) {

            JOptionPane.showMessageDialog(null,
                    "Sesión Iniciada");
            this.dispose();
            
            int opUsuario = 0;
            MenuUsuario menu = new MenuUsuario(usuarioActual);
            menu.setVisible(true);
            menu.setLocationRelativeTo(null);
            this.dispose();
 
            return;

        }

        int opAdmin = 0;

        adminActualNormal = arregloAdministradores
                .loginAE(user, pass);

        if (arregloAdministradores.loginAG(user, pass) || adminActualNormal != null) {

            JOptionPane.showMessageDialog(null, "Credenciales correctas. Sesion iniciada", "Mensaje",
                    JOptionPane.INFORMATION_MESSAGE);
            this.dispose();

        } else {

            JOptionPane.showMessageDialog(null, "Credenciales incorrectas.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean isAdminGeneral = arregloAdministradores.loginAG(user, pass);
        do {
            opAdmin = MenuAdministracion.menuAdminG(isAdminGeneral);

            switch (opAdmin) {
                case 1:
                    if (listaUsuarios.isVacia()) {
                        JOptionPane.showMessageDialog(null, "No hay usuarios registrados, hasta el momento",
                                "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                        break;
                    }
                    int opOpcion;
                    do {
                        opOpcion = listaUsuarios.mostrarUsuariosEnLista();
                        Usuario usuario = null;
                        switch (opOpcion) {

                            case 0: // Desactivar Usuario
                                String userx = JOptionPane
                                        .showInputDialog("Ingrese el nombre del usuario:");
                                usuario = arregloAdministradores.desactivarUser(userx, listaUsuarios.getCabeza()); // Supone que
                                // devuelve un
                                // objeto Usuario
                                // o null
                                if (usuario != null) {
                                    JOptionPane.showMessageDialog(null,
                                            "Usuario: " + userx + " Desactivado");
                                    listaUsuarios.saveUser();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Usuario no encontrado",
                                            "INFORMACION", JOptionPane.INFORMATION_MESSAGE, null);
                                }
                                break;
                            case 1: // Reactivar Usuario
                                String userToReactivate = JOptionPane
                                        .showInputDialog("Ingrese el nombre del usuario:");
                                usuario = arregloAdministradores.activarUser(userToReactivate, listaUsuarios.getCabeza());
                                if (usuario != null) {
                                    JOptionPane.showMessageDialog(null,
                                            "Usuario: " + userToReactivate + " Reactivado");
                                    listaUsuarios.saveUser();

                                } else {
                                    JOptionPane.showMessageDialog(null, "Usuario no encontrado",
                                            "INFORMACION", JOptionPane.INFORMATION_MESSAGE, null);
                                }
                                break;
                            case 2: // Eliminar Usuario
                                String userE = JOptionPane
                                        .showInputDialog("Ingrese el nombre del usuario:");
                                usuario = arregloAdministradores.eliminarUsuario(userE, listaUsuarios);
                                if (usuario != null) {
                                    JOptionPane.showMessageDialog(null, "Usuario: " + userE + " Eliminado");
                                    listaUsuarios
                                            .saveUser();

                                } else {
                                    JOptionPane.showMessageDialog(null, "Usuario no encontrado",
                                            "INFORMACION", JOptionPane.INFORMATION_MESSAGE, null);
                                }
                                break;
                        }
                        break;

                    } while (opOpcion != 3);
                    break;

                case 2:
                    if (isAdminGeneral && arregloAdministradores.isVacio()) {
                        JOptionPane.showMessageDialog(null, "No hay administradores agregados");
                    } else if (isAdminGeneral) {
                        int opcion = arregloAdministradores.mostrarAdministradoresEnLista();
                    } else {
                        JOptionPane.showMessageDialog(null, adminActualNormal);
                    }

                    break;
                case 3:
                    if (isAdminGeneral) {
                        String userA = JOptionPane.showInputDialog("Usuario:");
                        arregloAdministradores
                                .agregarAdministrador(userA);
                    } else {
                        JOptionPane.showMessageDialog(null, "Por ahora esta funcionalidad solo está disponible para el Administrador General");
                    }

                    break;

                case 4:

                    StringBuilder mensajesMensaje = new StringBuilder();
                    for (Tema tema : foro.getTemas()) {
                        mensajesMensaje.append(tema);
                        mensajesMensaje.append("-------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                    }
                    
                    Formatos.mostrarMensajesEnPaginas(mensajesMensaje.toString(), "Mensajes");
                    break;

                case 5:
                    String inputIdB = JOptionPane.showInputDialog(null, "ID del tema:");
                    if (inputIdB == null || inputIdB.isEmpty()) {
                        break; // Salir del caso sin realizar ninguna acción
                    }
                    int idB = Integer.parseInt(inputIdB);
                    foro.deleteTema(idB, SeguridadAdminG.getADMIN_GENERAL());
                    break;

                case 6:
                    String inputIdBu = JOptionPane.showInputDialog(null, "ID del tema:");
                    if (inputIdBu == null || inputIdBu.isEmpty()) {
                        break;
                    }
                    int idBu = Integer.parseInt(inputIdBu);

                    String inputNumE = JOptionPane.showInputDialog(null, "ID del mensaje:");
                    if (inputNumE == null || inputNumE.isEmpty()) {
                        break;
                    }
                    int numE = Integer.parseInt(inputNumE);

                    foro.eliminarMensaje(idBu, numE, SeguridadAdminG.getADMIN_GENERAL());
                    break;

                case 7:
                    String inputIdBus = JOptionPane.showInputDialog(null, "ID del tema:");
                    if (inputIdBus == null || inputIdBus.isEmpty()) {
                        break;
                    }
                    int idBus = Integer.parseInt(inputIdBus);

                    String inputNumMen = JOptionPane.showInputDialog(null, "ID del mensaje:");
                    if (inputNumMen == null || inputNumMen.isEmpty()) {
                        break;
                    }
                    int numMen = Integer.parseInt(inputNumMen);

                    String inputNumR = JOptionPane.showInputDialog(null, "ID del comentario:");
                    if (inputNumR == null || inputNumR.isEmpty()) {
                        break;
                    }
                    int numR = Integer.parseInt(inputNumR);

                    /*foro.eliminarRespuesta(idBus, numMen, numR, Administrador.getADMIN_GENERAL());*/
                    break;

                case 8:

                    JOptionPane.showMessageDialog(null, "Sesion cerrada con éxito");
                    break;
            }

        } while (opAdmin != 8);
        Home hom = new Home();
        hom.setVisible(true);
        hom.setLocationRelativeTo(null);
    }//GEN-LAST:event_btn_inciarSesionActionPerformed

    private void btn_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volverActionPerformed
        this.dispose();
        Home hom = new Home();
        hom.setVisible(true);
        hom.setLocationRelativeTo(null);
    }//GEN-LAST:event_btn_volverActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_crearCuenta;
    private javax.swing.JButton btn_inciarSesion;
    private javax.swing.JButton btn_volver;
    private javax.swing.JPasswordField campo_contra;
    private javax.swing.JTextField campo_user;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    // End of variables declaration//GEN-END:variables
}
