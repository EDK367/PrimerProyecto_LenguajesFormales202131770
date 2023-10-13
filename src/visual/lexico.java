/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package visual;

import analizadorSintactico.Estructura;
import analizadorSintactico.sintaticoPrueba;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.Buffer;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import numero.NumeroLinea;
import visualTokens.TokensVisuales;
import visualSintactico.SintacticoVisual;
/**
 *
 * @author denil
 */
public class lexico extends javax.swing.JPanel {
    File lectura;
    String errorSIn = "Ocurrio Un error Sintactico";
    NumeroLinea numeroLinea;
    DefaultTableModel modelo;
    JFileChooser seleccionar = new JFileChooser();
    File archivos;
    FileInputStream entrada;
    FileOutputStream salida;
    TokensVisuales visual = new TokensVisuales();
    public lexico() {

        initComponents();
        numeroLinea = new NumeroLinea(codigo);
        scrollLex.setRowHeaderView(numeroLinea);
        numeroLinea = new NumeroLinea(codSintactico);
        sintaticoScroll.setRowHeaderView(numeroLinea);

        /*   lex.addKeyListener(new java.awt.event.KeyAdapter() {
           public void keyReleased(java.awt.event.KeyEvent evt) {
               colorearTexto();
            }
        });*/
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
        run = new javax.swing.JButton();
        scrollLex = new javax.swing.JScrollPane();
        codigo = new javax.swing.JTextPane();
        jButton3 = new javax.swing.JButton();
        refresh = new javax.swing.JButton();
        sintaticoScroll = new javax.swing.JScrollPane();
        codSintactico = new javax.swing.JTextPane();
        tokensButton = new javax.swing.JButton();
        botonSIntactico = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        run.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton.png"))); // NOI18N
        run.setText("Run");
        run.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        run.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        run.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runActionPerformed(evt);
            }
        });
        jPanel1.add(run, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 10, 100, 50));

        scrollLex.setForeground(new java.awt.Color(204, 204, 204));

        codigo.setBackground(new java.awt.Color(153, 153, 153));
        codigo.setFont(new java.awt.Font("Yu Gothic UI", 0, 22)); // NOI18N
        codigo.setForeground(new java.awt.Color(0, 0, 0));
        codigo.setCaretColor(new java.awt.Color(0, 0, 102));
        codigo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        codigo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        codigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codigoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                codigoKeyReleased(evt);
            }
        });
        scrollLex.setViewportView(codigo);

        jPanel1.add(scrollLex, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 1070, 370));

        jButton3.setText("Abrir Archivo");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/refres.png"))); // NOI18N
        refresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });
        jPanel1.add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 40, 30));

        codSintactico.setBackground(new java.awt.Color(153, 153, 153));
        sintaticoScroll.setViewportView(codSintactico);

        jPanel1.add(sintaticoScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 1070, 200));

        tokensButton.setForeground(new java.awt.Color(0, 153, 204));
        tokensButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8-index-48_1.png"))); // NOI18N
        tokensButton.setText("Tokens");
        tokensButton.setBorderPainted(false);
        tokensButton.setContentAreaFilled(false);
        tokensButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tokensButton.setFocusPainted(false);
        tokensButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tokensButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tokensButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tokensButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tokensButtonMouseExited(evt);
            }
        });
        tokensButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tokensButtonActionPerformed(evt);
            }
        });
        jPanel1.add(tokensButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 10, -1, 70));

        botonSIntactico.setForeground(new java.awt.Color(204, 255, 255));
        botonSIntactico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8_code_48px.png"))); // NOI18N
        botonSIntactico.setText("Compilar");
        botonSIntactico.setBorderPainted(false);
        botonSIntactico.setContentAreaFilled(false);
        botonSIntactico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonSIntactico.setFocusPainted(false);
        botonSIntactico.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonSIntactico.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonSIntactico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonSIntacticoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonSIntacticoMouseExited(evt);
            }
        });
        botonSIntactico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSIntacticoActionPerformed(evt);
            }
        });
        jPanel1.add(botonSIntactico, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 0, -1, 90));

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
    //boton run para correr codigo 
    private void runActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runActionPerformed
        // TODO add your handling code here:
        //recibe todos los datos y los manda a analizar 
        String codigoRecorrido = codigo.getText();
        analizadorLexico lexico = new analizadorLexico(codigoRecorrido);
        List<Token> tokens = lexico.analizadorLexico();
        sintaticoPrueba si = new sintaticoPrueba(tokens);
        si.buscar();
        guardar();
        colorearTexto();
        visual.dispose();
        sintactico();
        // hola();


    }//GEN-LAST:event_runActionPerformed
    //boton para abrir un archivo
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //boton abrir archivo
        if (seleccionar.showDialog(null, "Abrir") == JFileChooser.APPROVE_OPTION) {
            archivos = seleccionar.getSelectedFile();
            if (archivos.canRead()) {
                if (archivos.getName().endsWith("txt")) {
                    String documento = abrirArchivo(archivos);

                    codigo.setText(documento);
                } else {
                    JOptionPane.showMessageDialog(null, "Archivo no encontrado");
                }
            }

        }

    }//GEN-LAST:event_jButton3ActionPerformed
    //prueba no servible ahorita
    private void codigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoKeyReleased
        // TODO add your handling code here:
        // validez();
    }//GEN-LAST:event_codigoKeyReleased
    //limpiar el panel
    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        // TODO add your handling code here:
        limpiar();
        codigo.setText("");
    }//GEN-LAST:event_refreshActionPerformed
    //eventos de codigo
    private void codigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoKeyPressed
        // Boton run en donde se escribe
        colorearTexto();
        if (evt.getKeyCode() == KeyEvent.VK_F6) {
            run.doClick();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            refresh.doClick();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F5) {
            tokensButton.doClick();
        tokensButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8-index-49.png")));    
        }
        if (evt.getKeyCode() == KeyEvent.VK_F7) {
            botonSIntactico.doClick();
        botonSIntactico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8_code_48px_p.png")));
        }

    }//GEN-LAST:event_codigoKeyPressed
    //metodo para limpiar el panel
    public void limpiar() {
        TokensVisuales limpio = new TokensVisuales();
        DefaultTableModel tablaLimpia = (DefaultTableModel) limpio.getTablaTokens().getModel();
        tablaLimpia.setRowCount(0);
    }
    //boton para ver los tokesn
    private void tokensButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tokensButtonActionPerformed
        validarTOkens();
    }//GEN-LAST:event_tokensButtonActionPerformed

    private void botonSIntacticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSIntacticoActionPerformed
        validarSintactico();
       // sintactico();
    }//GEN-LAST:event_botonSIntacticoActionPerformed
    //metodo para ver si existen errores sintacticos
    public void sintactico(){
        String codigoRecorrido = codigo.getText();
    analizadorLexico lexico = new analizadorLexico(codigoRecorrido);
    List<Token> tokens = lexico.analizadorLexico();
    sintaticoPrueba sin = new sintaticoPrueba(tokens);
    List<Estructura> estructura = sin.buscar();
    codSintactico.setText("");
    StringBuilder textoSintactico = new StringBuilder();
    boolean errorSintactico = false; // Variable para rastrear si se encontró un error
    boolean errorLexico = false;
    for (Estructura estructura1 : estructura) {
        if (estructura1.getExpresion().equals(errorSIn)) {
            textoSintactico.append(estructura1.toString()).append("\n");
            errorSintactico = true; // Marcamos que se encontró un error
        }
    }
        for (Token token : tokens) {
            if(token.getTipoToken().equals("ERROR")){
                errorLexico = true;
            }
            
        }
    // Si no se encontró ningún error, mostramos un mensaje
    if (!errorSintactico && !errorLexico) {
        textoSintactico.append("No se encontró ningún error Lexico."
                + "\nNo se encontró ningún error Sintactico");
    }else if(errorLexico && !errorSintactico){
        textoSintactico.append("Existen errores Lexicos"
                + "\nNo se encontró ningún error Sintactico");
    }else if(!errorLexico && errorSintactico){
        textoSintactico.append("No se encontró ningún error Lexico.");
    }else if(errorLexico && errorSintactico){
        textoSintactico.append("Existen Errores Lexicos.");
    }
    

    // Establece el texto completo en el JTextPane
    codSintactico.setText(textoSintactico.toString());


    
    }
    
    //eventos para las imagenes
    private void tokensButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tokensButtonMouseEntered
        // TODO add your handling code here:
    tokensButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8-index-48.png")));
    }//GEN-LAST:event_tokensButtonMouseEntered

    private void tokensButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tokensButtonMouseExited
    tokensButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8-index-48_1.png")));
    }//GEN-LAST:event_tokensButtonMouseExited

    private void botonSIntacticoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonSIntacticoMouseEntered
    botonSIntactico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8_code_48px_on.png")));
    }//GEN-LAST:event_botonSIntacticoMouseEntered

    private void botonSIntacticoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonSIntacticoMouseExited
    botonSIntactico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8_code_48px.png")));  
    }//GEN-LAST:event_botonSIntacticoMouseExited
    //imprimir lo neceasario metodo de prueba
    public void imprimir() {
        String codigoRecorrido = codigo.getText();
        analizadorLexico lexico = new analizadorLexico(codigoRecorrido);
        List<Token> tokens = lexico.analizadorLexico();
        sintaticoPrueba sin = new sintaticoPrueba(tokens);
        List<Estructura> estructura = sin.buscar();
        codSintactico.setText("");
        StringBuilder textoSintactico = new StringBuilder();
        for (Estructura estructura1 : estructura) {
            textoSintactico.append(estructura1.toString()).append("\n");
        }
        
        codSintactico.setText(textoSintactico.toString());
    }
    //metodo para imprimir en textPane
    public void appendToPane(JTextPane tp, String msg) {
        Document doc = tp.getDocument();
        try {
            doc.insertString(doc.getLength(), msg, null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    //valida los tokens y los envia tokensVisuales para poder clasificarlos a gusto
    public void validarTOkens() {
        TokensVisuales visualToken = new TokensVisuales();
        visualToken.setVisible(true);
        String codigoEscrito = codigo.getText();

        analizadorLexico analiza = new analizadorLexico(codigoEscrito);
        List<Token> tokens = analiza.analizadorLexico();
        modelo = (DefaultTableModel) visualToken.getTablaTokens().getModel();

        Object[] objeto = new Object[5];
        for (int i = 0; i < tokens.size(); i++) {
            objeto[0] = tokens.get(i).getTipoToken();
            objeto[1] = tokens.get(i).getPatron();
            objeto[2] = tokens.get(i).getLexema();
            objeto[3] = tokens.get(i).getFila();
            objeto[4] = tokens.get(i).getColumna();

            modelo.addRow(objeto);
        }
        visualToken.getTablaTokens().setModel(modelo);
    }
    public void validarSintactico(){
        SintacticoVisual visualSinta = new SintacticoVisual();
        visualSinta.setVisible(true);
        String codigoEscrito = codigo.getText();
        analizadorLexico analiza = new analizadorLexico(codigoEscrito);
        List<Token> tokens = analiza.analizadorLexico();
        sintaticoPrueba sin = new sintaticoPrueba(tokens);
        List<Estructura> sintac = sin.buscar();
        modelo = (DefaultTableModel) visualSinta.getTableGloblal().getModel();
        Object [] objeto = new Object[6];
        for (int i = 0; i < sintac.size() ; i++) {
            objeto[0] = sintac.get(i).getExpresion();
            objeto[1] = sintac.get(i).getBloque();
            objeto[2] = sintac.get(i).getValidacion();
            objeto[3] = sintac.get(i).getError();
            objeto[4] = sintac.get(i).getFila();
            objeto[5] = sintac.get(i).getColumna();
            
            modelo.addRow(objeto);
        }
        visualSinta.getTableGloblal().setModel(modelo);
    }
        

    //METODO PARA ABRIR Y GUARDADR
    public String abrirArchivo(File archivos) {
        String documento = "";

        try {
            entrada = new FileInputStream(archivos);
            int doc;

            while ((doc = entrada.read()) != -1) {
                char caracter = (char) doc;
                documento += caracter;
            }
        } catch (Exception e) {
        }
        return documento;
    }
    void guardar() {

        File lectura = new File("Datos.txt");

        if (lectura.exists()) {
            if (lectura.delete()) {
            } else {
                System.out.println("No se pudo eliminar el archivo existente");
            }
        }

        try {
            if (lectura.createNewFile()) {
            } else {
                System.out.println("No se pudo crear el archivo");
            }

            FileWriter escribir = new FileWriter(lectura, true);
            escribir.write(codigo.getText());
            escribir.close();
        } catch (Exception e) {
            System.out.println("ERROR en todo");
        }
    }
    
    //colores
    private void colorearTexto() {
        final StyleContext contenido = StyleContext.getDefaultStyleContext();//stilo del texto por default

        String escrituraDeCodigo = codigo.getText();
        analizadorLexico lexico = new analizadorLexico(escrituraDeCodigo);
        List<Token> tokens = lexico.analizadorLexico();
        StyledDocument documento = codigo.getStyledDocument(); //documento del jTextPane
        String textoDocumento;
        try {
            textoDocumento = documento.getText(0, documento.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
            return;
        }

        // eliminar estilos del jTextPane para que sea un reinicio
        documento.setCharacterAttributes(0, documento.getLength(), new SimpleAttributeSet(), true);

        for (Token token : tokens) {
            int posicion = 0;
            while ((posicion = textoDocumento.indexOf(token.getLexema(), posicion)) >= 0) {
                AttributeSet estilo = obtenerEstilo(token.getTipoToken());
                int finToken = posicion + token.getLexema().length();
                documento.setCharacterAttributes(posicion, finToken - posicion, estilo, false);
                posicion = finToken;
            }
        }
    }
    private AttributeSet obtenerEstilo(String tipoToken) {

        final StyleContext contenido = StyleContext.getDefaultStyleContext();

        if ("Identificador".equals(tipoToken)) {
            return contenido.addAttribute(contenido.getEmptySet(), StyleConstants.Foreground, Color.WHITE);
        } else if ("Aritmetico".equals(tipoToken) || "Comparación".equals(tipoToken)
                || "Logicos".equals(tipoToken) || "Asignacion".equals(tipoToken)) {
            return contenido.addAttribute(contenido.getEmptySet(), StyleConstants.Foreground, new Color(0, 170, 228));
        } else if ("Palabra reservada".equals(tipoToken)) {
            return contenido.addAttribute(contenido.getEmptySet(), StyleConstants.Foreground, new Color(87, 35, 100));
        } else if ("Numero entero".equals(tipoToken) || "Numero decimal".equals(tipoToken)
                || "Cadena".equals(tipoToken)) {
            return contenido.addAttribute(contenido.getEmptySet(), StyleConstants.Foreground, Color.RED);
        } else if ("Comentario".equals(tipoToken)) {
            return contenido.addAttribute(contenido.getEmptySet(), StyleConstants.Foreground, new Color(207, 207, 207));
        } else if ("Comentario Simple".equals(tipoToken)) {
            return contenido.addAttribute(contenido.getEmptySet(), StyleConstants.Foreground, Color.WHITE);
        } else if ("Otros".equals(tipoToken)) {
            return contenido.addAttribute(contenido.getEmptySet(), StyleConstants.Foreground, Color.GREEN);
        } else if ("ERROR".equals(tipoToken)) {
            return contenido.addAttribute(contenido.getEmptySet(), StyleConstants.Foreground, new Color(254, 0, 208));
        } else {
            return contenido.addAttribute(contenido.getEmptySet(), StyleConstants.Foreground, Color.CYAN);
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonSIntactico;
    private javax.swing.JTextPane codSintactico;
    public javax.swing.JTextPane codigo;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton refresh;
    private javax.swing.JButton run;
    private javax.swing.JScrollPane scrollLex;
    private javax.swing.JScrollPane sintaticoScroll;
    private javax.swing.JButton tokensButton;
    // End of variables declaration//GEN-END:variables
}
