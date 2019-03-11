/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import Control.ControlCursos;
import LogicaNegocio.ModelCurso;
import LogicaNegocio.TableCurso;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author cdiaz
 */

public class VistaCurso extends javax.swing.JFrame implements Observer {
    ControlCursos controller;
    ModelCurso model;

    /**
     * Creates new form Vista
     */
    public VistaCurso() {
        initComponents();
        btnCancelar.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        setVisible(true);
    }

    public void setController(ControlCursos controller) {
        this.controller = controller;
    }

    public void setModel(ModelCurso model) {
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

        IDField = new javax.swing.JTextField();
        labelID = new javax.swing.JLabel();
        botonID = new javax.swing.JButton();
        panelTabla = new java.awt.Panel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCursos = new javax.swing.JTable();
        panelInsertaProfesor = new java.awt.Panel();
        insertaId = new java.awt.Label();
        insertaLabelCodigo = new java.awt.Label();
        insertaLabelNombre = new java.awt.Label();
        insertarId = new java.awt.TextField();
        insertarCodigo = new java.awt.TextField();
        insertarNombre = new java.awt.TextField();
        insertaLabelCeditos = new javax.swing.JLabel();
        insertarCreditos = new javax.swing.JTextField();
        insertaLabelHorasSemanales = new javax.swing.JLabel();
        insertarHorasSemanales = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ciclos");
        setBackground(new java.awt.Color(0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        IDField.setToolTipText("Nombre");
        IDField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDFieldActionPerformed(evt);
            }
        });

        labelID.setText("Nombre");

