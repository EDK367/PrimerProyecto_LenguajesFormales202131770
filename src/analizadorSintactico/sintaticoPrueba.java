package analizadorSintactico;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.awt.JobAttributes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import visual.Token;

/**
 *
 * @author denil
 */
public class sintaticoPrueba {

    StringBuilder lex = new StringBuilder();
    private List<Token> tokens;
    private int number;
    private int estado;
    private int fila;
    private int columna;
    boolean mala = false;
    List<Estructura> estrucSintactico = new ArrayList<>();//array para las funciones
    
    String expresionVariables = "[Identificador,operador_Asignacion,Expresion]";
    String expresionInvalida = "No";
    
    String expresion_Ternario = "[Variable,if,condicion]";
    public sintaticoPrueba(List<Token> token) {
        this.tokens = token;
        this.number = 0;
        this.estado = 0;
        this.fila = 0;
        this.columna = 0;
    }

    //aca comienza la parte para buscar los tokens y definir si estan en el lenguaje o fuera de este
    public List<Estructura> buscar() {
        estado = 0;

        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(i).getTipoToken().equals("Identificador") && tokens.get(i).getColumna() == 1) {
                fila = tokens.get(i).getFila();
                number = i;
                variables_Ternario(number, fila);
            } else if (tokens.get(i).getLexema().equals("if")) {
                fila = tokens.get(i).getFila();
                number = i;
                columna = tokens.get(i).getColumna();
                estructuraIf(number, fila, columna);

            } else if (tokens.get(i).getLexema().equals("for")) {
                number = i;
                estructuraFor(number);
            } else if (tokens.get(i).getLexema().equals("while")) {
                number = i;
                estructuraWhile(number);
            } else if (tokens.get(i).getLexema().equals("def")) {
                number = i;
                estructuraFuncion(number);
            } else {
                // System.out.println("Que onda");
            }

        }
        if (estado == 1) {
            JOptionPane.showMessageDialog(null, "ERROR EN IDENTIFICADOR IDENTADO");
        }
        return estrucSintactico;

    }
    
    //estructura base para reconocer variables o ternarios
    public List<Estructura> Ternario_Variables(int number, int fila){
    estado = 0; //estado base donde se comienzad 
        for (int i = number; i < tokens.size(); i++) { //estructura for para leer tokesn
            while(estado != -1 && estado != 22){ //el estado 22 es el estado de aceptacion por lo que se detiene si se llega a este
                switch (estado) {
                    case 0://estado de reconocimiento de identificacion
                        if(tokens.get(number).getTipoToken().equals("Identificador")){
                            estado = 1;
                        }else{
                            estado = -1;
                        }
                        break;
                    case 1:
                        if(asignacion()){
                            estado = 2;
                        }
                        break;
                    default:
                        throw new AssertionError();
                }
  
            }
        }
    
        
        return estrucSintactico;
    }
    
    
    
    
    
    //ESTRUCTURAS PARA RECONOCER LAS DISTINTAS FUNCIONES
    
    //estructura de variables y operador ternario
    //pendiente para arreglar
    public List<Estructura> variables_Ternario(int number, int fila) {
        //estructura para reconocer variables, las variables necesitan estar en la columna 0 pero para ser recocidos
        // y si se llega con un if en la misma fila esta sera tomada como un operador ternario
        estado = 0; //estdo donde comienza
        boolean variableValida = false;
        boolean ternario_valido = false;
        //bloque for que se encargar de ir leyendo token por token par determinar que tipo de codigo es
        for (int i = number; i < tokens.size(); i++) {
            while (estado != -1 && estado != 4 && estado != -2) {
                //System.out.println("SI ENTRA EN EL SWITCH\u001B[38m");
                switch (estado) {
                    case 0://estado 0 analiza columna y fila 
                        if (tokens.get(number).getTipoToken().equals("Identificador")) {
                            // System.out.println("SI ES IDENTIFICADOR");
                            estado = 1;
                            columna = tokens.get(number).getColumna();
                        } else {
                            estado = -1;
estrucSintactico.add(new Estructura(expresionVariables, "Falta un identificador",expresionInvalida, fila, columna));
                        }
                        break;
                    case 1:
                        //  System.out.println("Si entre al estado 1 y su token es " + tokens.get(i).getTipoToken());
                        if (asignacion()) {
                            //  System.out.println("SI ES ASIGNACION");
                            estado = 2;
                        } else if (coma()) {
                            estado = 0;
                        } else {
                            estado = -1;
estrucSintactico.add(new Estructura(expresionVariables, "Falta el token asignacion",expresionInvalida, fila, columna));
                        }
                        break;
                    case 2:
                        // System.out.println("Tipo token nuevo es: " + tokens.get(i).getTipoToken());
                        if (asiganacionHecha()) {
                            //   System.out.println("SI ES OTRO IDENTIFICADOR");
                            estado = 3;
                        } else {
                            estado = -1;
estrucSintactico.add(new Estructura(expresionVariables, "Falta una Expresion",expresionInvalida, fila, columna));                            
                        }
                        break;
                    case 3:
                        if (coma()) {
                            estado = 2;
                        } else if (IF(fila)) {
                            //  System.out.println("SI es un if");   
                            //  System.out.println(tokens.get(i).getFila() + " esta es la fila del if ya otro");
                            estado = 5;
                        } else {
                            estado = 4;
                            variableValida = true;
                        }
                        break;
                    case 5:
                        if (isNot()) {
                            // System.out.println("Es un not");
                            estado = 6;
                        } else if (condicion()) {
                            System.out.println("Es un identi");
                            estado = 7;
                        } else {
estrucSintactico.add(new Estructura(expresion_Ternario, "Falta una condicion U operador Logico",expresionInvalida, fila, columna));                                                        
                            estado = -2;
                        }
                        break;
                    case 6:
                        if (condicion2()) {
                            estado = 7;
                        System.out.println("si esta aca");
                        } else {
                            estado = -2;
estrucSintactico.add(new Estructura(expresion_Ternario, "Falta una condicion despues del operador Logico",expresionInvalida, fila, columna));                    
                        }
                        break;

                    case 7:
                        if (elseTernario(fila)) {
                            System.out.println("Si llega a el estado 7");
                            estado = 8;
                        } else {
                            estado = -2;
estrucSintactico.add(new Estructura(expresion_Ternario, "Falta un else",expresionInvalida, fila, columna));                            
                        }
                        break;
                    case 8:
                        if (condicion2()) {
                            estado = 4;
                            ternario_valido = true;
                            System.out.println("si es una condicion ");
                        } else {
                            estado = -2;
estrucSintactico.add(new Estructura(expresion_Ternario, "Falta una condicion despues del else",expresionInvalida, fila, columna));                            
                        }
                        break;
                    default:
                        throw new AssertionError();
                }
            }//fin del while
        }//fin del for
        if (variableValida == true) {
            JOptionPane.showMessageDialog(null, "Variable Valida");
        } else if (ternario_valido == true) {
            JOptionPane.showMessageDialog(null, "Operador ternario");

        } else if (estado == -2) {

            JOptionPane.showMessageDialog(null, "NO es un operador ternario " + fila);
        } else {
            JOptionPane.showMessageDialog(null, "No valida " + fila + " " + lex);
        }
        return estrucSintactico;
    }


    //estructura para los if, if-elif-else, if-else
    public List<Estructura> estructuraIf(int number, int fila, int columna) {
        estado = 0;
        boolean if_valido = false;
        //bucle for para poder analizar cada uno de los tokens
        for (int i = number; i < tokens.size(); i++) {
            //System.out.println(tokens.get(i).getTipoToken());
            if (estado == -1) {
                break;
            }
            while (estado != -1 && estado != 5) {
                switch (estado) {
                    case 0://estado 0 el cual reconoce
                        if (tokens.get(number).getLexema().equals("if")) {
                            //  System.out.println("Consigue su primer if");
                            columna = tokens.get(i).getColumna();
                            fila = tokens.get(i).getFila();
                            estado = 1;
                            tokens.remove(i);
                        } else {
                            estado = -1;
                        }
                        break;
                    case 1:
                        if (valorBooleana()) {
                            estado = 2;
                            // System.out.println("Valor booleana aceptada");
                        } else if (condicion()) {
                            //System.out.println("Si cumple aca");
                            if (condicionIFS()) {
                                //  System.out.println("Hola es un gusto");
                                if (condicion2()) {
                                    //      System.out.println("cumple con lo dicho");
                                    estado = 2;
                                    break;
                                } else {
                                    //    System.out.println("no cumple con lo dicho");
                                    estado = -1;
estrucSintactico.add(new Estructura(expresionInvalida, "Falta una condicion", expresionInvalida, fila, columna));
                                }
                            } else {
                                estado = -1;
estrucSintactico.add(new Estructura(expresionInvalida, "Falta una asignacion", expresionInvalida, fila, columna));                                
                            }
                        } else {
                            estado = -1;
estrucSintactico.add(new Estructura(expresionInvalida, "Falta una expresion", expresionInvalida, fila, columna));                            
                        }

                        break;
                    case 2:
                        if (dosPuntos()) {
                            // System.out.println("Si es valido el dos puntos");
                            estado = 6;
                        } else {
                            estado = -1;
estrucSintactico.add(new Estructura(expresionInvalida, "Falta dos Puntos", expresionInvalida, fila, columna));                        
                        }
                        break;
                    case 3:
                        break;
                    case 4:
                        estado = 5;
                        if_valido = true;
                        break;
                    case 6:
                        if (elif(columna, fila)) {
                            //   System.out.println("En lo correcto del esle if ");
                            estado = 7;
                        } else if (Else(columna, fila)) {
                            estado = 10;
                        } else {
                            estado = 4;
                        }
                        break;
                    case 7:
                        if (valorBooleana()) {
                            estado = 8;
                        } else {
                            estado = -1;
                        }
                        break;
                    case 8:
                        if (dosPuntos()) {
                            // System.out.println("Se valida el otro dos puntos");
                            estado = 9;
                        } else {
                            estado = -1;
                        }
                        break;
                    case 9:
                        if (elif(columna, fila)) {
                            estado = 7;
                        } else if (Else(columna, fila)) {
                            estado = 10;
                        } else {
                            estado = 4;
                        }
                        break;
                    case 10:
                        if (dosPuntos()) {
                            estado = 4;
                        } else {
                            estado = -1;
                        }
                        break;
                    default:

                }

            }//fin del while
        }//fin del for
        if (if_valido == true) {
            JOptionPane.showMessageDialog(null, "IF Valido " + fila);
        } else {
            //  JOptionPane.showMessageDialog(null, "No valida " + fila +" "+ lex);
            JOptionPane.showMessageDialog(null, "   IF no es valido   " + fila);
        }
        return estrucSintactico;
    }//fin de estructura de if

    public void estructuraFor(int number) {

        estado = 0;
        boolean validoFor = false;
        for (int i = number; i < tokens.size(); i++) {
            if (estado == -1) {
                break;
            }
            while (estado != -1 && estado != 10) {

                switch (estado) {
                    case 0:
                        if (tokens.get(number).getLexema().equals("for")) {
                            estado = 1;
                            // System.out.println("for validado");
                            fila = tokens.get(i).getFila();
                            columna = tokens.get(i).getColumna();
                        } else {
                            estado = -1;
                        }
                        break;
                    case 1:
                        if (condicion2()) {
                            //   System.out.println("Condicion validada");
                            estado = 2;
                        } else {
                            estado = -1;
                        }
                        break;
                    case 2:
                        if (palabraIn(fila)) {
                            //  System.out.println("Se acepto la palabra in");
                            estado = 3;
                        } else {
                            estado = -1;
                        }
                        break;
                    case 3:
                        if (condicion2()) {
                            estado = 5;
                            //   System.out.println("cumple con la condicion 2 y se va a 5");
                        } else if (range(fila)) {
                            estado = 4;
                        } else {
                            estado = -1;
                        }
                        break;
                    case 4:
                        break;
                    case 5:
                        if (dosPuntos()) {
                            estado = 6;
                            //System.out.println("Si me acepto los dos puntos");
                        } else {
                            estado = -1;
                        }
                        break;
                    case 6:
                        if (isBreack(columna)) {
                            //System.out.println("Hubo un break ");
                            estado = 7;
                        } else {
                            estado = 7;
                            //System.out.println("no hubo bloque");
                        }
                        break;
                    case 7:
                        //System.out.println("LLEga al estado 7");
                        if (elseFor(columna)) {
                            estado = 8;
                            //System.out.println("es un else");
                        } else {
                            //System.out.println("Si esta en el estado de aceptacion");
                            estado = 10;
                            validoFor = true;
                        }
                        break;
                    case 8:
                        if (dosPuntos()) {
                            estado = 9;
                        } else {
                            estado = -1;
                        }
                        break;
                    case 9:
                        estado = 10;
                        validoFor = true;
                        break;
                    case -3:
                        estado = 10;
                        //System.out.println("Si fue mal identado");
                        break;
                    default:
                        throw new AssertionError();
                }
            }

        }//estructura for

        if (validoFor == true) {
            JOptionPane.showMessageDialog(null, "for valido");
        } else {
            JOptionPane.showMessageDialog(null, "Es un for no valido " + fila);
        }
    }

    public void estructuraWhile(int number) {
        estado = 0;
        boolean validoWhile = false;
        boolean malIdentado = false;
        for (int i = number; i < tokens.size(); i++) {
            if (estado == -1) {
                break;
            }
            while (estado != -1 && estado != 5) {
                switch (estado) {
                    case 0:
                        if (tokens.get(number).getLexema().equals("while")) {
                            fila = tokens.get(number).getFila();
                            columna = tokens.get(number).getColumna();
                            estado = 1;
                        } else {
                            estado = -1;
                        }
                        break;
                    case 1:
                        if (condicion2()) {
                            estado = 2;
                        } else if (valorBooleana()) {
                            estado = 4;
                        } else {
                            estado = -1;
                        }
                        break;
                    case 2:
                        if (asignacion()) {
                            estado = 3;
                        } else {
                            estado = -1;
                        }
                        break;
                    case 3:
                        if (condicion2()) {
                            estado = 4;
                        } else {
                            estado = -1;
                        }
                        break;
                    case 4:
                        if (dosPuntos()) {
                            estado = 6;
                        } else {
                            estado = -1;
                        }
                        break;
                    case 6:
                        //("esta en el estado 6");
                        if (fin_DE_Bloque(columna)) {
                            //System.out.println("Se cerro el bloque");
                            estado = 5;
                            validoWhile = true;
                        } else {
                            estado = 7;
                        }
                        break;
                    case 7:
                        if (identacionErronea(columna)) {
                            //System.out.println("si esta mal identado");
                            malIdentado = true;
                            estado = 5;
                        } else {
                            //System.out.println("se va al estado 8");
                            estado = 8;
                        }
                        break;
                    case 8:
                        if (segundaIdentacion(columna)) {
                            estado = 6;
                        } else {
                            estado = 5;
                            //System.out.println("llego hasta el estado 8");
                            validoWhile = true;
                        }

                        break;
                    default:
                        throw new AssertionError();
                }

            }//fin del while
        }//fin del for
        if (validoWhile == true) {
            JOptionPane.showMessageDialog(null, "While correcto" + fila);
        } else if (malIdentado == true) {
            JOptionPane.showMessageDialog(null, "Esta mal identado");
        } else {
            JOptionPane.showMessageDialog(null, "While malo " + fila);
        }
    }

    //estructura para poder definir todo lo necesario de una funcion, esto ya identado
    public void estructuraFuncion(int number) {
        estado = 0;//estado inicial del AFD
        boolean FuncionValida = false;
        boolean malIdentadoFuncion = false;
        for (int i = number; i < tokens.size(); i++) {//bucle for para recorrer codigos 
            if (estado == -1) {
                break;
            }
            while (estado != -1 && estado != 5) {
                switch (estado) {
                    case 0://estado inicial
                        if (tokens.get(number).getLexema().equals("def")) {//detecta la palabra def
                            fila = tokens.get(number).getFila();//fila inicial
                            columna = tokens.get(number).getColumna();//fila de la columna
                            estado = 1;
                            //System.out.println("Si es un def");
                        } else {
                            estado = -1;
                        }
                        break;
                    case 1:
                        if (identificador()) {
                            estado = 2;
                        } else {
                            estado = -1;
estrucSintactico.add(new Estructura("Funcion", "Falta un identificador", "ERROR EN FUNCION", fila, columna));                            
                        }
                        break;
                    case 2:
                        if (parentesisAbierto()) {
                            System.out.println("SI es un parentesis abierto en el estado 2");
                            estado = 3;
                        } else {
                            estado = -1;
estrucSintactico.add(new Estructura("Funcion", "Falta un parentesis", "ERROR EN FUNCION", fila, columna));                            
                        }
                        break;
                    case 3:
                        if (condicion2()) {
                            System.out.println("Estado tres Acepta la condicion");
                            estado = 4;
                        } else if (parentesisCerrado()) {
                            System.out.println("Primer parentesis");
                            estado = 7;
                        } else {
                            System.out.println("NO acepto la cosa");
                            estado = 4;
                        }
                        break;
                    case 4:
                        if (especial()) {
                            System.out.println("Estado 4 tiene la coma");
                            estado = 4;
                        } else if (coma()) {
                            estado = -1;
                            System.out.println("idneti  entro coma ");

                        } else {
                            System.out.println("Tiene que entrar aca");
                            estado = 6;
                        }
                        break;
                    case 6:
                        if (parentesisCerrado()) {
                            System.out.println("Se cierra el parentesis");
                            estado = 7;
                        } else if (parentesisMal()) {
                            estado = 7;
                        } else {
                            estado = -1;
estrucSintactico.add(new Estructura("Funcion", "Falta un parentesis Cerrado", "ERROR EN FUNCION", fila, columna));                            
                        }
                        break;
                    case 7:
                        if (dosPuntos()) {
                            System.out.println("SI tengo dos puntos");
                            estado = 8;
                        } else {
                            estado = -1;
                            System.out.println("no acepto los dos puntos ");
estrucSintactico.add(new Estructura("Funcion", "Faltan dos puntos", "ERROR EN FUNCION", fila, columna));                            
                        }
                        break;
                    case 8:
                        if (fin_DE_Bloque(columna)) {
                            System.out.println("Estado de aceptacion");
                            estado = 5;
                            FuncionValida = true;
                        } else {
                            estado = 9;
                        }
                        break;
                    case 9:
                        if (identacionErronea(columna)) {
                            malIdentadoFuncion = true;
                            estado = 5;
estrucSintactico.add(new Estructura("Funcion", "Funcion Valida con mala identadcion", "ERROR EN FUNCION", fila, columna));                            
                        } else {
                            estado = 10;
                        }
                        break;
                    case 10:
                        if (segundaIdentacion(columna)) {
                            estado = 8;
estrucSintactico.add(new Estructura("Funcion", "Funcion Valida con mala identadcion", "ERROR EN FUNCION", fila, columna)); 
                        } else {
                            System.out.println("Aceptado");
                            estado = 5;
                            FuncionValida = true;
                        }
                        break;
                    default:
                        throw new AssertionError();
                }
            }//fin del while
        }//fin del for 
        if (FuncionValida == true) {
            JOptionPane.showMessageDialog(null, "Funcion valida " + fila);
        } else if (malIdentadoFuncion == true) {
            JOptionPane.showMessageDialog(null, "Esta mal identado " + fila);
        } else {
            JOptionPane.showMessageDialog(null, "Es una funcion erronea " + fila);
        }
    }

    
    
    
    
    
    //estructura basica de las variables
    public boolean identificador() {
        number++;
        if (estado < tokens.size() && tokens.get(estado).getTipoToken().equals("Identificador")) {
            estado++;
            return true;
        }
        return false;
    }

    public boolean isFor() {
        // reconoce si es un identificador en la primera posicion
        if (estado < tokens.size() && tokens.get(estado).getTipoToken().equals("for")) {
            estado++;
            return true;
        }
        return false;
    }

    public boolean asiganacionHecha() {
        // si es un valor valido para una asiganacion
        number++;
        for (int i = number; i < tokens.size(); i++) {
            if ((tokens.get(number).getTipoToken().equals("Numero entero")
                    || tokens.get(number).getTipoToken().equals("Numero decimal")
                    || tokens.get(number).getTipoToken().equals("Cadena")
                    || tokens.get(number).getTipoToken().equals("Booleana")
                    || tokens.get(number).getTipoToken().equals("Identificador"))) {
                estado++;

                return true;
            } else {
            }
        }
        return false;
    }

    private boolean coma() {

        number++;
        for (int i = number; i < tokens.size(); i++) {
            if (estado < tokens.size() && tokens.get(estado).getLexema().equals(",")) {
                estado++;
                System.out.println("Si es una coma");
                return true;
            }
        }
        return false;
    }

    private boolean asignacion() {
        // si es un tipo de token que se este asignado en el apartado
        number++;
        for (int i = number; i < tokens.size(); i++) {

            //System.out.println("El TIPO de token es: \u001B[35m" + tokens.get(i).getTipoToken());
            if (estado < tokens.size() && tokens.get(number).getTipoToken().equals("Asignacion")) {
                estado++;

                return true;
            } else {
            }
        }
        return false;
    }

    private boolean valorBooleana() {
        //identifica si es un token tipo booleana
        //number++;
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getTipoToken().equals("Booleana")) {
                estado++;
                return true;
            }
        }
        return false;
    }

    private boolean isNot() {
        //identifica si es un token tipo booleana
        number++;
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getTipoToken().equals("Logicos")) {
                estado++;
                return true;
            }
        }
        return false;
    }

    private boolean dosPuntos() {
        number++;
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals(":")) {
                estado++;
                return true;
            } else {
                //System.out.println("No es dos puntos");
            }
        }
        return false;
    }

    private boolean IF(int fila) {
        for (int i = number; i < tokens.size(); i++) {
            //  System.out.println(tokens.get(number).getColumna());
            if (tokens.get(number).getLexema().equals("if") && tokens.get(number).getFila() == fila) {
                estado++;
                tokens.remove(i);
                return true;
            } else {
            }
        }
        return false;
    }

    private boolean elif(int columna, int fila) {

        number++;
        for (int i = number; i < tokens.size(); i++) {
            // System.out.println(tokens.get(number).getColumna());
            if (tokens.get(number).getLexema().equals("elif")
                    && tokens.get(number).getColumna() == columna) {
                estado++;
                return true;
            } else {
                // System.out.println("No entra en su columna");
            }
        }
        return false;
    }

    private boolean Else(int columna, int fila) {

        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals("else")
                    && (tokens.get(number).getColumna() == columna)) {
                estado++;
                return true;
            } else {
                //  System.out.println("No entra en su columna else");
            }
        }
        return false;
    }

    public boolean condicion() {
        // si es un valor valido para la condicion if
        for (int i = number; i < tokens.size(); i++) {
            if ((tokens.get(number).getTipoToken().equals("Numero entero")
                    || tokens.get(number).getTipoToken().equals("Numero decimal")
                    || tokens.get(number).getTipoToken().equals("Cadena")
                    || tokens.get(number).getTipoToken().equals("Identificador"))) {
                estado++;
                return true;
            } else {
            }
        }
        return false;
    }

    private boolean condicionIFS() {
        // si es un tipo de token que se este asignado en el apartado
        number++;
        for (int i = number; i < tokens.size(); i++) {
            //System.out.println("El TIPO de token es: \u001B[35m" + tokens.get(i).getTipoToken());
            if (estado < tokens.size() && tokens.get(number).getTipoToken().equals("Asignacion")
                    || tokens.get(number).getTipoToken().equals("Comparación")) {
                estado++;
                return true;
            } else {
            }
        }
        return false;
    }

    public boolean condicion2() {
        // si es un valor valido para la condicion if 2
        number++;
        for (int i = number; i < tokens.size(); i++) {
            if ((tokens.get(number).getTipoToken().equals("Numero entero")
                    || tokens.get(number).getTipoToken().equals("Numero decimal")
                    || tokens.get(number).getTipoToken().equals("Cadena")
                    || tokens.get(number).getTipoToken().equals("Identificador"))) {
                estado++;
                return true;
            } else {
            }
        }
        return false;
    }

    private boolean elseTernario(int fila) {
        number++;
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals("else")
                    && (tokens.get(number).getFila() == fila)) {
                estado++;
                return true;
            } else {
                // System.out.println("No entra en su columna else");
            }
        }
        return false;
    }

    private boolean palabraIn(int fila) {
        number++;
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals("in")
                    && (tokens.get(number).getFila() == fila)) {
                estado++;
                return true;
            } else {
                // System.out.println("No entra en su columna else");
            }
        }
        return false;
    }

    private boolean range(int fila) {
        number++;
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals("range")
                    && (tokens.get(number).getFila() == fila)) {
                estado++;
                return true;
            } else {
                // System.out.println("No entra en su columna else");
            }
        }
        return false;
    }

    private boolean bloqueFor(int columna) {
        number++;
        //System.out.println("la columna es "+columna);
        int colum = 0;
        colum = 4 + columna;
        for (int i = number; i < tokens.size(); i++) {

            //System.out.println(tokens.get(i).getColumna());
            //System.out.println(colum + " colum vale");
            if ((tokens.get(number).getTipoToken().equals("Identificador")
                    || tokens.get(number).getTipoToken().equals("Palabra reservada")
                    || tokens.get(number).getTipoToken().equals("Cadena")
                    || tokens.get(number).getTipoToken().equals("Numero entero")
                    || tokens.get(number).getTipoToken().equals("Booleano"))
                    && tokens.get(number).getColumna() == colum) {
                estado++;

                return true;
            } else if ((tokens.get(number).getTipoToken().equals("Identificador")
                    || tokens.get(number).getTipoToken().equals("Palabra reservada")
                    || tokens.get(number).getTipoToken().equals("Cadena")
                    || tokens.get(number).getTipoToken().equals("Numero entero")
                    || tokens.get(number).getTipoToken().equals("Booleano"))
                    && tokens.get(number).getColumna() != colum) {
                mala = true;
                return mala;

            } else {
                //System.out.println("No entra en su columna identada");
                return false;
            }
        }

        return false;
    }

    private boolean isBreack(int columna) {
        number++;
        for (int i = number; i < tokens.size(); i++) {
            if ((tokens.get(number).getLexema().equals("break")
                    && tokens.get(number).getColumna() == columna + 4)) {
                estado++;
                return true;
            } else {
                // System.out.println("No entra en su columna else");
            }
        }

        return false;
    }

    private boolean finBloque(int columna) {
        number++;
        for (int i = number; i < tokens.size(); i++) {
            //System.out.println(columna + "Esta columna es la del fin de bloque");
            if ((tokens.get(number).getTipoToken().equals("Identificador")
                    || tokens.get(number).getTipoToken().equals("Palabra Clave")
                    || tokens.get(number).getTipoToken().equals("Cadena")
                    || tokens.get(number).getTipoToken().equals("Numero entero")
                    || tokens.get(number).getTipoToken().equals("Booleano"))
                    && tokens.get(number).getColumna() == columna) {
                estado++;
                return true;
            } else {
                // System.out.println("No entra en su columna else");
            }
        }

        return false;
    }

    private boolean elseFor(int columna) {
        //System.out.println("esta en else for");
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals("else")
                    && (tokens.get(number).getColumna() == columna)) {
                estado++;
                //System.out.println("esle es correcto");
                return true;
            } else {
                //System.out.println("No entra en su columna else");
            }
        }
        return false;
    }

    private boolean isWhile(int columna) {
        for (int i = number; i < tokens.size(); i++) {
            //  System.out.println(tokens.get(number).getColumna());
            if (tokens.get(number).getLexema().equals("while")) {
                estado++;

                return true;
            } else {
            }
        }
        return false;
    }

    private boolean bloqueWhile(int columna) {
        number++;
        //System.out.println("Columna es "+  columna);
        int colum = 0;
        colum = 4 + columna;
        for (int i = number; i < tokens.size(); i++) {
            //  System.out.println(tokens.get(number).getColumna());
            if ((tokens.get(number).getTipoToken().equals("Identificador")
                    || tokens.get(number).getTipoToken().equals("Palabra Clave")
                    || tokens.get(number).getTipoToken().equals("Error")
                    || tokens.get(number).getTipoToken().equals("Cadena")
                    || tokens.get(number).getTipoToken().equals("Booleana")
                    || tokens.get(number).getTipoToken().equals("Numero entero"))) {
                if (tokens.get(i).getColumna() == colum) {
                    estado++;
                    //System.out.println("Si funciona el identado");
                    return true;
                } else {
                    //System.out.println("OTRO FALSE");
                    return false;
                }

            } else {
                //System.out.println("se quedo en el else");

            }
        }
        return false;
    }

    private boolean identacionErronea(int columna) {

        //System.out.println("Columna es "+  columna);
        int colum = 0;
        colum = 4 + columna;
        for (int i = number; i < tokens.size(); i++) {
            //  System.out.println(tokens.get(number).getColumna());
            if ((tokens.get(number).getTipoToken().equals("Identificador")
                    || tokens.get(number).getTipoToken().equals("Palabra Clave")
                    || tokens.get(number).getTipoToken().equals("Error")
                    || tokens.get(number).getTipoToken().equals("Cadena")
                    || tokens.get(number).getTipoToken().equals("Booleana")
                    || tokens.get(number).getTipoToken().equals("Numero entero"))
                    && (tokens.get(number).getColumna() > 1 && tokens.get(number).getColumna() != colum)) {
                estado++;
                return true;

            } else {
                //System.out.println("se quedo en el else");

            }
        }
        return false;
    }

    private boolean segundaIdentacion(int columna) {
        number++;
        //System.out.println("Columna es "+  columna);
        int colum = 0;
        colum = 4 + columna;
        for (int i = number; i < tokens.size(); i++) {
            //  System.out.println(tokens.get(number).getColumna());
            if ((tokens.get(number).getTipoToken().equals("Identificador")
                    || tokens.get(number).getTipoToken().equals("Palabra Clave")
                    || tokens.get(number).getTipoToken().equals("Error")
                    || tokens.get(number).getTipoToken().equals("Cadena")
                    || tokens.get(number).getTipoToken().equals("Booleana")
                    || tokens.get(number).getTipoToken().equals("Numero entero"))
                    && (tokens.get(number).getColumna() > 1 && tokens.get(number).getColumna() != colum)) {
                estado++;
                return true;

            } else {
                //System.out.println("se quedo en el else");

            }
        }
        return false;
    }

    private boolean fin_DE_Bloque(int columna) {
        number++;
        // System.out.println("Columna es "+  columna);

        for (int i = number; i < tokens.size(); i++) {
            //  System.out.println(tokens.get(number).getColumna());
            if ((tokens.get(number).getTipoToken().equals("Identificador")
                    || tokens.get(number).getTipoToken().equals("Palabra Clave")
                    || tokens.get(number).getTipoToken().equals("Error")
                    || tokens.get(number).getTipoToken().equals("Cadena")
                    || tokens.get(number).getTipoToken().equals("Booleana")
                    || tokens.get(number).getTipoToken().equals("Numero entero"))
                    && tokens.get(number).getColumna() == columna) {
                estado++;
                // System.out.println("ya no es parte del bloque");
                return true;
            } else {
                // System.out.println("no entro");

            }
        }
        return false;
    }

    private boolean nombreFuncion() {
        number++;
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getTipoToken().equals("Identificador")) {
                estado++;
                System.out.println("nombre de la funcion en correcto estado" + estado);
                return true;
            }
        }

        return false;
    }

    private boolean parentesisAbierto() {
        number++;
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals("(")) {
                estado++;
                System.out.println("si es un parentesis abierto");
                return true;
            }
        }
        return false;
    }

    private boolean parentesisCerrado() {

        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals(")")) {
                estado++;
                System.out.println("si es un parentesis cerrado ");
                return true;
            }
        }
        return false;
    }

    private boolean parentesisMal() {
        number++;
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals(")")) {
                estado++;
                System.out.println("si es un parentesis cerrado ");
                return true;
            }
        }
        return false;
    }

    private boolean especial() {

        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals(",")) {
                i++;
                if (tokens.get(i).getTipoToken().equals("Identificador")) {
                    return true;
                }

            }
        }
        return false;
    }

    
    
}//fin de la clase