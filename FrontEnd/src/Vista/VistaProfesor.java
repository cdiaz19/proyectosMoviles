/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import Control.ControlProfesores;
import LogicaNegocio.ModelProfesor;
import LogicaNegocio.TableProfesor;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Kevin
 */
public class VistaProfesor extends javax.swing.JFrame implements Observer {

    /**
     * Creates new form Vista
     */
    public VistaProfesor() {
        initComponents();
        btnCancelar.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        setVisible(true);
    }

    public void setController(ControlProfesores controller) {
        this.controller = controller;
    }

    public void setModel(ModelProfesor model) {
        this.model = model;
        model.addObserver(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CedulaField = new javax.swing.JTextField();
        labelCedula = new javax.swing.JLabel();
        botonCedula = new javax.swing.JButton();
        panelTabla = new java.awt.Panel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProfesor = new javax.swing.JTable();
        panelInsertaProfesor = new java.awt.Panel();
        insertaId = new java.awt.Label();
        insertaCedula = new java.awt.Label();
        insertaNombre = new java.awt.Label();
        insertarId = new java.awt.TextField();
        insertarCedula = new java.awt.TextField();
        insertarNombre = new java.awt.TextField();
        btnAgregar = new javax.swing.JButton();
        insertaEmail = new javax.swing.JLabel();
        insertarEmail = new javax.swing.JTextField();
        insertaContrasena = new javax.swing.JLabel();
        insertarContrasena = new javax.swing.JTextField();
        insertaTelefono = new javax.swing.JLabel();
        insertarTelefono = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Profesores");
        setBackground(new java.awt.Color(0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        CedulaField.setToolTipText("Nombre");
        CedulaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CedulaFieldActionPerformed(evt);
            }
        });

        labelCedula.setText("Cedula");

        botonCedula.setText("Buscar");
        botonCedula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonCedulaMouseClicked(evt);
            }
        });
        botonCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCedulaActionPerformed(evt);
            }
        });

        panelTabla.setBackground(new java.awt.Color(153, 153, 153));
        panelTabla.setForeground(new java.awt.Color(255, 153, 51));

        tablaProfesor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Cedula", "Nombre", "Email", "Contasena", "Telefono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaProfesor.setToolTipText("");
        tablaProfesor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProfesorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProfesor);

        javax.swing.GroupLayout panelTablaLayout = new javax.swing.GroupLayout(panelTabla);
        panelTabla.setLayout(panelTablaLayout);
        panelTablaLayout.setHorizontalGroup(
            panelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablaLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 42, Short.MAX_VALUE))
        );
        panelTablaLayout.setVerticalGroup(
            panelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        panelInsertaProfesor.setBackground(new java.awt.Color(204, 204, 204));

        insertaId.setText("id");

        insertaCedula.setText("cedula");

        insertaNombre.setText("Nombre");

        insertarId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarIdActionPerformed(evt);
            }
        });

        insertarCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarCedulaActionPerformed(evt);
            }
        });

        insertarNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarNombreActionPerformed(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        insertaEmail.setText("Email");

        insertaContrasena.setText("Contrasena");

        insertarContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarContrasenaActionPerformed(evt);
            }
        });

        insertaTelefono.setText("Telefono");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInsertaProfesorLayout = new javax.swing.GroupLayout(panelInsertaProfesor);
        panelInsertaProfesor.setLayout(panelInsertaProfesorLayout);
        panelInsertaProfesorLayout.setHorizontalGroup(
            panelInsertaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInsertaProfesorLayout.createSequentialGroup()
                .addGroup(panelInsertaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInsertaProfesorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelInsertaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelInsertaProfesorLayout.createSequentialGroup()
                                .addComponent(insertaTelefono)
                                .addGap(36, 36, 36)
                                .addComponent(insertarTelefono))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelInsertaProfesorLayout.createSequentialGroup()
                                .addGroup(panelInsertaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(insertaContrasena, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(insertaEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(insertaId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(insertaCedula, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(insertaNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(24, 24, 24)
                                .addGroup(panelInsertaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(insertarNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                                    .addComponent(insertarId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(insertarCedula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(insertarEmail)
                                    .addComponent(insertarContrasena)))))
                    .addGroup(panelInsertaProfesorLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(btnAgregar)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        panelInsertaProfesorLayout.setVerticalGroup(
            panelInsertaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInsertaProfesorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInsertaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelInsertaProfesorLayout.createSequentialGroup()
                        .addGroup(panelInsertaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelInsertaProfesorLayout.createSequentialGroup()
                                .addGroup(panelInsertaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(insertaId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(insertarId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(insertaCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(insertarCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(insertaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(insertarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(panelInsertaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(insertaEmail)
                    .addComponent(insertarEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInsertaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(insertarContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(insertaContrasena))
                .addGap(3, 3, 3)
                .addGroup(panelInsertaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(insertaTelefono)
                    .addComponent(insertarTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(panelInsertaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnActualizar)
                    .addComponent(btnEliminar)
                    .addComponent(btnCancelar))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        insertarCedula.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelCedula)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CedulaField, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonCedula))
                            .addComponent(panelInsertaProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CedulaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCedula)
                    .addComponent(botonCedula))
                .addGap(24, 24, 24)
                .addComponent(panelTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(panelInsertaProfesor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("ventanaProductos");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CedulaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CedulaFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CedulaFieldActionPerformed

    private void insertarIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_insertarIdActionPerformed

    private void insertarNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_insertarNombreActionPerformed

    private void botonCedulaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCedulaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_botonCedulaMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            this.controller.agregar();
            this.insertarId.setText("");
            this.insertarCedula.setText("");
            this.insertarNombre.setText("");
            this.insertarEmail.setText("");
            this.insertarTelefono.setText("");
            this.insertarContrasena.setText("");
            
        } catch (GlobalException ex) {
            Logger.getLogger(VistaProfesor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(VistaProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void insertarCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_insertarCedulaActionPerformed

    private void botonCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCedulaActionPerformed
        try {
            this.controller.buscar();
            this.CedulaField.setText("");
        } catch (GlobalException ex) {
            Logger.getLogger(VistaProfesor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(VistaProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonCedulaActionPerformed

    private void insertarContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarContrasenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_insertarContrasenaActionPerformed

    private void tablaProfesorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProfesorMouseClicked
        // TODO add your handling code here:
        btnAgregar.setEnabled(false);
        btnActualizar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnEliminar.setEnabled(true);
        TableProfesor Tablemodel = (TableProfesor) tablaProfesor.getModel();

        int filaProfesorSeleccionada = tablaProfesor.getSelectedRow();
        
        this.insertarId.setText(Tablemodel.getValueAt(filaProfesorSeleccionada, 0).toString());
        this.insertarId.setEnabled(false);
        this.insertarCedula.setText(Tablemodel.getValueAt(filaProfesorSeleccionada, 1).toString());
        this.insertarCedula.setEnabled(false);
        this.insertarNombre.setText(Tablemodel.getValueAt(filaProfesorSeleccionada, 2).toString());
        this.insertarEmail.setText(Tablemodel.getValueAt(filaProfesorSeleccionada, 3).toString());
        this.insertarContrasena.setText(Tablemodel.getValueAt(filaProfesorSeleccionada, 4).toString());
        this.insertarTelefono.setText(Tablemodel.getValueAt(filaProfesorSeleccionada, 5).toString());

    }//GEN-LAST:event_tablaProfesorMouseClicked

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        btnAgregar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnEliminar.setEnabled(false);

        this.insertarId.setText("");
        this.insertarCedula.setText("");
        this.insertarNombre.setText("");
        this.insertarEmail.setText("");
        this.insertarTelefono.setText("");
        this.insertarContrasena.setText("");
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {
            this.controller.actualizar();
            this.insertarId.setText("");
            this.insertarCedula.setText("");
            this.insertarNombre.setText("");
            this.insertarEmail.setText("");
            this.insertarTelefono.setText("");
            this.insertarContrasena.setText("");
            
        } catch (GlobalException ex) {
            Logger.getLogger(VistaProfesor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(VistaProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            this.controller.eliminar();
        } catch (GlobalException ex) {
            Logger.getLogger(VistaProfesor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(VistaProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaProfesor().setVisible(true);
            }
        });
    }

    public ModelProfesor getModel() {
        return model;
    }

    public void update(Observable updatedModel, Object param) {
        CedulaField.setText(model.getFilter().getNombre());

        tablaProfesor.setModel(model.getProfesores());
        this.revalidate();
        if (!model.getMensaje().equals("")) {
            JOptionPane.showMessageDialog(this, model.getMensaje(), "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    ControlProfesores controller;
    ModelProfesor model;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField CedulaField;
    private javax.swing.JButton botonCedula;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private java.awt.Label insertaCedula;
    private javax.swing.JLabel insertaContrasena;
    private javax.swing.JLabel insertaEmail;
    private java.awt.Label insertaId;
    private java.awt.Label insertaNombre;
    private javax.swing.JLabel insertaTelefono;
    public java.awt.TextField insertarCedula;
    public javax.swing.JTextField insertarContrasena;
    public javax.swing.JTextField insertarEmail;
    public java.awt.TextField insertarId;
    public java.awt.TextField insertarNombre;
    public javax.swing.JTextField insertarTelefono;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCedula;
    private java.awt.Panel panelInsertaProfesor;
    private java.awt.Panel panelTabla;
    private javax.swing.JTable tablaProfesor;
    // End of variables declaration//GEN-END:variables
}
