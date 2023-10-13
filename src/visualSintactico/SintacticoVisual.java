package visualSintactico;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TabableView;

/**
 *
 * @author denil
 */
public class SintacticoVisual extends javax.swing.JFrame {
private String errorSIn = "Ocurrio Un error Sintactico";
    /**
     * Creates new form SintacticoVisual
     */
    public SintacticoVisual() {
        initComponents();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tableGloblal = new javax.swing.JTable();
        buscador = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        estrucVis = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        estructur = new javax.swing.JTable();
        var = new javax.swing.JButton();
        terna = new javax.swing.JButton();
        IF = new javax.swing.JButton();
        ELSE = new javax.swing.JButton();
        elif = new javax.swing.JButton();
        ifElse = new javax.swing.JButton();
        forr = new javax.swing.JButton();
        While = new javax.swing.JButton();
        fun = new javax.swing.JButton();
        error = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ROWCOUNT = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        FUN = new javax.swing.JTextField();
        llame = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 450));
        setType(java.awt.Window.Type.UTILITY);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableGloblal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Expresion", "Bloque", "Validez", "Tipo", "Fila", "Colum"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableGloblal);
        if (tableGloblal.getColumnModel().getColumnCount() > 0) {
            tableGloblal.getColumnModel().getColumn(4).setResizable(false);
            tableGloblal.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 560, 240));

        buscador.setText("Estructura");
        buscador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscadorActionPerformed(evt);
            }
        });
        jPanel1.add(buscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, -1, -1));

        estrucVis.setEditable(false);
        estrucVis.setColumns(20);
        estrucVis.setRows(5);
        estrucVis.setText("Visualización de la Estructura Seleccioanada\n");
        jScrollPane2.setViewportView(estrucVis);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 480, 140));

        estructur.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Expresion", "Bloque", "Validez", "Tipo", "Fila", "Columna"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        estructur.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jScrollPane3.setViewportView(estructur);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 520, 170));

        var.setText("Variables");
        var.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                varActionPerformed(evt);
            }
        });
        jPanel1.add(var, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 180, -1, -1));

        terna.setText("Ternarios");
        terna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ternaActionPerformed(evt);
            }
        });
        jPanel1.add(terna, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 180, -1, -1));

        IF.setText("IF");
        IF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IFActionPerformed(evt);
            }
        });
        jPanel1.add(IF, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 180, -1, -1));

        ELSE.setText("IF-ELSE");
        ELSE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ELSEActionPerformed(evt);
            }
        });
        jPanel1.add(ELSE, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 180, -1, -1));

        elif.setText("If-Elif");
        elif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elifActionPerformed(evt);
            }
        });
        jPanel1.add(elif, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 180, -1, -1));

        ifElse.setText("If-Elif-Else");
        ifElse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ifElseActionPerformed(evt);
            }
        });
        jPanel1.add(ifElse, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 180, -1, -1));

        forr.setText("For");
        forr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forrActionPerformed(evt);
            }
        });
        jPanel1.add(forr, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 210, -1, -1));

        While.setText("While");
        While.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WhileActionPerformed(evt);
            }
        });
        jPanel1.add(While, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 210, -1, -1));

        fun.setText("Funciones");
        fun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                funActionPerformed(evt);
            }
        });
        jPanel1.add(fun, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 210, -1, -1));

        error.setText("Errores");
        error.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                errorActionPerformed(evt);
            }
        });
        jPanel1.add(error, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 210, -1, -1));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cantidad");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 250, -1, -1));

        ROWCOUNT.setEditable(false);
        jPanel1.add(ROWCOUNT, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 250, 80, -1));

        jButton1.setText("Buscar Funcion");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, -1, -1));
        jPanel1.add(FUN, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 400, 450, -1));

        llame.setText("Llamadas");
        llame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                llameActionPerformed(evt);
            }
        });
        jPanel1.add(llame, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 210, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscadorActionPerformed
    int fila = tableGloblal.getSelectedRow();
        if (fila != -1) {
            String datos = "Expresión: "+tableGloblal.getValueAt(fila, 0) + "\n"
            +"Tipo de Bloque: "+ tableGloblal.getValueAt(fila, 1) + "\n"
            +"Validacion de Bloque: "+ tableGloblal.getValueAt(fila, 2) + "\n"
            +"Tipo de Valor o Error: "+ tableGloblal.getValueAt(fila, 3) + "\n"        
            +"Fila: "+ tableGloblal.getValueAt(fila, 4) + "\n"
            +"Columna: "+ tableGloblal.getValueAt(fila, 5);
            estrucVis.setText(datos);
        } else {
            estrucVis.setText("No se ha seleccionado ninguna fila.");
        }
    }//GEN-LAST:event_buscadorActionPerformed

    private void varActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_varActionPerformed
    variables();
    conteo();
    }//GEN-LAST:event_varActionPerformed

    private void ternaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ternaActionPerformed
    ternarios();
    conteo();
    }//GEN-LAST:event_ternaActionPerformed

    private void IFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IFActionPerformed
    IF();
    conteo();
    }//GEN-LAST:event_IFActionPerformed

    private void ELSEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ELSEActionPerformed
    ELSE();
    conteo();
    }//GEN-LAST:event_ELSEActionPerformed

    private void elifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elifActionPerformed
    ELIF();
    conteo();
    }//GEN-LAST:event_elifActionPerformed

    private void ifElseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ifElseActionPerformed
    ELIF_ELSE();
    conteo();
    }//GEN-LAST:event_ifElseActionPerformed

    private void forrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forrActionPerformed
    For();
    conteo();
    }//GEN-LAST:event_forrActionPerformed

    private void WhileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WhileActionPerformed
    While();
    conteo();
    }//GEN-LAST:event_WhileActionPerformed

    private void funActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_funActionPerformed
    funciones();
    conteo();
    }//GEN-LAST:event_funActionPerformed

    private void errorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_errorActionPerformed
    errores();
    conteo();
    }//GEN-LAST:event_errorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    buscarTabla();
    conteo();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void llameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_llameActionPerformed
    llamadas();
    conteo();
    }//GEN-LAST:event_llameActionPerformed
   
    public void variables(){
        String expresion = "Variable";
        DefaultTableModel filtrado = (DefaultTableModel) estructur.getModel();
        DefaultTableModel original = (DefaultTableModel) tableGloblal.getModel();
        filtrado.setRowCount(0);
        for (int i = 0; i < original.getRowCount(); i++) {
            String filtroToken = original.getValueAt(i, 0).toString();
            if (filtroToken.equalsIgnoreCase(expresion)) {
                Object[] contenido = {
                    original.getValueAt(i, 0),
                    original.getValueAt(i, 1),
                    original.getValueAt(i, 2),
                    original.getValueAt(i, 3),
                    original.getValueAt(i, 4),
                    original.getValueAt(i, 5)
                };
                filtrado.addRow(contenido);
            }
        }

    }
    public void ternarios(){
        String expresion = "Operador Ternario";
        DefaultTableModel filtrado = (DefaultTableModel) estructur.getModel();
        DefaultTableModel original = (DefaultTableModel) tableGloblal.getModel();
        filtrado.setRowCount(0);
        for (int i = 0; i < original.getRowCount(); i++) {
            String filtroToken = original.getValueAt(i, 0).toString();
            if (filtroToken.equalsIgnoreCase(expresion)) {
                Object[] contenido = {
                    original.getValueAt(i, 0),
                    original.getValueAt(i, 1),
                    original.getValueAt(i, 2),
                    original.getValueAt(i, 3),
                    original.getValueAt(i, 4),
                    original.getValueAt(i, 5)
                };
                filtrado.addRow(contenido);
            }
        }

    }   
    public void IF(){
        String expresion = "IF";
        DefaultTableModel filtrado = (DefaultTableModel) estructur.getModel();
        DefaultTableModel original = (DefaultTableModel) tableGloblal.getModel();
        filtrado.setRowCount(0);
        for (int i = 0; i < original.getRowCount(); i++) {
            String filtroToken = original.getValueAt(i, 0).toString();
            if (filtroToken.equalsIgnoreCase(expresion)) {
                Object[] contenido = {
                    original.getValueAt(i, 0),
                    original.getValueAt(i, 1),
                    original.getValueAt(i, 2),
                    original.getValueAt(i, 3),
                    original.getValueAt(i, 4),
                    original.getValueAt(i, 5)
                };
                filtrado.addRow(contenido);
            }
        }

    } 
    public void ELSE(){
        String expresion = "IF-Else";
        DefaultTableModel filtrado = (DefaultTableModel) estructur.getModel();
        DefaultTableModel original = (DefaultTableModel) tableGloblal.getModel();
        filtrado.setRowCount(0);
        for (int i = 0; i < original.getRowCount(); i++) {
            String filtroToken = original.getValueAt(i, 0).toString();
            if (filtroToken.equalsIgnoreCase(expresion)) {
                Object[] contenido = {
                    original.getValueAt(i, 0),
                    original.getValueAt(i, 1),
                    original.getValueAt(i, 2),
                    original.getValueAt(i, 3),
                    original.getValueAt(i, 4),
                    original.getValueAt(i, 5)
                };
                filtrado.addRow(contenido);
            }
        }

    } 
    public void ELIF(){
        String expresion = "IF-Elif";
        DefaultTableModel filtrado = (DefaultTableModel) estructur.getModel();
        DefaultTableModel original = (DefaultTableModel) tableGloblal.getModel();
        filtrado.setRowCount(0);
        for (int i = 0; i < original.getRowCount(); i++) {
            String filtroToken = original.getValueAt(i, 0).toString();
            if (filtroToken.equalsIgnoreCase(expresion)) {
                Object[] contenido = {
                    original.getValueAt(i, 0),
                    original.getValueAt(i, 1),
                    original.getValueAt(i, 2),
                    original.getValueAt(i, 3),
                    original.getValueAt(i, 4),
                    original.getValueAt(i, 5)
                };
                filtrado.addRow(contenido);
            }
        }

    } 
    public void ELIF_ELSE(){
        String expresion = "IF-Elif-Else";
        DefaultTableModel filtrado = (DefaultTableModel) estructur.getModel();
        DefaultTableModel original = (DefaultTableModel) tableGloblal.getModel();
        filtrado.setRowCount(0);
        for (int i = 0; i < original.getRowCount(); i++) {
            String filtroToken = original.getValueAt(i, 0).toString();
            if (filtroToken.equalsIgnoreCase(expresion)) {
                Object[] contenido = {
                    original.getValueAt(i, 0),
                    original.getValueAt(i, 1),
                    original.getValueAt(i, 2),
                    original.getValueAt(i, 3),
                    original.getValueAt(i, 4),
                    original.getValueAt(i, 5)
                };
                filtrado.addRow(contenido);
            }
        }

    } 
    public void For(){
        String expresion = "For";
        DefaultTableModel filtrado = (DefaultTableModel) estructur.getModel();
        DefaultTableModel original = (DefaultTableModel) tableGloblal.getModel();
        filtrado.setRowCount(0);
        for (int i = 0; i < original.getRowCount(); i++) {
            String filtroToken = original.getValueAt(i, 0).toString();
            if (filtroToken.equalsIgnoreCase(expresion)) {
                Object[] contenido = {
                    original.getValueAt(i, 0),
                    original.getValueAt(i, 1),
                    original.getValueAt(i, 2),
                    original.getValueAt(i, 3),
                    original.getValueAt(i, 4),
                    original.getValueAt(i, 5)
                };
                filtrado.addRow(contenido);
            }
        }

    } 
    public void While(){
        String expresion = "While";
        DefaultTableModel filtrado = (DefaultTableModel) estructur.getModel();
        DefaultTableModel original = (DefaultTableModel) tableGloblal.getModel();
        filtrado.setRowCount(0);
        for (int i = 0; i < original.getRowCount(); i++) {
            String filtroToken = original.getValueAt(i, 0).toString();
            if (filtroToken.equalsIgnoreCase(expresion)) {
                Object[] contenido = {
                    original.getValueAt(i, 0),
                    original.getValueAt(i, 1),
                    original.getValueAt(i, 2),
                    original.getValueAt(i, 3),
                    original.getValueAt(i, 4),
                    original.getValueAt(i, 5)
                };
                filtrado.addRow(contenido);
            }
        }

    } 
    public void funciones(){
        String expresion = "Funcion";
        DefaultTableModel filtrado = (DefaultTableModel) estructur.getModel();
        DefaultTableModel original = (DefaultTableModel) tableGloblal.getModel();
        filtrado.setRowCount(0);
        for (int i = 0; i < original.getRowCount(); i++) {
            String filtroToken = original.getValueAt(i, 0).toString();
            if (filtroToken.equalsIgnoreCase(expresion)) {
                Object[] contenido = {
                    original.getValueAt(i, 0),
                    original.getValueAt(i, 1),
                    original.getValueAt(i, 2),
                    original.getValueAt(i, 3),
                    original.getValueAt(i, 4),
                    original.getValueAt(i, 5)
                };
                filtrado.addRow(contenido);
            }
        }

    }
    public void errores(){
        DefaultTableModel filtrado = (DefaultTableModel) estructur.getModel();
        DefaultTableModel original = (DefaultTableModel) tableGloblal.getModel();
        filtrado.setRowCount(0);
        for (int i = 0; i < original.getRowCount(); i++) {
            String filtroToken = original.getValueAt(i, 0).toString();
            if (filtroToken.equalsIgnoreCase(errorSIn)) {
                Object[] contenido = {
                    original.getValueAt(i, 0),
                    original.getValueAt(i, 1),
                    original.getValueAt(i, 2),
                    original.getValueAt(i, 3),
                    original.getValueAt(i, 4),
                    original.getValueAt(i, 5)
                };
                filtrado.addRow(contenido);
            }
        }

    }
    public void llamadas(){
        String llamadas = "Print Funcion";
        String llamadas1 = "Llamada de Funcion";
        DefaultTableModel filtrado = (DefaultTableModel) estructur.getModel();
        DefaultTableModel original = (DefaultTableModel) tableGloblal.getModel();
        filtrado.setRowCount(0);
        for (int i = 0; i < original.getRowCount(); i++) {
            String filtroToken = original.getValueAt(i, 0).toString();
            if (filtroToken.equalsIgnoreCase(llamadas) 
                || filtroToken.equalsIgnoreCase(llamadas1)) {
                Object[] contenido = {
                    original.getValueAt(i, 0),
                    original.getValueAt(i, 1),
                    original.getValueAt(i, 2),
                    original.getValueAt(i, 3),
                    original.getValueAt(i, 4),
                    original.getValueAt(i, 5)
                };
                filtrado.addRow(contenido);
            }
        }

    }
    public void conteo(){
        DefaultTableModel model = (DefaultTableModel) estructur.getModel();
        int conteo = model.getRowCount();
        
        ROWCOUNT.setText(Integer.toString(conteo));
        
    }
    
    private void buscarTabla() {
        //buscar lexema 
    String buscar = FUN.getText().toLowerCase();
    DefaultTableModel filtrado = (DefaultTableModel) estructur.getModel();
    DefaultTableModel original = (DefaultTableModel) tableGloblal.getModel();
    filtrado.setRowCount(0);

        for (int i = 0; i < original.getRowCount(); i++) {
            String filtroLexema = original.getValueAt(i, 1).toString().toLowerCase();
            if(filtroLexema.contains(buscar)){
              Object[] contenido={
                  original.getValueAt(i, 0),
                  original.getValueAt(i, 1),
                  original.getValueAt(i, 2),
                  original.getValueAt(i, 3),
                  original.getValueAt(i, 4),
                  original.getValueAt(i, 5)
              };  
              filtrado.addRow(contenido);
            }
        }
   
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
            java.util.logging.Logger.getLogger(SintacticoVisual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SintacticoVisual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SintacticoVisual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SintacticoVisual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SintacticoVisual().setVisible(true);
            }
        });
    }

    public JTable getTableGloblal() {
        return tableGloblal;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ELSE;
    private javax.swing.JTextField FUN;
    private javax.swing.JButton IF;
    private javax.swing.JTextField ROWCOUNT;
    private javax.swing.JButton While;
    private javax.swing.JButton buscador;
    private javax.swing.JButton elif;
    private javax.swing.JButton error;
    private javax.swing.JTextArea estrucVis;
    private javax.swing.JTable estructur;
    private javax.swing.JButton forr;
    private javax.swing.JButton fun;
    private javax.swing.JButton ifElse;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton llame;
    private javax.swing.JTable tableGloblal;
    private javax.swing.JButton terna;
    private javax.swing.JButton var;
    // End of variables declaration//GEN-END:variables
}
