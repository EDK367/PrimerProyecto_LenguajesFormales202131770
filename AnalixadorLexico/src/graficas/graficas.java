/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package graficas;

import visual.*;
import graficas.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author denil
 */
public class graficas extends javax.swing.JPanel {
lexico lexico = new lexico();


private final String[] palabrasClave = {
    "as","assert","break","class","continue","def","def","del","elif",
    "else","except","False","finally","for","from","global","if","import","in",
    "is","lamda","None","nonlocal","pass","raise","return","True",
    "try","while","with","yield"    
    };
    /**
     * Creates new form graficos
     */
    public graficas() {
        initComponents();
        llamada();
     
    
    }
  
    
    public void llamada(){
           for (String claves : palabrasClave){
            clav.addItem(claves);
        
    }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        clav = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        pepe = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(1100, 700));
        setPreferredSize(new java.awt.Dimension(1100, 700));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        clav.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        clav.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavActionPerformed(evt);
            }
        });
        jPanel1.add(clav, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 80));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        jPanel1.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 190, 60));

        jPanel1.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 190, 70));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 0, -1, -1));

        pepe.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jPanel1.add(pepe, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, 340, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void clavActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clavActionPerformed
        // TODO add your handling code here:
        int opciones = clav.getSelectedIndex();
        String opcionClave = clav.getSelectedItem().toString();
        
        pepe.setText(String.valueOf(opcionClave));
        
    }//GEN-LAST:event_clavActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> clav;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField pepe;
    // End of variables declaration//GEN-END:variables
}