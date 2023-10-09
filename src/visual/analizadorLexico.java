package visual;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;


/**
 *
 * @author denil
 */
public class analizadorLexico {
    
    
    private String recorreCodigo;//recorre lo que se escribio en le JTextPane
    private int posicion;//posicion de lo que se escribio
    private int filaActual;//identifica la fila
    private int columnaActual;//identifica la columna
//constructor y asigancion de los valores siendo 1 el inicial
    public analizadorLexico(String recorreCodigo) {
        this.recorreCodigo = recorreCodigo;
        this.posicion = 0;
        this.filaActual = 1;
        this.columnaActual = 1;
    }
    private final String[] variable = {"var"};
    private final String[] salto = {" \n"};
    private final String[] identificadorEsp={"_"};
    //identificador especial el cual servira para poder usar el guion bajo
    private final String[] aritmeticos = {"+","-","**","/","//","%","*","!"};
    //suma,resta,exponente,divison,division,modulo,multiplicacion
    private final String[] comparacion = {"==","!=",">","<",">=","<="};
    //igual,diferente,mayor que,menor que,mayor igual que, menor igual que
    private final String[] logicos = {"and","or","not"};
    //y,o,negacion
    private final String[] asignacion = {"=","+=","-=","/=","//=","%=","*="};
    //asignacion, con operadores aritmeticos
    private final String[] palabrasClave = {
    "as","print","assert","break","class","continue","def","def","del","elif",
    "else","except","False","finally","for","from","global","if","import","in",
    "is","lamda","None","nonlocal","pass","raise","range","return","True",
    "try","while","with","yield"    
    };
    private final String[] booleanas = {"True","False"};        
    //verdadero y falso
    private final String[] otros = {"(",")","{","}","[","]",",",";",":","."};
    //parentesis,llaves,corchetes,coma,punto y coma,dos puntos

   


    
    
    //obtiene la posicion de lo escrito hasta el tamaño del codigo si ya no hay se detiene
    private char obtenerPosicionCaracter(){
        if(posicion>=recorreCodigo.length()){
            return '\0'; //final de la lectura
        }
        return recorreCodigo.charAt(posicion);
    } 
    //avanza la posicion del caracter y sumba fila y columna
    private void avanzarChar(){
        char caracterActual = obtenerPosicionCaracter();
        posicion++;
        columnaActual++;
        if(caracterActual == '\n'){
            saltoLinea();
        }
    }//final de avanzar
    //metodo que me sirve para identificar caracteres especiales comop lo es == o +=
    private void retroceder(){
        char caracterActual = obtenerPosicionCaracter();
        posicion--;
        columnaActual--;
        
    }//final de avanzar
    //hace el salto de linea para seguir leyendo
    private void saltoLinea(){
        filaActual++;
        columnaActual=1;
    }//final de salto de linea
    private void lineas(){
        filaActual++;
    }
    private void regresarLinea(){
        filaActual--;
        columnaActual++;
    }
    //bloque el cual me servira para almacenar y trabajar los comentarios
    private String comentario(){
     StringBuilder lexema = new StringBuilder();
     char caracterActual;
     while((caracterActual = obtenerPosicionCaracter()) != '\0'
             && (Character.isLetterOrDigit(caracterActual) 
             || caracterActual==' ' || caracterActual == '\n')){
        lexema.append(caracterActual);
        avanzarChar(); 
     }
     return lexema.toString();
    }
    //bloque que servira para obtener las comillas
    private String cadena(){
     StringBuilder lexema = new StringBuilder();
     char caracterActual;
     while((caracterActual = obtenerPosicionCaracter()) != '\0'
             && (Character.isLetterOrDigit(caracterActual) 
             || caracterActual==' ' || caracterActual == '\n')){
        lexema.append(caracterActual);
        avanzarChar(); 
     }
     return lexema.toString();
    }
    
