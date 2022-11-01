package Ventanas;

import Modelado.Administracion;
import Modelado.Edificacion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ActualizarDatosAdministracion extends javax.swing.JFrame {

    public ActualizarDatosAdministracion() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        mostrarDatosConjunto();
    }

    // <editor-fold defaultstate="collapsed" desc="Funciones">  
    private void habilitarCampos() {
        txtTotalApartamentos.setEditable(true);
        txtTotalCasas.setEditable(true);
        txtValorAdministracionApartamentos.setEditable(true);
        txtValorAdministracionCasas.setEditable(true);
    }

    private void mostrarDatosConjunto() {
        txtTotalApartamentos.setEditable(false);
        txtTotalCasas.setEditable(false);
        txtValorAdministracionApartamentos.setEditable(false);
        txtValorAdministracionCasas.setEditable(false);
        ArrayList<Administracion> ListaEdificaciones;
        String fichero = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader("Administracion.json"));
            String linea;
            while ((linea = br.readLine()) != null) {
                fichero += linea;
            }
            if (!fichero.isEmpty()) {
                try {
                    BufferedReader br2 = new BufferedReader(new FileReader("Administracion.json"));
                    ListaEdificaciones = new Gson().fromJson(br2, new TypeToken<ArrayList<Administracion>>() {
                    }.getType());
                    txtTotalApartamentos.setText(String.valueOf(ListaEdificaciones.get(0).getNumeroApartamentos()));
                    txtTotalCasas.setText(String.valueOf(ListaEdificaciones.get(0).getNumeroCasas()));
                    txtValorAdministracionApartamentos.setText(String.valueOf(ListaEdificaciones.get(0).getPrecioAdministracionApartamentos()));
                    txtValorAdministracionCasas.setText(String.valueOf(ListaEdificaciones.get(0).getPrecioAdministracionCasas()));
                    br2.close();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error Fichero no encontrado", 0);

                }

            }
            br.close();

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error Fichero no encontrado", 0);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", 0);
        }
    }

    private void registrarDatosAdministracion() {
        Gson documento = new Gson();
        String fichero = "";
        ArrayList<Administracion> ListaDatosAdministracion = new ArrayList<>();

        int numeroDeCasas = Integer.parseInt(txtTotalCasas.getText());
        int numeroDeApartamentos = Integer.parseInt(txtTotalApartamentos.getText());
        double precioAdministracionApartamentos = Double.parseDouble(txtValorAdministracionApartamentos.getText());
        double precioAdministracionCasas = Double.parseDouble(txtValorAdministracionCasas.getText());

        try {
            BufferedReader br = new BufferedReader(new FileReader("Administracion.json"));
            String linea;
            while ((linea = br.readLine()) != null) {
                fichero += linea;
            }
            if (fichero.isEmpty()) {

                Administracion nuevaEdificacion = new Administracion(numeroDeApartamentos, numeroDeCasas, precioAdministracionApartamentos, precioAdministracionCasas);

                ListaDatosAdministracion.add(nuevaEdificacion);
                String datosAdministracion = documento.toJson(ListaDatosAdministracion);

                try (BufferedWriter bw = new BufferedWriter(new FileWriter("Administracion.json"))) {
                    bw.write(datosAdministracion);
                    JOptionPane.showMessageDialog(null, "Los datos se han guardado con exito", "Felicidades", 1);
                    Menu abrir = new Menu();
                    abrir.setVisible(true);
                    this.setVisible(false);

                } catch (IOException ex) {
                    Logger.getLogger(Edificacion.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                try {
                    BufferedReader br2 = new BufferedReader(new FileReader("Administracion.json"));
                    ListaDatosAdministracion = new Gson().fromJson(br2, new TypeToken<ArrayList<Administracion>>() {
                    }.getType());

                    Administracion nuevaEdificacion = new Administracion(numeroDeApartamentos, numeroDeCasas, precioAdministracionApartamentos, precioAdministracionCasas);
                    ListaDatosAdministracion.clear();
                    ListaDatosAdministracion.add(nuevaEdificacion);

                    String datosAdministracion = documento.toJson(ListaDatosAdministracion);

                    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Administracion.json"))) {
                        bw.write(datosAdministracion);
                        JOptionPane.showMessageDialog(null, "Los datos se han guardado con exito", "Felicidades", 1);
                        Menu abrir = new Menu();
                        abrir.setVisible(true);
                        this.setVisible(false);

                    } catch (IOException ex) {
                        Logger.getLogger(Administracion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    br2.close();

                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error Fichero no encontrado", 0);

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", 0);
                }
            }

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error Fichero no encontrado", 0);

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", 0);
        }

    }
    // </editor-fold> 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtTotalApartamentos = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTotalCasas = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtValorAdministracionApartamentos = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtValorAdministracionCasas = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        botonActualizarDatos = new javax.swing.JButton();
        botonVolver = new javax.swing.JButton();
        botonEditarDatos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Numero de apartamentos:");

        txtTotalApartamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalApartamentosActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Numero de casas:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Valor de la administracion Apartamentos:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Valor de la administracion Casas:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("ADMINISTRACION");

        botonActualizarDatos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonActualizarDatos.setText("Actualizar Datos");
        botonActualizarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarDatosActionPerformed(evt);
            }
        });

        botonVolver.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonVolver.setText("Volver");
        botonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVolverActionPerformed(evt);
            }
        });

        botonEditarDatos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonEditarDatos.setText("Editar Datos");
        botonEditarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEditarDatosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(268, 268, 268))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtValorAdministracionApartamentos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                            .addComponent(txtTotalCasas, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotalApartamentos, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtValorAdministracionCasas)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonEditarDatos)
                        .addGap(160, 160, 160)
                        .addComponent(botonActualizarDatos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                        .addComponent(botonVolver)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTotalApartamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTotalCasas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValorAdministracionApartamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValorAdministracionCasas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonActualizarDatos)
                    .addComponent(botonVolver)
                    .addComponent(botonEditarDatos))
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTotalApartamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalApartamentosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalApartamentosActionPerformed

    private void botonActualizarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarDatosActionPerformed
        registrarDatosAdministracion();
    }//GEN-LAST:event_botonActualizarDatosActionPerformed

    private void botonEditarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarDatosActionPerformed
        habilitarCampos();
    }//GEN-LAST:event_botonEditarDatosActionPerformed

    private void botonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverActionPerformed
        Menu abrir = new Menu();
        abrir.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_botonVolverActionPerformed

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
            java.util.logging.Logger.getLogger(ActualizarDatosAdministracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ActualizarDatosAdministracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ActualizarDatosAdministracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActualizarDatosAdministracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ActualizarDatosAdministracion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonActualizarDatos;
    private javax.swing.JButton botonEditarDatos;
    private javax.swing.JButton botonVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtTotalApartamentos;
    private javax.swing.JTextField txtTotalCasas;
    private javax.swing.JTextField txtValorAdministracionApartamentos;
    private javax.swing.JTextField txtValorAdministracionCasas;
    // End of variables declaration//GEN-END:variables
}
