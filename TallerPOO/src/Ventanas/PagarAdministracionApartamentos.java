package Ventanas;

import Modelado.Apartamento;
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

public class PagarAdministracionApartamentos extends javax.swing.JFrame {

    public PagarAdministracionApartamentos() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        txtTotalAPagarApartamento.setEditable(false);
        mostrarValorAdministracion();
    }

    // <editor-fold defaultstate="collapsed" desc="Funciones">
    private void mostrarValorAdministracion() {

        ArrayList<Apartamento> listaPropietariosApartamentos = optenerDatosApartamento();

        txtTotalAPagarApartamento.setText(String.valueOf(listaPropietariosApartamentos.get(0).getPrecioAdministracionApartamentos()));
    }

    private ArrayList optenerDatosApartamento() {
        ArrayList<Apartamento> listaPropietariosApartamentos;

        try {
            BufferedReader br = new BufferedReader(new FileReader("Propietarios_Apartamentos.json"));
            listaPropietariosApartamentos = new Gson().fromJson(br, new TypeToken<ArrayList<Apartamento>>() {
            }.getType());

            br.close();
            return listaPropietariosApartamentos;
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error Fichero no encontrado", 0);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", 0);
        }
        return null;
    }

    private ArrayList<Apartamento> validarPago() {
        ArrayList<Apartamento> datosApartamento = optenerDatosApartamento();

        boolean pago;

        String numeroDelApartamento = txtNumeroDelApartamento.getText();
        String torre = txtTorre.getText().toUpperCase();
        String nombrePropietario = txtnombrePropietarioApartamento.getText();

        for (int i = 0; i < datosApartamento.size(); i++) {
            if (numeroDelApartamento.equals(datosApartamento.get(i).getIdApartamento())
                    && torre.equals(datosApartamento.get(i).getTorre()) && nombrePropietario.equals(datosApartamento.get(i).getNombrePropietario().getNombre())) {
                pago = datosApartamento.get(i).pagoAdministracion();
                if (pago) {
                    datosApartamento.get(i).setEstadoPago(true);
                    return datosApartamento;
                } else {
                    JOptionPane.showMessageDialog(null, "no se ha podido pagar con exito la administracion de este mes", "ERROR", 0);
                    return null;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Datos Incorrectos", "ERROR", 0);
        return null;
    }

    private void pagarAdministracion() {
        Gson documento = new Gson();

        ArrayList<Apartamento> datosApartamento = validarPago();

        if (datosApartamento != null) {
            String datosPropietarioApartamento = documento.toJson(datosApartamento);

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("Propietarios_Apartamentos.json"))) {
                bw.write(datosPropietarioApartamento);
                JOptionPane.showMessageDialog(null, "Se ha pagado con exito la administracion de este mes", "", 1);
                Menu abrir = new Menu();
                abrir.setVisible(true);
                this.setVisible(false);

            } catch (IOException ex) {
                Logger.getLogger(Apartamento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // </editor-fold> 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTorre = new javax.swing.JTextField();
        txtnombrePropietarioApartamento = new javax.swing.JTextField();
        botonPagar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtTotalAPagarApartamento = new javax.swing.JTextField();
        botonVolver = new javax.swing.JButton();
        txtNumeroDelApartamento = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("PAGAR ADMINISTRACION");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Numero del apartamento");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Torre");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Nombre del propietario");

        botonPagar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonPagar.setText("Pagar");
        botonPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPagarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("total a pagar");

        botonVolver.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonVolver.setText("Volver");
        botonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(botonPagar)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTorre)
                                    .addComponent(txtnombrePropietarioApartamento)
                                    .addComponent(txtTotalAPagarApartamento)
                                    .addComponent(txtNumeroDelApartamento, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(botonVolver))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(jLabel1)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNumeroDelApartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTorre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtnombrePropietarioApartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTotalAPagarApartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonPagar)
                    .addComponent(botonVolver))
                .addGap(50, 50, 50))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPagarActionPerformed
        pagarAdministracion();
    }//GEN-LAST:event_botonPagarActionPerformed

    private void botonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverActionPerformed
        Menu abrir = new Menu();
        abrir.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_botonVolverActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PagarAdministracionApartamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PagarAdministracionApartamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PagarAdministracionApartamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PagarAdministracionApartamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PagarAdministracionApartamentos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonPagar;
    private javax.swing.JButton botonVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtNumeroDelApartamento;
    private javax.swing.JTextField txtTorre;
    private javax.swing.JTextField txtTotalAPagarApartamento;
    private javax.swing.JTextField txtnombrePropietarioApartamento;
    // End of variables declaration//GEN-END:variables
}