        private String espacio(){
     StringBuilder lexema = new StringBuilder();
     char caracterActual;
     while((caracterActual = obtenerPosicionCaracter()) != '\0'
             && (Character.isLetterOrDigit(caracterActual) 
             || caracterActual == '\n')){
        lexema.append(caracterActual);
        avanzarChar(); 
     }
     return lexema.toString();
    }
    //bloque para obtener las palabras  
    private String obtenerLexemas(){
     StringBuilder lexema = new StringBuilder();
     char caracterActual;
     while((caracterActual = obtenerPosicionCaracter()) != '\0'
             && (Character.isLetterOrDigit(caracterActual) 
             || caracterActual=='_' )){
        lexema.append(caracterActual);
        avanzarChar(); 
     }
     return lexema.toString();
    }
    //bloque para obtenr los numeros tanto enteros como decimales
    private String obtenerNumero(){
     StringBuilder lexema = new StringBuilder();
     char caracterActual;
     while((caracterActual = obtenerPosicionCaracter()) != '\0'
             && (Character.isDigit(caracterActual)
             || caracterActual=='.')){
        lexema.append(caracterActual);
        avanzarChar();
        }
     return lexema.toString();
    }
    
//lectura de los arreglos para saber de que tipo es 
    //varibles
     private boolean esVar(String lexema){
             for (String var : variable){
                 if(lexema.equals(var)){
                     return true;
                 }
            }       
             return false;
        }
        