        botonID.setText("Buscar");
        botonID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonIDMouseClicked(evt);
            }
        });
        botonID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonIDActionPerformed(evt);
            }
        });

        panelTabla.setBackground(new java.awt.Color(153, 153, 153));
        panelTabla.setForeground(new java.awt.Color(255, 153, 51));

        tablaCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Codigo", "Nombre", "Creditos", "Horas Semanales"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaCursos.setToolTipText("");
        tablaCursos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCursosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCursos);

        javax.swing.GroupLayout panelTablaLayout = new javax.swing.GroupLayout(panelTabla);
        panelTabla.setLayout(panelTablaLayout);
        panelTablaLayout.setHorizontalGroup(
            panelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        insertaLabelCodigo.setText("Codigo");

        insertaLabelNombre.setText("Nombre");

        insertarId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarIdActionPerformed(evt);
            }
        });

        insertarCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarCodigoActionPerformed(evt);
            }
        });

        insertarNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarNombreActionPerformed(evt);
            }
        });

        insertaLabelCeditos.setText("Creditos");

        insertaLabelHorasSemanales.setText("Horas Semanales");

        insertarHorasSemanales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarHorasSemanalesActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
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
                            .addComponent(insertaLabelHorasSemanales, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(insertaLabelCeditos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(insertaId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(insertaLabelCodigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(insertaLabelNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(24, 24, 24)
                        .addGroup(panelInsertaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(insertarNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(insertarId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(insertarCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(insertarCreditos, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                            .addComponent(insertarHorasSemanales)))
                    .addGroup(panelInsertaProfesorLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)))
                .addContainerGap(8, Short.MAX_VALUE))
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
                                .addComponent(insertaLabelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(insertarCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(insertaLabelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(insertarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(panelInsertaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(insertarCreditos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(insertaLabelCeditos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInsertaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(insertarHorasSemanales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(insertaLabelHorasSemanales))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInsertaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizar)
                    .addComponent(btnAgregar)
                    .addComponent(btnEliminar))
                .addGap(15, 15, 15))
        );

        insertarCodigo.getAccessibleContext().setAccessibleDescription("");
        insertaLabelCeditos.getAccessibleContext().setAccessibleDescription("");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnCerrar.setText("Cancelar Ventana");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(IDField, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonID))
                    .addComponent(panelTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelInsertaProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCerrar)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnCancelar)
                                .addGap(31, 31, 31)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelID)
                    .addComponent(botonID))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(panelTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelInsertaProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelar)
                        .addGap(48, 48, 48)
                        .addComponent(btnCerrar)
                        .addGap(74, 74, 74))))
        );

        labelID.getAccessibleContext().setAccessibleName("Buscar por ID");

        getAccessibleContext().setAccessibleName("ventanaProductos");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IDFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDFieldActionPerformed

    private void insertarIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_insertarIdActionPerformed

    private void insertarNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_insertarNombreActionPerformed

    private void botonIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonIDMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_botonIDMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            this.controller.agregar();
            this.insertarId.setText("");
            this.insertarCodigo.setText("");
            this.insertarNombre.setText("");
            this.insertarCreditos.setText("");
            this.insertarHorasSemanales.setText("");
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(VistaCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void insertarCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_insertarCodigoActionPerformed

    private void botonIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonIDActionPerformed
        try {
            this.controller.buscar();
            this.IDField.setText("");
            this.insertarId.setText("");
            this.insertarCodigo.setText("");
            this.insertarNombre.setText("");
            this.insertarCreditos.setText("");
            this.insertarHorasSemanales.setText("");
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(VistaCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonIDActionPerformed

    private void insertarHorasSemanalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarHorasSemanalesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_insertarHorasSemanalesActionPerformed

    private void tablaCursosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCursosMouseClicked
        btnAgregar.setEnabled(false);
        btnActualizar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnEliminar.setEnabled(true);
        TableCurso Tablemodel = (TableCurso) tablaCursos.getModel();

        int filaCursoSeleccionada = tablaCursos.getSelectedRow();
        
        this.insertarId.setText(Tablemodel.getValueAt(filaCursoSeleccionada, 0).toString());
        this.insertarId.setEnabled(false);
        this.insertarCodigo.setText(Tablemodel.getValueAt(filaCursoSeleccionada, 1).toString());
        this.insertarNombre.setText(Tablemodel.getValueAt(filaCursoSeleccionada, 2).toString());
        this.insertarCreditos.setText(Tablemodel.getValueAt(filaCursoSeleccionada, 3).toString());
        this.insertarHorasSemanales.setText(Tablemodel.getValueAt(filaCursoSeleccionada, 4).toString());
    }//GEN-LAST:event_tablaCursosMouseClicked

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        btnAgregar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnEliminar.setEnabled(false);

        this.insertarId.setText("");
        this.insertarCodigo.setText("");
        this.insertarNombre.setText("");
        this.insertarCreditos.setText("");
        this.insertarHorasSemanales.setText("");
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {
            this.controller.actualizar();
            this.insertarId.setText("");
            this.insertarCodigo.setText("");
            this.insertarNombre.setText("");
            this.insertarCreditos.setText("");
            this.insertarHorasSemanales.setText("");     
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(VistaCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            this.controller.eliminar();
            this.insertarId.setText("");
            this.insertarCodigo.setText("");
            this.insertarNombre.setText("");
            this.insertarCreditos.setText("");
            this.insertarHorasSemanales.setText("");
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(VistaCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

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
            java.util.logging.Logger.getLogger(VistaCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new VistaCurso().setVisible(true);
        });
    }

    public ModelCurso getModel() {
        return model;
    }

    @Override
    public void update(Observable updatedModel, Object param) {
        tablaCursos.setModel((TableModel) model.getCursos());
        this.revalidate();
        if (!model.getMensaje().equals("")) {
            JOptionPane.showMessageDialog(this, model.getMensaje(), "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField IDField;
    private javax.swing.JButton botonID;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private java.awt.Label insertaId;
    private javax.swing.JLabel insertaLabelCeditos;
    private java.awt.Label insertaLabelCodigo;
    private javax.swing.JLabel insertaLabelHorasSemanales;
    private java.awt.Label insertaLabelNombre;
    public java.awt.TextField insertarCodigo;
    public javax.swing.JTextField insertarCreditos;
    public javax.swing.JTextField insertarHorasSemanales;
    public java.awt.TextField insertarId;
    public java.awt.TextField insertarNombre;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelID;
    private java.awt.Panel panelInsertaProfesor;
    private java.awt.Panel panelTabla;
    private javax.swing.JTable tablaCursos;
    // End of variables declaration//GEN-END:variables
}
