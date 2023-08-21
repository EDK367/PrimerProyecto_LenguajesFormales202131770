/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package visual;

import graficas.graficas;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 *
 * @author denil
 */
public class interfazUsuario extends javax.swing.JFrame {
graficas graficas = new graficas();
lexico lexico = new lexico(); //llamar a la parte del lexico


/**
     * Creates new form interfazUsuario
     */
    public interfazUsuario() {
     
        
        initComponents();
        seleccionPanel(lexico);//que siempre se inicie la parte del lexico
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Fondo = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        grafic = new javax.swing.JButton();
        help = new javax.swing.JButton();
        acerca = new javax.swing.JButton();
        analizador = new javax.swing.JButton();
        cambia = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(1101, 750));
        setType(java.awt.Window.Type.POPUP);

        Fondo.setBackground(new java.awt.Color(255, 255, 255));
        Fondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        grafic.setText("Generar Grafica");
        grafic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graficActionPerformed(evt);
            }
        });

        help.setText("Ayuda");

        acerca.setText("Acerca De");

        analizador.setText("Escritura");
        analizador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analizadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(analizador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grafic)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(help)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acerca)
                .addContainerGap(647, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grafic)
                    .addComponent(help)
                    .addComponent(acerca)
                    .addComponent(analizador)))
        );

        Fondo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 50));

        cambia.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout cambiaLayout = new javax.swing.GroupLayout(cambia);
        cambia.setLayout(cambiaLayout);
        cambiaLayout.setHorizontalGroup(
            cambiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1100, Short.MAX_VALUE)
        );
        cambiaLayout.setVerticalGroup(
            cambiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        Fondo.add(cambia, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1100, 700));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void analizadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analizadorActionPerformed
     //analizador  //se llama al analizador lexico
        lexico lexico = new lexico();
        seleccionPanel(lexico);
              
    }//GEN-LAST:event_analizadorActionPerformed

    private void graficActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graficActionPerformed
        // TODO add your handling code here:
        seleccionPanel(graficas);
    }//GEN-LAST:event_graficActionPerformed


       /*public void lecturaArch(){
         
           try {
               File archivo = new File("Datos.txt");
               Scanner datos = new Scanner(archivo);
               StringBuilder escribe = new StringBuilder();
               
               while(datos.hasNextLine()){
                   escribe.append(datos.nextLine()).append("\n");
                   System.out.println("entra");
                   System.out.println(escribe.toString());
                   lexico.lex.setText(escribe.toString());
               }
               datos.close();
               
           } catch (Exception e) {
               System.out.println("Hola no se pudo");
               e.printStackTrace();
           }
       }
   */
   
   
     private void seleccionPanel(JPanel selec){ // que se realice el cambio del jpanel
        selec.setSize(1100, 700);
        selec.setLocation(0, 0);
        
        cambia.removeAll();
        cambia.add(selec, BorderLayout.CENTER);
        cambia.revalidate();
        cambia.repaint();   
    }
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
            java.util.logging.Logger.getLogger(interfazUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(interfazUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(interfazUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(interfazUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new interfazUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Fondo;
    private javax.swing.JButton acerca;
    private javax.swing.JButton analizador;
    private javax.swing.JPanel cambia;
    private javax.swing.JButton grafic;
    private javax.swing.JButton help;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}