        //caracteres artimeticos
        private boolean esAritmeticos(String lexema){
             for (String ope : aritmeticos) {
                 if (lexema.equals(ope)) {
                     return true;
            }
        }
        return false;
    }
        //uso del guion bajo
        private boolean esEspecial(String lexema){
             for (String es : identificadorEsp) {
                 if (lexema.equals(es)) {
                     return true;
            }
        }
        return false;
    }
        //caracteres de comparacion == etc
        private boolean esComparacion(String lexema){
             for(String comp : comparacion){
                 if(lexema.equals(comp)){
                     return true;
                 }
             }
        return false;     
        }
        //caracteres de logica and or not
        private boolean esLogicos(String lexema){
             for (String logica : logicos){
                 if(lexema.equals(logica)){
                     return true;
                 }
            }       
             return false;
        }
        //caracteres de asignacion
        private boolean esAsignacion(String lexema){
            for (String asigna : asignacion){
                if(lexema.equals(asigna)){
                    return true;
                }
            }
             return false;
        }
        //caracteres de palabras reservadas
        private boolean sonPalabrasClaves(String lexema){
            for (String palabra : palabrasClave){
                if(lexema.equals(palabra)){
                    return true;
                }
            }
             return false;
        }
        //booleanas
        private boolean sonBooleanas(String lexema){
            for (String booleana : booleanas){
                if(lexema.equals(booleana))
                    return true;
            }
             return false;
        }
        //caracteres tipo .,; etc
        private boolean sonOtros(char caracter){
            for(String simbolos : otros){
                if(Character.toString(caracter).equals(simbolos)){
                    return true;
                }
            }
             return false;
        }
        
        
        //numeros enteros y decimales
        //entero
        private boolean Entero(String lexema){
            try {
                Integer.parseInt(lexema);
                return true;
            } catch (NumberFormatException e) {
            }
            return false;
        }
        //decimal
        private boolean Decimal(String lexema){
            try {
                Double.parseDouble(lexema);
                return true;
            } catch (NumberFormatException e) {
            }
            return false;
        }
        
        
        //analizador lexico, aca se empezara analizar los datos
    public List<Token> analizadorLexico(){
      List<Token> tokens = new ArrayList<>();//array en donde se guarda todo lo necesario
   

      char caracterActual;
      
    while((caracterActual = obtenerPosicionCaracter()) != '\0'){
        //análisis del caracter especial

        if(caracterActual == '_'){
            //se avanza dos caracteres y se regresa a la posicion inicial con retroceder
            avanzarChar();
            avanzarChar();
            //System.out.println("pepe");
            retroceder();
            String lexema2 = Character.toString(caracterActual);//almacenamos el lexema _
            //cambiamos el caracter actual
            caracterActual=obtenerPosicionCaracter();
            //si es una letra agregamos como un solo token
            if(Character.isLetter(caracterActual)){
               //System.out.println("como esta");
            String lexema = obtenerLexemas();
tokens.add(new Token(lexema2+lexema, "Identificador",filaActual, columnaActual-lexema.length(),"([\\w]|_)+(\\w|\\d)*"));
            //si es un numero agregamos como un solo token           
            }else if(Character.isDigit(caracterActual)){
            String lexema = obtenerNumero();
tokens.add(new Token(lexema2+lexema, "Identificador", filaActual, columnaActual-lexema.length(),"([\\w]|_)+(\\w|\\d)*"));                
            }else if(caracterActual == ' '){//si existe un espacio es un error en el token
            String lexema = Character.toString(caracterActual);   
tokens.add(new Token(lexema2+lexema, "ERROR", filaActual, columnaActual-lexema.length(),"ERROR"));
            }else{
tokens.add(new Token(lexema2, "Identificador", filaActual, columnaActual-lexema2.length(),"([\\w]|_)+(\\w|\\d)*"));                
            }
        //fin de el caracter especial
        //comienzo del comentario
        }else if(caracterActual == '#'){
            String lexema2 = Character.toString(caracterActual);
            avanzarChar();
            while((caracterActual = obtenerPosicionCaracter()) != '\n' 
                && caracterActual != '\0'){
                String lexema = comentario(); //agregamos el comentario al lexema junto con el lexema especial que indica que es un comentario y 
tokens.add(new Token(lexema2+lexema, "Comentario", filaActual, columnaActual-lexema.length()-1,"([\\w]|_)+(\\w|\\d)*"));//default en 1                   
                if(caracterActual == '\n'){
                    saltoLinea();
                }
                avanzarChar();
            }
            avanzarChar();
        //identificamos si es una cadena para tomarlo como un caracter
        }else if(caracterActual =='"'){
            String lexema2 = Character.toString(caracterActual);
            avanzarChar();
            StringBuilder lexema = new StringBuilder();
            boolean cadenaCerrada = false;//boolean para saber si es o no cerrada
            while ((caracterActual = obtenerPosicionCaracter()) != '\0') {
                if (caracterActual == '"') {
            cadenaCerrada = true;
            break; // Romper el bucle al encontrar la comilla de cierre
            }
            lexema.append(caracterActual);
            avanzarChar();
            }
                if(cadenaCerrada==true){
        //cerrro correctamente
tokens.add(new Token(lexema2 + lexema.toString() + lexema2, "Cadena", filaActual, columnaActual - lexema2.length(),"([\\w]|_)+(\\w|\\d)*"));
avanzarChar();
                }else{
        // Agregar el token de cadena sin cierre al ArrayList como error
      
tokens.add(new Token("Error", "ERROR", filaActual, columnaActual - lexema2.length(),"ERROR"));
            avanzarChar();
 
                }
        //identifica si es una letra 
        }else if(Character.isLetter(caracterActual)){
            String lexema = obtenerLexemas(); //metodo para identificar si es lexema o no
            //si es un metodo boleano
             if(sonBooleanas(lexema)){
tokens.add(new Token(lexema, "Booleana", filaActual, columnaActual-lexema.length(),lexema)); 
                //System.out.println("hola aca entra");
            //si es una variable 
            }else if(esVar(lexema)){
               tokens.add(new Token(lexema, "Variable", filaActual, columnaActual-lexema.length(),lexema)); 
                //System.out.println("Variable"); 
                
            //si es un metodo logico
            }else if(esLogicos(lexema)){
tokens.add(new Token(lexema, "Logicos", filaActual, columnaActual-lexema.length(),lexema)); 
                //System.out.println("logicos");


            //las palabras reservadas o claves como def
            }else if(sonPalabrasClaves(lexema)){
tokens.add(new Token(lexema, "Palabra reservada", filaActual, columnaActual-lexema.length(),lexema)); 
            //System.out.println("si entra");
            //si no es ninguna de estas sera conocido como identificador
            }else{
tokens.add(new Token(lexema, "Identificador", filaActual, columnaActual-lexema.length(),"([\\w]|_)+(\\w|\\d)*"));
            }
             //cadena simple
             }else if(caracterActual =='\''){
            String lexema2 = Character.toString(caracterActual);
            avanzarChar();
            StringBuilder lexema = new StringBuilder();
            boolean cadenaCerrada = false;//boolean para saber si es o no cerrada
            while ((caracterActual = obtenerPosicionCaracter()) != '\0') {
                if (caracterActual == '\'') {
            cadenaCerrada = true;
            break; // Romper el bucle al encontrar la comilla de cierre
            }
            lexema.append(caracterActual);
            avanzarChar();
            }
                if(cadenaCerrada==true){
        //cerrro correctamente
tokens.add(new Token(lexema2 + lexema.toString() + lexema2, "Cadena Simple", filaActual, columnaActual - lexema2.length(),"([\\w]|_)+(\\w|\\d)*"));
avanzarChar();
                }else{
        // Agregar el token de cadena sin cierre al ArrayList como error
            
tokens.add(new Token(lexema2 + lexema.toString(), "ERROR", filaActual, columnaActual - lexema2.length(),"ERROR"));
            

                }
        //identifica si es una letra 
        }else if(Character.isLetter(caracterActual)){
            String lexema = obtenerLexemas(); //metodo para identificar si es lexema o no
            //si es un metodo boleano
             if(sonBooleanas(lexema)){
tokens.add(new Token(lexema, "Booleana", filaActual, columnaActual-lexema.length(),lexema)); 
                //System.out.println("hola aca entra");
            //si es un metodo logico
            }else if(esLogicos(lexema)){
tokens.add(new Token(lexema, "Logicos", filaActual, columnaActual-lexema.length(),lexema)); 
                //System.out.println("logicos");
            //las palabras reservadas o claves como def
            }else if(sonPalabrasClaves(lexema)){
tokens.add(new Token(lexema, "Palabra reservada", filaActual, columnaActual-lexema.length(),lexema)); 
               //System.out.println("si entra");               
            //si no es ninguna de estas sera conocido como identificador
            }else{
tokens.add(new Token(lexema, "Identificador", filaActual, columnaActual-lexema.length(),"([\\w]|_)+(\\w|\\d)*"));
            }
             //caracteres numero
             //si son numeros tanto enteros como decimales
        }else if(Character.isDigit(caracterActual)){
            String lexema = obtenerNumero();
            //numero entero
             if(Entero(lexema)){
tokens.add(new Token(lexema, "Numero entero", filaActual, columnaActual-lexema.length(),"([0-9])*"));
            //numero decimal
            }else{
tokens.add(new Token(lexema, "Numero decimal", filaActual, columnaActual-lexema.length(),"([0-9])*[.]([0-9])+"));
            }//fin de la busqueda de numero
             
            //valores no letras ni numeros
            
            //valores aritmetcos 
        }else if(esAritmeticos(Character.toString(caracterActual))){
         if(caracterActual == '/'){//esto servira para reconocer si es divicion simple o doble
            avanzarChar();//los valores artmeticos a sido aprovados
            avanzarChar();
                String lexema2 = Character.toString(caracterActual);  
                //System.out.println("holis"+lexema2);
                retroceder();
                caracterActual=obtenerPosicionCaracter();
                //si identifica que hay un / adelante
            if(caracterActual == '/'){
                String lexema = Character.toString(caracterActual);
tokens.add(new Token(lexema2+lexema, "Aritmetico", filaActual, columnaActual-lexema2.length(),lexema2+lexema));  
avanzarChar();

            }else if(caracterActual == '='){
                String lexema = Character.toString(caracterActual);
tokens.add(new Token(lexema2+lexema, "Asignacion", filaActual, columnaActual-lexema2.length(),lexema2+lexema));  
avanzarChar();                
            }else{//de lo contrario solo tomara un valor
                retroceder();
tokens.add(new Token(lexema2, "Aritmetico", filaActual, columnaActual-lexema2.length(),lexema2));  
avanzarChar();  
            }
            //encotrar si es exponente o es multiplicacion
            }else if(caracterActual=='*'){//se repite lo mismo que cono la division
            avanzarChar();
            avanzarChar();
            String lexema2 = Character.toString(caracterActual);
                //System.out.println("multiplicacion");
                retroceder();
                caracterActual=obtenerPosicionCaracter();
                if(caracterActual == '*'){
                    String lexema = Character.toString(caracterActual);
tokens.add(new Token(lexema2+lexema, "Aritmetico", filaActual, columnaActual-lexema2.length()+1,lexema2+lexema));
avanzarChar();
               }else if(caracterActual == '='){
                 String lexema = Character.toString(caracterActual);
tokens.add(new Token(lexema2+lexema, "Asignacion", filaActual, columnaActual-lexema2.length()+1,lexema2+lexema));
avanzarChar();  
            }else{
                retroceder();
tokens.add(new Token(lexema2, "Aritmetico", filaActual, columnaActual-lexema2.length()+1,lexema2));
avanzarChar();
                }
                
            }else if(caracterActual == '+'){
            avanzarChar();
            avanzarChar();
            String lexema2 = Character.toString(caracterActual);
                //System.out.println("multiplicacion");
                retroceder();
                caracterActual=obtenerPosicionCaracter();
                
               if(caracterActual == '='){
                String lexema = Character.toString(caracterActual);
tokens.add(new Token(lexema2+lexema, "Asignacion", filaActual, columnaActual-lexema2.length()+1,lexema2+lexema));
avanzarChar(); 
               }else{
                    retroceder();
tokens.add(new Token(lexema2, "Asignacion", filaActual, columnaActual-lexema2.length()+1,lexema2));
avanzarChar();
               } 
            }else if (caracterActual == '-'){
avanzarChar();
            avanzarChar();
            String lexema2 = Character.toString(caracterActual);
                //System.out.println("multiplicacion");
                retroceder();
                caracterActual=obtenerPosicionCaracter();
                
               if(caracterActual == '='){
                String lexema = Character.toString(caracterActual);
tokens.add(new Token(lexema2+lexema, "Asignacion", filaActual, columnaActual-lexema2.length()+1,lexema2+lexema));
avanzarChar(); 
               }else{
                    retroceder();
tokens.add(new Token(lexema2, "Asignacion", filaActual, columnaActual-lexema2.length()+1,lexema2));
avanzarChar();
               } 
            }else if (caracterActual == '%') {
              avanzarChar();
            avanzarChar();
            String lexema2 = Character.toString(caracterActual);
                //System.out.println("multiplicacion");
                retroceder();
                caracterActual=obtenerPosicionCaracter();
                
               if(caracterActual == '='){
                String lexema = Character.toString(caracterActual);
tokens.add(new Token(lexema2+lexema, "Asignacion", filaActual, columnaActual-lexema2.length()+1,lexema2+lexema));
avanzarChar(); 
               }else{
                    retroceder();
tokens.add(new Token(lexema2, "Asignacion", filaActual, columnaActual-lexema2.length()+1,lexema2));
avanzarChar();
               }  
               }else if (caracterActual == '!') {
              avanzarChar();
            avanzarChar();
            String lexema2 = Character.toString(caracterActual);
                //System.out.println("multiplicacion");
                retroceder();
                caracterActual=obtenerPosicionCaracter();
                
               if(caracterActual == '='){
                String lexema = Character.toString(caracterActual);
tokens.add(new Token(lexema2+lexema, "Asignacion", filaActual, columnaActual-lexema2.length()+1,lexema2+lexema));
avanzarChar(); 
               }else{
                    retroceder();
tokens.add(new Token(lexema2, "Asignacion", filaActual, columnaActual-lexema2.length()+1,lexema2));
avanzarChar();
               }  
               
               
            //este metodo sera para colocar el resto de los valores aritmeticos
        }else{String lexema = Character.toString(caracterActual);
tokens.add(new Token(lexema, "Aritmetico", filaActual, columnaActual-lexema.length()+1,lexema));            
avanzarChar();
                //System.out.println("entra aritmeticos");
               }
         
         
                //aca estan los de comparacion
        }else if(esComparacion(Character.toString(caracterActual))){
                //se hace lo mismo pero para el menor y todo
                if(caracterActual == '<'){
                avanzarChar();
                avanzarChar();
                
                //System.out.println("hola"); 
                String lexema2 = Character.toString(caracterActual);
                retroceder();
                
                caracterActual = obtenerPosicionCaracter();
                    if(caracterActual == '='){
                        String lexema = Character.toString(caracterActual);
tokens.add(new Token(lexema2+lexema, "Comparación", filaActual, columnaActual-lexema2.length(),lexema2+lexema));  
avanzarChar(); 
                    }else{
tokens.add(new Token(lexema2, "Comparación", filaActual, columnaActual-lexema2.length(),lexema2));  
                   
               }
            }else if(caracterActual == '>'){
               avanzarChar();
               avanzarChar();
              // System.out.println("mayor");
               String lexema2 = Character.toString(caracterActual);
               retroceder();
               caracterActual = obtenerPosicionCaracter();
               if(caracterActual == '='){
               String lexema = Character.toString(caracterActual);
tokens.add(new Token(lexema2+lexema, "Comparación", filaActual, columnaActual-lexema2.length()+1,lexema2+lexema));  
avanzarChar();                    
               }else{
tokens.add(new Token(lexema2, "Comparación", filaActual, columnaActual-lexema2.length()+1,lexema2));  
                 
               }
           }
            //aca verifica asignacion
        }else if(esAsignacion(Character.toString(caracterActual))){
            if(caracterActual == '='){
            avanzarChar();
            avanzarChar();
            String lexema2 = Character.toString(caracterActual);  
                //System.out.println("entra asignacion");
            retroceder();
            caracterActual=obtenerPosicionCaracter();
                if(caracterActual=='='){//valores de doble igual o suma, resta, multi,divi
                String lexema = Character.toString(caracterActual);
tokens.add(new Token(lexema2+lexema, "Asignacion", filaActual, columnaActual-lexema2.length(),lexema2+lexema));  
avanzarChar(); 
                }else{
                retroceder();
tokens.add(new Token(lexema2, "Asignacion", filaActual, columnaActual-lexema2.length(),lexema2));  
avanzarChar();  
                }
            }else{ 
                String lexema = Character.toString(caracterActual);
tokens.add(new Token(lexema, "Asignacion", filaActual, columnaActual-lexema.length(),lexema));            

                //System.out.println("entra asignacion");
                }//termina asignacion
            
            //comienza los valores otros, otros no tiene muchas clases
        }else if(sonOtros(caracterActual)){
            String lexema = Character.toString(caracterActual);
tokens.add(new Token(lexema, "Otros", filaActual, columnaActual-lexema.length(),lexema));
avanzarChar();


        //errores, en este apartado se tratara de conseguir todos los posibles errores y mandarlos a un text
        //especial para que se pueda reconocer
        }else if(caracterActual ==' '){
            String lexema = Character.toString(caracterActual);
avanzarChar();
        }else if(caracterActual=='\n' || caracterActual=='\r'){
            String lexema = Character.toString(caracterActual);
avanzarChar();
        //se sigue manejando errores
        }else{
          
            String lexema = Character.toString(caracterActual);
tokens.add(new Token(lexema, "ERROR", filaActual, columnaActual-lexema.length(),lexema));
avanzarChar();
//System.out.println("Son errores de caracteres no en el alfabeto");



        }
        

        
    }//fin del while
    return tokens;
      
    }

        
        
        
}//fin de la clase
