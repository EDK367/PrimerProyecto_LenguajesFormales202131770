/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package visual;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.Buffer;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import numero.NumeroLinea;
/**
 *
 * @author denil
 */
public class lexico extends javax.swing.JPanel {
    NumeroLinea numeroLinea;
    
       JFileChooser seleccionar = new JFileChooser();
       File archivos;
       FileInputStream entrada;
       FileOutputStream salida;
    /**
     * Creates new form lexico
     */
    public lexico() { 
       
        initComponents();
        validez();
        numeroLinea = new NumeroLinea(lex);
        scrollLex.setRowHeaderView(numeroLinea);
        numeroLinea = new NumeroLinea(lexemas);
        scrollLexemas.setRowHeaderView(numeroLinea);
        numeroLinea = new NumeroLinea(errores);
        scrollErrores.setRowHeaderView(numeroLinea);
         lex.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                colorearTexto();
            }
        });
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
        jButton1 = new javax.swing.JButton();
        scrollLex = new javax.swing.JScrollPane();
        lex = new javax.swing.JTextPane();
        scrollLexemas = new javax.swing.JScrollPane();
        lexemas = new javax.swing.JTextArea();
        scrollErrores = new javax.swing.JScrollPane();
        errores = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton.png"))); // NOI18N
        jButton1.setText("Run");
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 0, 100, 50));

        scrollLex.setForeground(new java.awt.Color(204, 204, 204));

        lex.setBackground(new java.awt.Color(255, 255, 255));
        lex.setFont(new java.awt.Font("Yu Gothic UI", 0, 22)); // NOI18N
        lex.setForeground(new java.awt.Color(0, 0, 0));
        lex.setCaretColor(new java.awt.Color(0, 0, 102));
        lex.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lex.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lex.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lexKeyReleased(evt);
            }
        });
        scrollLex.setViewportView(lex);

        jPanel1.add(scrollLex, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 1050, 420));

        lexemas.setEditable(false);
        lexemas.setColumns(20);
        lexemas.setRows(5);
        lexemas.setText("\n\n\n\n");
        scrollLexemas.setViewportView(lexemas);

        jPanel1.add(scrollLexemas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 610, 210));

        errores.setEditable(false);
        errores.setColumns(20);
        errores.setRows(5);
        errores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                erroresKeyReleased(evt);
            }
        });
        scrollErrores.setViewportView(errores);

        jPanel1.add(scrollErrores, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 510, 420, 150));

        jButton3.setText("Abrir Archivo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jButton2.setText("refresh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
//boton para correr el codigo en el jtextArea
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //recibe todos los datos y los manda a analizar 
       
        validez();
        guardar();
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //boton abrir archivo
        
        if(seleccionar.showDialog(null, "Abrir")==JFileChooser.APPROVE_OPTION){
            archivos=seleccionar.getSelectedFile();
            if(archivos.canRead()){
                 if(archivos.getName().endsWith("txt")){
                     String documento=abrirArchivo(archivos);
                     
                     lex.setText(documento);
                 }else{
                     JOptionPane.showMessageDialog(null, "Archivo no encontrado");
                 }
            }
            
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void lexKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lexKeyReleased
        // TODO add your handling code here:
        //a validez();
    }//GEN-LAST:event_lexKeyReleased

    private void erroresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_erroresKeyReleased
        // TODO add your handling code here:
        errores();
    }//GEN-LAST:event_erroresKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        lex.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed
   public void validez(){
    String codigoEscrito = lex.getText();
        analizadorLexico analiza = new analizadorLexico(codigoEscrito);
        List<Token> tokens = analiza.analizadorLexico();
        List<Token> tokenError = analiza.analizadorLexico();
        
        if(tokens !=null && tokenError.isEmpty()){
        lexemas.setText("");
            System.out.println("entra en tokens vacios");
        for (Token token : tokens) {
            lexemas.append(token.toString() + "\n");
        }
        errores.setText("");
        }else{
            lexemas.setText("ERROR en caracter Desconocido");
            System.out.println("aca llega");
        errores();       
}
    
        
   }

   public void errores(){
       String codigoEscrito = lex.getText();
       analizadorLexico anali = new analizadorLexico(codigoEscrito);
       
       List<Token> tokenError = anali.analizadorLexico();
        errores.setText(" ");
        for (Token token1 : tokenError){
            errores.append(token1.toString() + "\n");
        
}
   }
   
   public String abrirArchivo(File archivos){
         String documento = "";
         
         try {
           entrada = new FileInputStream(archivos);
           int doc;
           
           while((doc=entrada.read())!= -1){
               char caracter = (char)doc;
               documento+=caracter;
           }
       } catch (Exception e) {
       }
       return documento;
   }
   
   private ArrayList<String> contenido = new ArrayList<>();
   public void tok(){
       String toks = lexemas.getText();
       contenido.add(toks);
       
   }
   public ArrayList<String> getContenido() {
        return contenido;
    }
   
   File lectura;
   void guardar(){
       
    File lectura = new File("Datos.txt");
    
    if (lectura.exists()) {
        if (lectura.delete()) {
            System.out.println("Archivo existente eliminado");
        } else {
            System.out.println("No se pudo eliminar el archivo existente");
        }
    }
    
    try {
        if (lectura.createNewFile()) {
            System.out.println("Archivo creado exitosamente");
        } else {
            System.out.println("No se pudo crear el archivo");
        }
        
        FileWriter escribir = new FileWriter(lectura, true);
        escribir.write(lex.getText());
        escribir.close();
    } catch (Exception e) {
        System.out.println("ERROR en todo");
    }
   }

