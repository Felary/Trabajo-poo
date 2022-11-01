package Ventanas;

import Modelado.Apartamento;
import Modelado.Casa;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ListaPropietarios extends javax.swing.JFrame {

    public ListaPropietarios() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        labelTitulo.setText("LISTA PROPIETARIOS");

    }

    // <editor-fold defaultstate="collapsed" desc="Funciones"> 
    private void mostrarPorpietariosApartamentos(ArrayList<Apartamento> datosApartamento) {
        labelTitulo.setText("LISTA PROPIETARIOS DE LOS APARTAMENTOS");
        DefaultTableModel tabla = new DefaultTableModel();
        TablaPropietarios.setModel(tabla);

        tabla.addColumn("Nombre Propietario");
        tabla.addColumn("Torre");
        tabla.addColumn("# Apartamento");
        tabla.addColumn("Estado");
        tabla.addColumn("Deuda");

        String[] vectorDatosApartamentos = new String[5];

        for (int i = 0; i < datosApartamento.size(); i++) {
            if (!datosApartamento.get(i).getEstadoPago()) {
                String estado = "Moroso";
                vectorDatosApartamentos[0] = datosApartamento.get(i).getNombrePropietario().getNombre();
                vectorDatosApartamentos[1] = datosApartamento.get(i).getTorre();
                vectorDatosApartamentos[2] = datosApartamento.get(i).getIdApartamento();
                vectorDatosApartamentos[3] = estado;
                vectorDatosApartamentos[4] = String.valueOf(datosApartamento.get(i).getPrecioAdministracionApartamentos());
                tabla.addRow(vectorDatosApartamentos);
            } else {
                String estado = "Al Dia";
                vectorDatosApartamentos[0] = datosApartamento.get(i).getNombrePropietario().getNombre();
                vectorDatosApartamentos[1] = datosApartamento.get(i).getTorre();
                vectorDatosApartamentos[2] = datosApartamento.get(i).getIdApartamento();
                vectorDatosApartamentos[3] = estado;
                vectorDatosApartamentos[4] = "0";
                tabla.addRow(vectorDatosApartamentos);

            }
        }
    }

    private void mostrarPorpietariosCasas(ArrayList<Casa> datosCasa) {
        labelTitulo.setText("LISTA PROPIETARIOS DE LAS CASAS");
        DefaultTableModel tabla = new DefaultTableModel();
        TablaPropietarios.setModel(tabla);

        tabla.addColumn("Nombre Propietario");
        tabla.addColumn("# Casa");
        tabla.addColumn("Estado");
        tabla.addColumn("Deuda");

        String[] vectorDatosCasa = new String[4];

        for (int i = 0; i < datosCasa.size(); i++) {
            if (!datosCasa.get(i).getEstadoPago()) {
                String estado = "Moroso";
                vectorDatosCasa[0] = datosCasa.get(i).getNombrePropietario().getNombre();
                vectorDatosCasa[1] = datosCasa.get(i).getIdCasa();
                vectorDatosCasa[2] = estado;
                vectorDatosCasa[3] = String.valueOf(datosCasa.get(i).getPrecioAdministracionCasas());
                tabla.addRow(vectorDatosCasa);
            } else {
                String estado = "Al Dia";
                vectorDatosCasa[0] = datosCasa.get(i).getNombrePropietario().getNombre();
                vectorDatosCasa[1] = datosCasa.get(i).getIdCasa();
                vectorDatosCasa[2] = estado;
                vectorDatosCasa[3] = String.valueOf(datosCasa.get(i).getPrecioAdministracionCasas());
                tabla.addRow(vectorDatosCasa);

            }
        }
    }

    private void optenerDatosApartamento() {
        ArrayList<Apartamento> datosApartamento;
        String fichero = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader("Propietarios_Apartamentos.json"));
            String linea;
            while ((linea = br.readLine()) != null) {
                fichero += linea;
            }

            if (!fichero.isEmpty()) {
                try {
                    BufferedReader br2 = new BufferedReader(new FileReader("Propietarios_Apartamentos.json"));
                    datosApartamento = new Gson().fromJson(br2, new TypeToken<ArrayList<Apartamento>>() {
                    }.getType());
                    mostrarPorpietariosApartamentos(datosApartamento);

                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error Fichero no encontrado", 0);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se han registrado datos", "ERROR", 0);
            }

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error Fichero no encontrado", 0);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", 0);
        }
    }

    private void optenerDatosCasa() {
        ArrayList<Casa> datosCasa;
        String fichero = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader("Propietarios_Casas.json"));
            String linea;
            while ((linea = br.readLine()) != null) {
                fichero += linea;
            }

            if (!fichero.isEmpty()) {
                try {
                    BufferedReader br2 = new BufferedReader(new FileReader("Propietarios_Casas.json"));
                    datosCasa = new Gson().fromJson(br2, new TypeToken<ArrayList<Casa>>() {
                    }.getType());
                    mostrarPorpietariosCasas(datosCasa);

                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error Fichero no encontrado", 0);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se han registrado datos", "ERROR", 0);
            }

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error Fichero no encontrado", 0);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", 0);
        }
    }

    // </editor-fold> 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaPropietarios = new javax.swing.JTable();
        botonVolver = new javax.swing.JButton();
        botonMostrarCasas = new javax.swing.JButton();
        botonMostrarApartamentos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        TablaPropietarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaPropietarios);

        botonVolver.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonVolver.setText("Volver");
        botonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVolverActionPerformed(evt);
            }
        });

        botonMostrarCasas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonMostrarCasas.setText("Informacion Casas");
        botonMostrarCasas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMostrarCasasActionPerformed(evt);
            }
        });

        botonMostrarApartamentos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonMostrarApartamentos.setText("Informacion Apartamentos");
        botonMostrarApartamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMostrarApartamentosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonMostrarApartamentos)
                        .addGap(80, 80, 80)
                        .addComponent(botonMostrarCasas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonVolver))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonVolver)
                    .addComponent(botonMostrarCasas)
                    .addComponent(botonMostrarApartamentos))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverActionPerformed
        Menu abrir = new Menu();
        abrir.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_botonVolverActionPerformed

    private void botonMostrarApartamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonMostrarApartamentosActionPerformed
        optenerDatosApartamento();
    }//GEN-LAST:event_botonMostrarApartamentosActionPerformed

    private void botonMostrarCasasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonMostrarCasasActionPerformed
        optenerDatosCasa();
    }//GEN-LAST:event_botonMostrarCasasActionPerformed

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
            java.util.logging.Logger.getLogger(ListaPropietarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaPropietarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaPropietarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaPropietarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaPropietarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaPropietarios;
    private javax.swing.JButton botonMostrarApartamentos;
    private javax.swing.JButton botonMostrarCasas;
    private javax.swing.JButton botonVolver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTitulo;
    // End of variables declaration//GEN-END:variables
}
