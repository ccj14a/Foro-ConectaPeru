/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.proyecto.Vistas;

import com.proyecto.Administrador.ArregloAdministradores;
import com.proyectoUsuario.ListaEnlazadaDobleUsuarios;
import com.proyectoUsuario.Usuario;
import java.awt.Dimension;
import javax.swing.JOptionPane;

/**
 *
 * @author ALE
 */
public class MenuUsuario extends javax.swing.JFrame {

    com.proyecto.Foro.Foro foro = new com.proyecto.Foro.Foro();
    Usuario usuarioActual;
    ListaEnlazadaDobleUsuarios listaUsuarios = new ListaEnlazadaDobleUsuarios();
    ArregloAdministradores arregloAdministradores = new ArregloAdministradores();

    public MenuUsuario() {
        initComponents();
        this.setSize(new Dimension(790, 520));
        listaUsuarios.loadUser();
        arregloAdministradores.cargaAdmin();
       
    }

    public MenuUsuario(Usuario usuarioActual) {
        this();
        this.usuarioActual=usuarioActual;
        user.setText(usuarioActual.getUsuario());
        foro.cargarTemas();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btn_irforo = new javax.swing.JButton();
        btn_mipefil = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        btn_editar1 = new javax.swing.JButton();
        panel_user = new javax.swing.JPanel();
        user = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jPasswordField1.setText("jPasswordField1");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 0, 0,90));

        btn_irforo.setBackground(new java.awt.Color(0, 0, 0));
        btn_irforo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_irforo.setForeground(new java.awt.Color(204, 0, 255));
        btn_irforo.setText("Ir al foro");
        btn_irforo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_irforoActionPerformed(evt);
            }
        });

        btn_mipefil.setBackground(new java.awt.Color(0, 0, 0));
        btn_mipefil.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_mipefil.setForeground(new java.awt.Color(255, 0, 153));
        btn_mipefil.setText("Mi perfil");
        btn_mipefil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mipefilActionPerformed(evt);
            }
        });

        btn_editar.setBackground(new java.awt.Color(0, 0, 0));
        btn_editar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_editar.setForeground(new java.awt.Color(0, 255, 51));
        btn_editar.setText("Editar perfil");
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        btn_editar1.setBackground(new java.awt.Color(0, 0, 0));
        btn_editar1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_editar1.setForeground(new java.awt.Color(255, 153, 0));
        btn_editar1.setText("Cerrar sesion");
        btn_editar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_editar1, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(btn_editar, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(btn_mipefil, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(btn_irforo, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(btn_irforo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_mipefil, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_editar1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 250, 280));

        panel_user.setBackground(new java.awt.Color(0, 0, 0,150));

        user.setEditable(false);
        user.setBackground(new java.awt.Color(0, 0, 0));
        user.setForeground(new java.awt.Color(255, 255, 255));
        user.setFocusable(false);

        jLabel3.setFont(new java.awt.Font("Segoe Print", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 153));
        jLabel3.setText("Usuario actual");

        javax.swing.GroupLayout panel_userLayout = new javax.swing.GroupLayout(panel_user);
        panel_user.setLayout(panel_userLayout);
        panel_userLayout.setHorizontalGroup(
            panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_userLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        panel_userLayout.setVerticalGroup(
            panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_userLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(user, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.add(panel_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 260, 60));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/castillo (1).jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_mipefilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mipefilActionPerformed
        JOptionPane.showMessageDialog(null,
                "Nombres: "
                + usuarioActual.getNombres() + "\n"
                + "Usuario: " + usuarioActual.getUsuario() + "\n" + "Contraseña: "
                + usuarioActual.getContra() + "\nEdad: " + usuarioActual.getEdad()
                + "\nEstado: " + usuarioActual.getEstadoComoTexto(),
                "👤:  @" + usuarioActual.getUsuario(),
                JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btn_mipefilActionPerformed

    private void btn_irforoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_irforoActionPerformed
        ForoVent ventana = new ForoVent(usuarioActual);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_btn_irforoActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        listaUsuarios.modificarAtributosUsuario(usuarioActual);
        listaUsuarios.saveUser();
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_editar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editar1ActionPerformed
        this.dispose();
        Home hom = new Home();
        hom.setVisible(true);
        hom.setLocationRelativeTo(null);
        
    }//GEN-LAST:event_btn_editar1ActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_editar1;
    private javax.swing.JButton btn_irforo;
    private javax.swing.JButton btn_mipefil;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPanel panel_user;
    private javax.swing.JTextField user;
    // End of variables declaration//GEN-END:variables
}