//colores
private void colorearTexto(){
    final StyleContext contenido = StyleContext.getDefaultStyleContext();//stilo del texto por default
    
    
    String escrituraDeCodigo = lex.getText();
    analizadorLexico lexico = new analizadorLexico(escrituraDeCodigo);
    List<Token> tokens = lexico.analizadorLexico();
    StyledDocument documento = lex.getStyledDocument(); //documento del jTextPane
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
    
        if("Identificador".equals(tipoToken)){
        return contenido.addAttribute(contenido.getEmptySet(), StyleConstants.Foreground, Color.BLACK);
        
        }else if("Aritmetico".equals(tipoToken) || "Comparación".equals(tipoToken)
            || "Logicos".equals(tipoToken) || "Asignacion".equals(tipoToken)){
            return contenido.addAttribute(contenido.getEmptySet(), StyleConstants.Foreground, new Color(0, 170, 228));
        }else if("Palabra reservada".equals(tipoToken)){
            return contenido.addAttribute(contenido.getEmptySet(), StyleConstants.Foreground, new Color(87, 35, 100));
        }else if("Numero entero".equals(tipoToken) || "Numero decimal".equals(tipoToken)
            || "Cadena".equals(tipoToken)){
            return contenido.addAttribute(contenido.getEmptySet(), StyleConstants.Foreground, Color.RED);
        }else if("Comentario".equals(tipoToken)){
            return contenido.addAttribute(contenido.getEmptySet(), StyleConstants.Foreground, new Color(145, 145, 145));
        }else if("Otros".equals(tipoToken)){
            return contenido.addAttribute(contenido.getEmptySet(), StyleConstants.Foreground, Color.GREEN);
        }else if("ERROR".equals(tipoToken)){
            return contenido.addAttribute(contenido.getEmptySet(), StyleConstants.Foreground, new Color(254, 0, 208));
        }else{
            return contenido.addAttribute(contenido.getEmptySet(), StyleConstants.Foreground, Color.CYAN);
        }

         
        }
      
   


   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea errores;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JTextPane lex;
    private javax.swing.JTextArea lexemas;
    private javax.swing.JScrollPane scrollErrores;
    private javax.swing.JScrollPane scrollLex;
    private javax.swing.JScrollPane scrollLexemas;
    // End of variables declaration//GEN-END:variables
}
