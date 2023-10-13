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
    private List<Token> tokens;
    private int number;
    private int estado;
    private int fila;
    private int columna;
    List<Estructura> estrucSintactico = new ArrayList<>();//array para las funciones
    String errorSIn = "Ocurrio Un error Sintactico";
    String var = "";
    String name= "";
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
                number = i;
                Ternario_Variables(number);
            } else if (tokens.get(i).getLexema().equals("if")) {
                fila = tokens.get(i).getFila();
                number = i;
                columna = tokens.get(i).getColumna();
                estructur_if_elif_else(number, fila, columna);
            } else if (tokens.get(i).getLexema().equals("for")) {
                number = i;
                estructuraFor(number);
            } else if (tokens.get(i).getLexema().equals("while")) {
                number = i;
                estructuraWhile(number);
            } else if (tokens.get(i).getLexema().equals("def")) {
                number = i;
                estructuraFunciones(number);
            }else if (tokens.get(i).getLexema().equals("print")){
                number = i;
                impresionFunciones(number);
            }else{
                
            }

        }
        
        return estrucSintactico;

    }
        
    //ESTRUCTURAS PARA RECONOCER LAS DISTINTAS FUNCIONES
    //EL ESTADO DE ACEPATCION VALIDO ES 22 MIENTRAS QUE EL INVALIDO ES -1 SIENDO EL 0 ESTADO INICIAL
    //estructura base para reconocer variables o ternarios y funciones (Valido)
    public List<Estructura> Ternario_Variables(int number) {
        estado = 0;//estado inicial
        for (int i = number; i < tokens.size(); i++) {//bucle para recorrer todos los tokens
            while(estado != -1 && estado != 22){
                switch (estado) {
                    case 0://estado inicial del automata el cual obtiene los datos necesarios para identificar variables u operadores 
                        if(tokens.get(number).getTipoToken().equals("Identificador")){
                            var = tokens.get(number).getLexema();
                            fila = tokens.get(number).getFila();
                            columna = tokens.get(number).getColumna();
                            estado = 1;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Variable", "Error Variable", "ERROR Desconocido", fila, columna));
                        }
                        break;
                    case 1://estado el cual identifica si es una asignacion simple o no
                        //System.out.println("estado 1");
                        if(coma()){
                            estado = 2;
                        }else if(parentesisAbiertoEspe()){
                            estado = 36;
                        }else if(asignacion(fila)){
                            estado = 4;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Variable", "Error Variable", "Falta una asignacion o una coma", fila, columna));                            
                        }
                        break;
                    case 2://estado el cual verifica un identificador luego de la coma (asignacion multiple)
                        //System.out.println("estado 2");
                        if(identificadorVariables(fila)){
                            estado = 3; 
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Variable", "Error Variable", "Falta un identificador luego de la coma", fila, columna));                            
                        }
                        break;
                    case 3://estado que vuelve a verificar si es una coma o ya se declara la variable
                        //System.out.println("estado 3");
                        if(coma()){
                            estado = 2;
                        }else if(asignacion(fila)){
                           estado = 4; 
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Variable", "Error Variable", "Falta un valor de asignacion", fila, columna));                            
                        }
                        break;
                    case 4://estado el cual identifica asignacion (number,strign,identi,etc) o array o List
                        //System.out.println("estado 4");
                        if(llaveAbierta()){
                            estado = 5;
                        }else if(corcheteAbiertoEspe()){
                            estado = 10;
                        }else{
                            estado = 14;
                        }
                        break;
                    case 5://estado de array o array List
                        //System.out.println("estado 5");
                        if(corcheteAbierto()){
                            estado = 6;  
                        }else if(llaveCerradaEspe()){
                            estado = 22;
estrucSintactico.add(new Estructura("Variable", var, "Variable Valida", "Variable Array", fila, columna));                             
                        }else if(condicion()){
                            estado = 51;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Variable", "Error Variable", "Falta una Llave de cierre", fila, columna));                            
                        }
                        break;
                    case 51://estado extra el cual ayuda a saber si hay elementos en el array
                        if(coma()){
                            estado = 52;
                        }else if(llaveCerradaEspe()){
                            estado = 22;
estrucSintactico.add(new Estructura("Variable", var, "Variable Valida", "Variable Array", fila, columna));                            
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Variable", "Error Variable", "Falta una Llave de cierre", fila, columna));                             
                        }
                        break;
                    case 52://estado extra el cual ayuda a identificar si hay varios elementos en el array
                        if(condicion3(fila)){
                            estado = 51;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Variable", "Error Variable", "Falta una condicion luego de la coma", fila, columna));                            
                        }
                        break;
                    case 50://estado especial extra el cual ayuda a conocer elementos de un array List con elemento
                        //System.out.println("esrtado especial 30");
                        if(corcheteAbierto()){
                            estado = 6;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Variable", "Error Variable", "Falta un Corchete luego de la coma", fila, columna));                            
                        }
                        break;
                    case 6://estado el cual identifica un array List con elementos
                        //System.out.println("estado 6");
                        if(corcheteCerrado()){
                            estado = 9;
                        }else if(condicion()){
                            estado = 7;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Variable", "Error Variable", "Falta un Corchete de cierre", fila, columna));                            
                        }
                        break;
                    case 7://estado para verificar varios elementos de un array List
                        //System.out.println("estado 7");
                        if(coma()){
                            estado = 8;
                        }else if(dosPuntosEspe()){
                            estado = 8;
                        }else if(corcheteCerradoEspe()){
                            estado = 9;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Variable", "Error Variable", "Falta un Corchete de cierre o no se acepto la coma o dos puntos", fila, columna));
                        }
                        break;
                    case 8://estado para encontrar condiciones luego de los dos puntos o coma
                        //System.out.println("estado 8");
                        if(condicion3(fila)){
                            estado = 7;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Variable", "Error Variable", "Falta una condicion luego de la coma", fila, columna));                            
                        }
                        break;
                    case 9://estado para verificar el array List o varios elementos de List
                        //System.out.println("estado 9 ");
                        if(coma()){
                            estado = 50;
                        }else if(llaveCerradaEspe()){
                            estado = 22;
estrucSintactico.add(new Estructura("Variable", var, "Variable Valida", "Variable Array Lista", fila, columna));                            
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Variable", "Error Variable", "Falta una Llave de Cierre", fila, columna));                            
                        }
                        break;
                    case 10://estado para identificar una lista
                        //System.out.println("estado 10");
                        if(condicion3(fila)){  
                            estado = 11;
                        }else if(corcheteCerradoEspe()){
                            estado = 22;
estrucSintactico.add(new Estructura("Variable", var, "Variable Valida", "Variable Lista", fila, columna)); 
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Variable", "Error Variable", "Falta un Corchete de Cierre", fila, columna));                            
                        }
                        break;
                    case 11://estado de aceptacion para verificar si la lista esta vacia o no 
                        //System.out.println("estado 11");
                        if(corcheteCerrado()){
                            estado = 22;
estrucSintactico.add(new Estructura("Variable", var, "Variable Valida", "Variable Lista", fila, columna)); 
                        }else{
                            estado = 12;                            
                        }
                        break;
                    case 12://estado que encuentra varios elementos de una List
                        //System.out.println("estado 12");
                        if(dosPuntosEspe()){
                            estado = 13;
                        }else if(comaEspe()){
                            estado = 13;
                        }else{
                        estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Variable", "Error Variable", "Falta un Corchete de Cierre", fila, columna));                            
                        }
                        break;
                    case 13://estado para seguir encontrando varios elementos de la list luego de la coma o dos puntos
                        //System.out.println("estado 13");
                        if(condicion3(fila)){
                            estado = 11;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Variable", "Error Variable", "Falta un condicion luego de la coma o dos puntos", fila, columna));                            
                        }
                        break;
                    case 14://estado el cual no es ni un array o una lista por lo que busca otro tipo de valor 
                        //variable,entero,decimal,booleana,cadena o lo toma como incorrecta
                        //System.out.println("estado 14");
                        if(var(fila)){
                            estado = 15;
                        }else if(int_(fila)){
                            estado = 17;
                        }else if(float_(fila)){
                            estado = 19;
                        }else if(booleanas(fila)){
                            estado = 21;
                        }else if(Strings_(fila)){
                            estado = 23;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Variable", "Error Variable", "Falta una condicion luego de la asignacion", fila, columna));                            
                        }
                        break;
                        //el estado 26  es para poder encontrar el valor de los operadores ternarios
                    case 15://estado de variable para identificar solo identificadores
                        //System.out.println("estado 15");
                        if(coma()){
                            estado = 16;
                        }else if(IF_Ternario(fila)){
                            estado = 26;                           
                        }else if(condicionERROR(fila)){
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Variable", "Error Variable", "Variable Mal Asignada", fila, columna));
                        }else{
                            estado = 22;
estrucSintactico.add(new Estructura("Variable", var, "Variable Valida", "Variable Asignada", fila, columna));                            
                        }
                        break;
                    case 16://estado que verifica varias condiciones variables
                        //System.out.println("estado 16");
                        if(var_Espe(fila)){
                            estado = 15;
                        }else if(identificadorVariables(fila)){
                            estado = 15;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Variable", "Error Variable", "Falta una Condicion identificador luego de la coma", fila, columna));
                        }
                        break;
                    case 17://estado para verificar solo numeros enteros
                        if(coma()){
                            estado = 18;
                        }else if(IF_Ternario(fila)){
                            estado = 26;
                        }else if(condicionERROR(fila)){
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Variable", "Error Variable", "Variable Mal Asignada", fila, columna));
                        }else{
                            estado = 22;
estrucSintactico.add(new Estructura("Variable", var, "Variable Valida", "Variable Int", fila, columna));                            
                        }
                        break;
                    case 18://estado que verifica varios numeros enteros
                        if(int_Espe(fila)){
                            estado = 17;
                        }else if(identificadorVariables(fila)){
                            estado = 17;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Variable", "Error Variable", "Falta un numero entero luego de la coma", fila, columna));
                        }
                        break;
                    case 19://estado para verificar numeros decimales
                        if(coma()){
                            estado = 20;
                        }else if(IF_Ternario(fila)){
                            estado = 26;
                        }else if(condicionERROR(fila)){
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Variable", "Error Variable", "Variable Mal Asignada", fila, columna));                                                        
                        }else{
                            estado = 22;
estrucSintactico.add(new Estructura("Variable", var, "Variable Valida", "Variable Float", fila, columna));
                        }
                        break;
                    case 20://estado para verificar si existen varios numeros decimales
                        if(float_Espe(fila)){
                            estado = 19;
                        }else if(identificadorVariables(fila)){
                            estado = 19;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Variable", "Error Variable", "Falta un numero decimal luego de la coma", fila, columna));
                        }
                        break;
                    case 21://estado unico el cual solo verifica un valor booleano
                        if(IF_Ternario(fila)){
                            estado = 26;
                        }else if(condicionERROREspe(fila)){
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Variable", "Error Variable", "Variable Mal Asignada", fila, columna));
                        }else{
                            estado = 22;
estrucSintactico.add(new Estructura("Variable", var, "Variable Valida", "Variable Booleana", fila, columna));
                        }
                        break;
                    case 23://estado para verificar cadenas o cadenas simples
                        if(coma()){
                            estado = 24;
                        }else if(IF_Ternario(fila)){
                            estado = 26;
                        }else if(condicionERROR(fila)){
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Variable", "Error Variable", "Variable Mal Asignada", fila, columna));
                        }else{
                            estado = 22;
estrucSintactico.add(new Estructura("Variable", var, "Variable Valida", "Variable Cadena", fila, columna));
                        }
                        break;
                    case 24://estado para verificar varias cadenas o cadenas simples
                        if(Strings_Espe(fila)){
                            estado = 23;
                        }else if(identificadorVariables(fila)){
                            estado = 23;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Variable", "Error Variable", "Falta una Cadena luego de la coma", fila, columna));
                        }
                        break;
                        //aca se empieza para valores ternarios
                    case 26://estado que si se recibe un if luego de una asignacion correcta entra en operadores ternarios
                        //si es un valor logico o una condicion valida del operador ternario
                        //System.out.println("estado 26 Ternario");
                        if(isLogic()){
                            estado = 27;
                        }else if(condicion()){
                            estado = 29;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Operador Ternario", "Error Ternario", "Falta una condicon luego del IF", fila, columna));
                        }
                        break;
                    case 27://estado el cual luego de recibir un operador logico emplea una condicion
                        //System.out.println("estado 27");
                        if(condicion3(fila)){
                            estado = 28;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Operador Ternario", "Error Ternario", "Falta una condicon luego del operador logico", fila, columna));                            
                        }
                        break;
                    case 28://estado que verifica si hay varios operadores logicos lo correspondiente en java a || o && o si es un else
                        //System.out.println("estado 28");
                        if(isLogic()){
                            estado = 27;
                        }else if(elseTernario(fila)){
                            estado = 35;
                        }else if(elseTernarioEspe(fila)){
                            estado = 35;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Operador Ternario", "Error Ternario", "Falta la palabra else", fila, columna));                            
                        }
                        break;
                    case 29://operador de si recibe una condicion luego del if para asi pasar a un else o una comparativa
                        //System.out.println("estado 29");
                        if(asignacionIFS()){
                            estado = 30;
                        }else if(elseTernario(fila)){
                            estado = 35;
                        }else{
                            estado = 31;
                        }
                        break;
                    case 30://condicion luego del token que comapara
                        //System.out.println("estado 30");
                        if(condicion3(fila)){
                            estado = 31;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Operador Ternario", "Error Ternario", "Falta una condicion de validacion en el If", fila, columna));                            
                        }
                        break;
                    case 31://si son varias condiciones comparativas o ya es un else
                        //System.out.println("estado 31");
                        if(EspeLogic()){
                           estado = 32; 
                        }else if(elseTernarioEspe(fila)){
                            estado = 35;
                        }else if(elseTernario(fila)){
                            estado = 35;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Operador Ternario", "Error Ternario", "Falta la palabra else", fila, columna));                            
                        }
                        break;
                    case 32://estado para seguir verificando mas comparativas luego de un operador logico 
                        //System.out.println("estado 32");
                        if(condicion3(fila)){
                            estado = 33;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Operador Ternario", "Error Ternario", "Falta una condicion luego del operador logico", fila, columna));                                                        
                        }
                        break;
                    case 33://estado para hacer una comparativa valor de else
                        //System.out.println("estado 33");
                        if(asignacionIFS()){
                            estado = 34;
                        }else if(elseTernarioEspe(fila)){
                            estado = 35;
                        }else if(elseTernario(fila)){
                            estado = 35;
                        }else{
estrucSintactico.add(new Estructura(errorSIn, "Operador Ternario", "Error Ternario", "Falta la palabra else", fila, columna));                                                                                    
                            estado = -1;
                        }
                        break;
                    case 34://uultimo estado para verificar una condicion 
                        //System.out.println("estado 34");
                        if(condicion3(fila)){
                            estado = 31;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Operador Ternario", "Error Ternario", "Falta una condicion Valida luego de la comparacion", fila, columna));
                        }
                        break;
                    case 35://estado luego de recibir un else por lo que pide una condicion
                        if(condicion3(fila)){
                            estado = 25; 
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Operador Ternario", "Error Ternario", "Falta una condicion Valida luego del else", fila, columna));                            
                        }
                        break;
                    case 25://estado de aceptacion para los operadores ternarios
                        estado = 22;
estrucSintactico.add(new Estructura("Operador Ternario", "Operador Ternario", "Operador Ternario Valido", "", fila, columna));                        
                        break;
                        //aca se empieza las llamadas de funciones
                    case 36:
                        if(parentesisMal()){
                            estado = 47;
                        }else if(condicion()){
                            estado = 37;
                        }else{
                            estado = 101;
                        }
                        break;
                    case 101:
                        if(corcheteAbierto()){
                            estado = 42;
                        }else if(corcheteAbiertoEspe()){
                            estado = 42;
                        }else{
                            estado = 102;
                        }
                        break;
                    case 102:
                        if(llaveAbierta()){
                            estado = 39;
                        }else if(parentesisCerrado()){
                            estado = 47;
                        }else{
                            estado = 103;
                        }
                        break;
                    case 103:
                        if(condicion()){
                            estado = 37;
                        }else if(condicion3(fila)){
                            estado = 37;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, var, "Error en llamada", "Falta un parentesis de Cierre", fila, columna));                        
                        }
                        break;
                    case 37:
                        //System.out.println("estado 37");
                        if(coma()){
                            estado = 38;
                        }else if(parentesisCerrado()){
                            estado = 47;
                        }else if(parentesisMal()){
                            estado = 47;
                        }else{
                           estado = -1;
estrucSintactico.add(new Estructura(errorSIn, var, "Error en llamada", "Faltaa un parentesis de Cierre", fila, columna));                        
                        }
                        break;
                    case 38:
                        //System.out.println("38");
                        if(condicion3(fila)){
                            estado = 37;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, var, "Error en Llamada de Funcion", "Falta un valor de llamada", fila, columna));                            
                        }
                        break;
                    case 39:
                        //System.out.println("39");
                        if(condicion3(fila)){
                            estado = 40;
                        }else if(llaveCerradaEspe()){
                            estado = 100;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, var, "Error en Llamada de Funcion", "Falta una Llave de cierre", fila, columna));    
                        }
                        break;
                    case 100:
                        //System.out.println("estado 100");
                        if(parentesisCerrado()){
                            estado = 47;
                        }else if(parentesisMal()){
                            estado = 47;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, var, "Error en Llamada de Funcion", "Falta un parentesis de Cierre", fila, columna));                            
                        }
                        break;
                    case 40:
                        //System.out.println("40");
                        if(coma()){
                            estado = 41;
                        }else if(llaveCerradaEspe()){
                            estado = 100;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, var, "Error en Llamada de Funcion", "Falta una Llave de cierre", fila, columna));                            
                        }
                        break;
                    case 41:
                        //System.out.println("41");
                        if(condicion3(fila)){
                           estado = 40; 
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, var, "Error en Llamada de Funcion", "Falta una condicion luego de la coma", fila, columna));
                        }
                        break;
                    case 42:
                        //System.out.println("42");
                        if(condicion()){
                            estado = 43;
                        }else if(corcheteCerrado()){
                            estado = 45;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, var, "Error en Llamada de Funcion", "Falta una Corchete de cierre", fila, columna));                            
                        }
                        break;
                    case 43:
                        //System.out.println("43");
                        if(coma()){
                            estado = 44;
                        }else if(corcheteCerrado()){
                            estado = 45;
                        }else{
                        estado = -1;
estrucSintactico.add(new Estructura(errorSIn, var, "Error en Llamada de Funcion", "Falta un Corchete de Cierre", fila, columna));                            
                        }
                        break;
                    case 44:
                        //System.out.println("44");
                        if(condicion3(fila)){
                            estado = 43;
                        }else{
                           estado = -1;
estrucSintactico.add(new Estructura(errorSIn, var, "Error en Llamada de Funcion", "Falta una condicion luego de la coma", fila, columna));                            
                        }
                        break;
                    case 45:
                        //System.out.println("45");
                        if(coma()){
                            estado = 46;
                        }else if(dosPuntos()){
                            estado = 46;
                        }else if(dosPuntosEspe()){
                            estado = 46;
                        }else if(parentesisCerrado()){
                            estado = 47;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, var, "Error en Llamada de Funcion", "Falta un Parentesis de cierre", fila, columna));                            
                        }
                        break;
                    case 46:
                        //System.out.println("46");
                        if(corcheteAbierto()){
                            estado = 40;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, var, "Error en Llamada de Funcion", "Falta un Corchete de avertura luego de la coma", fila, columna));                            
                        }
                        break;
                    case 47:
                        //System.out.println("47");
                        if(condicionERRORDoble(fila)){
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, var, "Error en Llamada de Funcion", "Funcion con llamado erroneo", fila, columna));                            
                        }else{
                            estado = 22;
estrucSintactico.add(new Estructura("Llamada de Funcion", var, "Llamada de Funcion Valida", "", fila, columna));                                      
                        }
                        break;
                    default:
                        throw new AssertionError();
                }
            }//fin del while
        }//fin del for
        return estrucSintactico;
    }//fin del operador ternrio y variables
    
    //estructura base para reconocer if, if-elif, if-elif-else, if-else (Valido)
    public List<Estructura>  estructur_if_elif_else(int number, int fila, int columna){
        estado = 0; //estado inicial
        for (int i = number; i < tokens.size(); i++) {//bucle for para recorrer los tokens
            while (estado != -1 && estado != 22) {                
                switch (estado) {
                    case 0:
                        if(tokens.get(number).getLexema().equals("if")){//se encuentra el primer if y se obtiene sus atributos
                          columna = tokens.get(i).getColumna();//servira para la identacion
                          fila = tokens.get(i).getFila();//valor de las condiciones
                          estado = 1;
                        }else{
estrucSintactico.add(new Estructura(errorSIn, "IF", "ERROR If","ERROR DESCONOCIDO", fila, columna));
                            estado = -1;
                        }
                        break;
                    case 1://estado uno para reconocer un valor 
                        //System.out.println("LLega al estado 1");
                        if(valorBooleana()){//valor booleana para la primera condicion if siendo evaluada directamente
                            estado = 4;
                        }else if(condicion()){//condicion 1 para el if evaluando una sentencia con operador
                            estado = 2;
                        }else if(isLogic()){//operador logico para la evaluacion
                            estado = 3;
                        }else{
estrucSintactico.add(new Estructura(errorSIn, "IF", "ERROR If","Falta una condicional o valor Booleano", fila, columna));
                        estado = -1;
                        }
                        break;
                    case 2://estado 2 que se encargar de evaluar la asiganacion si exite una condicion en lugar de boleana o logico
                        //System.out.println("estado 2");
                        if(asignacionIFS()){//agrega el valor de asignacion
                            estado = 3;
                        }else if(isLogic()){
                            estado = 3;
                        }else if(dosPuntosEspe()){//puede ser un valor boleano por metodo de identificador
                            estado = 15;
                        }else if(dosPuntos()){
                            estado = 15;
                        }else{
estrucSintactico.add(new Estructura(errorSIn, "IF", "ERROR If","Falta una condicion o dos puntos", fila, columna)); 
                            estado = -1;
                        }
                        break;
                    case  3://estado 3 si se encontro una asigancionIFS
                        //System.out.println("estado 3");
                        if(condicion2()){//si es una condicion valida luego del IFS
                            estado = 4;
                        }else if(condicion()){
                            estado = 4;
                        }else{
estrucSintactico.add(new Estructura(errorSIn, "IF", "ERROR If","Falta un condicional", fila, columna));
                            estado = -1;
                        }
                        break;
                    case 4://estado el cual luego de recibir una condicion valida se encarga de repetir la secuencia o darla por valida
                        //System.out.println("estado 4");
                        if(dosPuntos()){//estructura if valida solo reconocer bloques o elif y else
                            estado = 15;
                        }else if(dosPuntosEspe()){
                            estado = 15;
                        }else if(asignacion(fila)){
                            estado = 1;
                        }else if(isLogic()){//es un if con operador logico es decir en java seraia como a || b
                            estado = 1;
                        }else{
estrucSintactico.add(new Estructura(errorSIn, "IF", "ERROR If","Faltan dos puntos", fila, columna));  
                            estado = -1;
                        }
                        break;
                        //el estado 15 para adelante son identificaciones para saber si son o no son bloques mal identados
                    case 15://estado extra de la estructura la cual servira para identicar bloques o sentencias
                        //System.out.println("esta en el estado 15");
                        if(Elif(columna, fila)){//si es elif se ira para el estado 6 siendo reconocido bloques epsilon o no de if principal
                            estado = 6;
                        }else if(Else(columna, fila)){//si es else se ira para el estado 13 siendo reconocido bloques epsilon o no de if principal
                            estado = 13;
                        }else{
                            estado = 16;
                        }
                        break;
                        
                    case 16://estado 16 para reconocer fin de bloque otorgando un if simple
                        //System.out.println("estado 16");
                        if(fin_DE_Bloque_if(columna)){
                            estado = 5;
                        }else{
                            estado = 17;
                        }
                        break;
                    case 17://declarando un fin simple sin contenido adentro
                        //System.out.println("estado 17");
                        if(finCodigo()){
                            estado = 5;
                        }else{
                          estado = 18;  
                        }
                        break;
                    case 18://verificacion de bloques mal identados
                        //System.out.println("estado 18");
                        if(identacion(columna, number, columna)){
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "IF", "ERROR If","Bloque IF con mala identacion", fila, columna));                            
                        }else{
                            estado = 19;
                        }
                        break;
                    case 19://sigue verificando bloques 
                        //System.out.println("estado 19");
                        if(!segundaIdentacion(columna)){
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "IF", "ERROR If","Bloque IF con mala identacion", fila, columna));                            
                        }else{
                            //System.out.println("se va para el 15");
                            estado = 15;
                        }
                        break;
                        //aca termina la identacion
                    case 5://si el bloque es un if simple
estrucSintactico.add(new Estructura("IF", "IF", "If valido","", fila, columna));
                            estado = 22; 
                        
                        break;
                    case 6://si es un elif 
                        //System.out.println("estado 6");
                        if(valorBooleana()){
                            estado = 9;
                        }else if(condicion()){
                            estado = 7;
                        }else{
estrucSintactico.add(new Estructura(errorSIn, "IF-Elif", "ERROR If-Elif","Falta un condicion Valida", fila, columna)); 
                            estado = -1;
                        }
                        break;
                    case 7://estado en el que se verifica la validez del elif
                        if(asignacionIFS()){
                            estado = 8;
                        }else if(dosPuntosEspe()){
                            estado = 9;
                        }else{
estrucSintactico.add(new Estructura(errorSIn, "IF-Elif", "ERROR If-Elif","Falta una condicional o dos puntos", fila, columna));     
                            estado = -1;
                        }
                        break;
                    case 8://si cumple con la condicion luego de la asignacion
                        if(condicion2()){
                            estado = 9;
                        }else{
estrucSintactico.add(new Estructura(errorSIn, "IF-Elif", "ERROR If-Elif","Falta una condicional", fila, columna));     
                            estado = -1;
                        }
                        break;
                    case 9://estado encargado de verificar el elif o de encontrar un elif estilo a || b
                        if(dosPuntosEspe()){
                            estado = 20;
                        }else if(dosPuntos()){
                            estado = 20;
                        }else if(asignacionIFS()){
                            estado = 6;
                        }else if(isLogic()){//vuelve a la sentencia para verificar el a || b
                            estado = 6;
                        }else{
estrucSintactico.add(new Estructura(errorSIn, "IF-Elif", "ERROR If-Elif","Faltan dos Puntos", fila, columna)); 
                            estado = -1;
                        }
                        break;
                        //bloque para los elif
                    case 20://verifica si hay otro elif
                        if(Elif(columna, fila)){
                            estado = 6;
                        }else{
                            estado = 21;
                        }
                        break;
                    case 21://verifica si hay un else
                        if(Else(columna, fila)){
                            estado = 11;
                        }else{
                            estado = 23;
                        }
                        break;
                    case 23://si hay un fin de bloque if 
                        if(fin_DE_Bloque_if(columna)){
                           estado = 10; 
                        }else{
                            estado = 24;
                        }
                        break;
                    case 24://verifica si el elif esta  acio
                        //System.out.println("estado 24");
                        if(finCodigo()){
                            estado = 10;
                        }else{
                            estado = 25;
                        }
                        break;
                    case 25://bloque para identificar mala identacion
                        //System.out.println("estado 25");
                        if(identacion(columna, number, columna)){
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "IF", "ERROR If","Bloque Elif con mala identacion", fila, columna));
                        }else{
                            estado = 26;  
                        }
                        break;
                    case 26://segundo bloque para la mala indentacion
                        if(!segundaIdentacion(columna)){
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "IF", "ERROR If","Bloque Elif con mala identacion", fila, columna));
                        }else{
                            estado = 20;
                        }
                        break;
                        //aca termina para los bloques del if
                    case 10://valida el bloque elif
estrucSintactico.add(new Estructura("IF-Elif", "IF-Elif", "If-Elif Valido","", fila, columna));                            
                            estado = 22;
                        break;
                    case 11://aca llega si es un elif else
                        //System.out.println("estado 11");
                        if(dosPuntos()){
                            estado = 27;
                        }else{
estrucSintactico.add(new Estructura(errorSIn, "IF-Elif-Else", "ERROR If-Elif-Else","Faltan dos Puntos", fila, columna));
                            estado = -1;
                        }
                        break;
                        //estructura para verificar si existe un bloque de codigo
                    case 27://si hay un fin de bloque para elif-else
                        //System.out.println("estado 27");
                        if(fin_DE_Bloque(columna)){
                            estado = 12;
                        }else{
                            estado = 28;
                        }
                        break;
                    case 28://si esta vacio
                        //System.out.println("stado 28");
                        if(finCodigo()){
                            estado = 12;
                        }else{
                            estado = 29;
                        }
                        break;
                    case 29://si el bloque esta mal identado
                        //System.out.println("identacion 29");
                        if(identacion(columna, number, columna)){
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "IF", "ERROR If","Bloque Else con mala identacion", fila, columna));
                        }else{
                            estado = 30;
                        }
                        break;
                    case 30://sigue verificando si el bloque esta mal identado
                        //System.out.println("estado 30");
                        if(!segundaIdentacion(columna)){
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "IF", "ERROR If","Bloque Else con mala identacion", fila, columna)); 
                        }else{
                            estado = 27;
                        }
                        break;
                        //fin de la estructura para bloque de if-elif-else
                    case 12://estado de if-elif-else
                        //System.out.println("llega al esado 12");
estrucSintactico.add(new Estructura("IF-Elif-Else", "IF-Elif-Else", "If-Elif-Else Valido","", fila, columna));
                        estado = 22;
                        break;
                    case 13://si es un if-else
                        if(dosPuntos()){                           
                            estado = 31;
                        }else{
estrucSintactico.add(new Estructura(errorSIn, "IF-Else", "ERROR If-Else","Faltan dos Puntos", fila, columna)); 
                            estado = -1;
                        }
                        break;
                        //estructura para el bloque de if-else
                    case 31://si ya termino el bloque de else
                        //System.out.println("estado 31");
                        if(fin_DE_Bloque(columna)){
                            estado = 14;
                        }else{
                            estado = 32;
                        }
                        break;
                    case 32://si el else esta vacio
                        //System.out.println("estado 31");
                        if(finCodigo()){
                            estado = 14; 
                        }else if(Return(columna)){
                            estado = 14;
                        }else{
                            estado = 33;
                        }
                        break;
                    case 33://si el bloque esta mal identando
                        if(identacion(columna, number, columna)){
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "If-Else", "ERROR If-Else","Bloque Else con mala identacion", fila, columna));
                        }else{
                            estado = 34;
                        }
                        break;
                    case 34://sigue analizanod el bloque para el else
                        if(!segundaIdentacion(columna)){
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "If-Else", "ERROR If-Else","Bloque Else con mala identacion", fila, columna));
                        }else{
                            estado = 31;
                        }
                        break;
                    case 14://estado de aceptacion para el if-else
                        //System.out.println("estado 14");
estrucSintactico.add(new Estructura("IF-Else", "IF-Else", "If-Else Valido","",fila, columna));     
                        estado = 22;
                        break;
                    default:
                        throw new AssertionError();
                }//fin del switch
            }//fin del while
        }//fin del for 
        return estrucSintactico;
    }//fin de la estructura

    //estructura base para poder analizar los bucles for (Valido)
    public List<Estructura> estructuraFor(int number){
        estado = 0; //estado inicial 
        for (int i = number; i < tokens.size(); i++) {//bucle para recorrer los tokens
            while(estado != -1 && estado != 22){
                switch (estado) {
                    case 0://estado inicial y asignacion de valores importantes como es la fila y la columna 
                        if(tokens.get(number).getLexema().equals("for")){
                            columna = tokens.get(number).getColumna();
                            fila = tokens.get(number).getFila();
                            estado = 1;
                        }else{
                          estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "For", "ERROR en For", "ERROR desconocido", fila, columna));
                        }
                        break;
                    case 1://estado el cual buscara una condicion con fila similar a la del for
                        //System.out.println("estado 1");
                        if(condicion3(fila)){
                            estado = 2;
                        }else{
estrucSintactico.add(new Estructura(errorSIn, "For", "ERROR en For", "Falta una condicion", fila, columna));
                            estado = -1;
                        }
                        break;
                    case 2://busca la palabra in para la estructura del for
                        //System.out.println("estado 2");
                        if(palabraIn(fila)){
                            estado = 3;
                        }else{
estrucSintactico.add(new Estructura(errorSIn, "For", "ERROR en For", "Falta el token in", fila, columna));     
                            estado = -1;
                        }
                        break;
                    case 3://estado el cual se encarga de verificar si el for es desde un rango o una condicion
                        //System.out.println("estado 3");
                        if(range(fila)){
                            estado = 4;
                        }else if(condicion()){
                            estado = 10;
                        }else{
estrucSintactico.add(new Estructura(errorSIn, "For", "ERROR en For", "Falta una condicion", fila, columna));    
                            estado = -1;
                        }
                        break;
                    case 4://estado que viene precendente de un for range
                        //System.out.println("estado 4");
                        if(parentesisAbierto()){
                            estado = 5;
                        }else{
estrucSintactico.add(new Estructura(errorSIn, "For", "ERROR en For", "Falta un parentesis", fila, columna));     
                            estado = -1;
                        }
                        break;
                    case 5://primera condiciono obligatoria de un range
                        //System.out.println("estado 5");
                        if(condicion2()){
                            estado = 6;
                        }else{
estrucSintactico.add(new Estructura(errorSIn, "For", "ERROR en For", "Falta una condicion dentro del parentesis", fila, columna));
                            estado = -1;
                        }
                        break;                        
                    case 6://estado el cual verifica una condicion doble o una condicion simple para el range
                        //System.out.println("llega al 6");
                        if(parentesisCerrado()){
                            estado = 10;
                        }else{
                            estado = 7;
                        }
                        break;
                    case 7://si viene una coma indica que no es una condicion simple
                        //System.out.println("estado 7");
                        if(coma()){
                            estado = 8;
                        }else{
                            estado = 9;
                        }
                        break;
                    case 8://luego de la coma viene una condicion
                        //System.out.println("estado 8");
                        if(condicion3(fila)){
                            estado = 6;
                        }else{
estrucSintactico.add(new Estructura(errorSIn, "For", "ERROR en For", "Falta una condicion luego de la coma", fila, columna));    
                           estado = -1;                             
                        }
                        break;
                    case 9://verifica si existe un parentesis luego de las condiciones
                        //System.out.println("llega al 9");
                        if(parentesisCerrado()){
                            estado = 10;
                        }else if(parentesisMal()){
                            estado = 10;
                        }else if(coma()){
                            estado =8;
                        }else{
estrucSintactico.add(new Estructura(errorSIn, "For", "ERROR en For", "Falta un parentesis de cierre", fila, columna));    
                           estado = -1;                             
                        }
                        break;
                    case 10://si ya se cumplio con la estructura simple del for este estado verifica su final con dos puntos
                        //System.out.println("SI esta estado 10");
                        if(dosPuntos()){
                           estado = 11; 
                        }else{
estrucSintactico.add(new Estructura(errorSIn, "For", "ERROR en For", "Faltan dos puntos", fila, columna));    
                           estado = -1; 
                        }
                        break;
                    case 11://estado si verifica si es un for con breack sin contenido
                        //System.out.println("Estado 11");
                        if(isBreack(columna)){ 
                            estado = 21;
                        }else if(Else(columna, fila)){
                            estado = 16;
                        }else{
                            estado = 12;
                        }
                        break;
                    case 12://si termino el for sin breack
                        //System.out.println("estado 14");
                        if(fin_DE_Bloque_if(columna)){
estrucSintactico.add(new Estructura("For", "For", "For Valido", "", fila, columna));                            
                            estado = 22;
                        }else{
                            estado = 13;
                        }
                        break;
                    case 13://si el for esta vacio
                        //System.out.println("estado 13");
                        if(finCodigo()){
estrucSintactico.add(new Estructura("For", "For", "For Valido", "", fila, columna));                            
                            estado = 22; 
                        }else{
                            estado = 14;
                        }
                        break;
                    case 14://bloque que identifica la identacion
                        //System.out.println("estado 14");
                        if(identacion(columna, number, columna)){
estrucSintactico.add(new Estructura(errorSIn, "For", "ERROR en For", "Bloque con mala identacion", fila, columna));;      
                            estado = -1;
                        }else{
                            estado = 15;
                        }
                        break;
                    case 15://segundo bloque de identacion 
                        if(!segundaIdentacion(columna)){
estrucSintactico.add(new Estructura(errorSIn, "For", "ERROR en For", "Bloque con mala identacion", fila, columna));;      
                            estado = -1;
                        }else{
                            estado = 11;
                        }
                        break;
                    case 21://si es un estado con break y else
                        if(ElseBreak(columna, fila)){
                            estado = 23;
                        }else{
estrucSintactico.add(new Estructura("For", "For", "For Valido con Break", "", fila, columna));                            
                            estado = 22;
                        }
                        break;
                    case 23://verifca los dos puntos para un for-else con break
                        if(dosPuntos()){
                            estado = 24;
                        }else if(dosPuntosEspe()){
                            estado = 24;
                        }else{
estrucSintactico.add(new Estructura(errorSIn, "For", "ERROR en For", "Faltan dos puntos en el else", fila, columna));;      
                            estado = -1;                            
                        }
                        break;
                    case 24://encontrar el fin del bloque o si esta vacio el for-else con break
                        if(fin_DE_Bloque(columna)){
                            estado = 27;
                        }else if(finCodigo()){
                            estado = 27;
                        }else{
                            estado = 25;
                        }
                        break;
                    case 25://detectar mala identacion en el for-else con break
                        if(identacion(columna, number, columna)){
estrucSintactico.add(new Estructura(errorSIn, "For", "ERROR en For", "Bloque else con mala identacion", fila, columna));;      
                            estado = -1;                            
                        }else{
                            estado = 26;
                        }
                        break;
                    case 26://detectar mala identaicon el for-else con break
                        if(!segundaIdentacion(columna)){
estrucSintactico.add(new Estructura(errorSIn, "For", "ERROR en For", "Bloque else con mala identacion", fila, columna));;      
                            estado = -1;                            
                        }else{
                            estado = 24;
                        }
                        break;
                    case 27://estao de aceptacion de un for-else con break
estrucSintactico.add(new Estructura("For", "For", "For-Else con break Valido", "", fila, columna));;      
                            estado = 22;                         
                        break;
                    case 16://estado el cual recibe un for-else
                        if(dosPuntos()){
                            estado = 17;
                        }else if(dosPuntosEspe()){
                            estado = 17;
                        }else{
estrucSintactico.add(new Estructura(errorSIn, "For", "ERROR en For", "Faltan dos puntos en el else", fila, columna));;      
                            estado = -1;                            
                        }
                        break;
                    case 17://si es un for vacio o si ya se cerro el bloque
                        if(fin_DE_Bloque(columna)){
                            estado = 20;
                        }else if(finCodigo()){
                            estado = 20;
                        }else{
                            estado = 18;
                        }
                        break;
                    case 18://detecta si el bloque tiene mala identacion
                        if(identacion(columna, number, columna)){
estrucSintactico.add(new Estructura(errorSIn, "For", "ERROR en For", "Bloque else con mala identacion", fila, columna));;      
                            estado = -1;                            
                        }else{
                            estado = 19;
                        }
                        break;
                    case 19://segundo metodo para detectar si tiene mala identacion
                        if(!segundaIdentacion(columna)){
estrucSintactico.add(new Estructura(errorSIn, "For", "ERROR en For", "Bloque else con mala identacion", fila, columna));;      
                            estado = -1;                            
                        }else{
                            estado = 17;
                        }
                        break;
                    case 20://estado de aceptacion para un for-else
estrucSintactico.add(new Estructura("For", "For", "For-Else Valido", "", fila, columna));;      
                            estado = 22;                        
                        break;
                    default:
                        throw new AssertionError();
                }
            }//fin del while
        }//fin del for 
        return estrucSintactico;
    }//fin de la clase de estructuraFor
    
    //estructura base para poder analizar los bucles while (Valido)
    public List<Estructura> estructuraWhile(int number){
       estado = 0;//estado inicial
       for (int i = number; i < tokens.size(); i++) {//bucle for para recorrer todos los tokens
           while(estado != -1 && estado != 22){
               switch (estado) {
                   case 0://estado inicial y sus respectivas asignaciones importantes
                       if(tokens.get(number).getLexema().equals("while")){
                            fila = tokens.get(number).getFila();
                            columna = tokens.get(number).getColumna();
                            estado = 1;
                       }else{
estrucSintactico.add(new Estructura(errorSIn, "While", "ERROR While", "ERROR desconocido", fila, columna));
                            estado = -1;
                       }
                       break;
                   case 1://estado que reconoce una primera condicion
                       if(condicion2()){
                            estado = 3;
                       }else{
                            estado = 2;
                       }
                       break;
                   case 2://o un posible valor booleano
                       if(valorBooleana()){
                            estado = 5;
                       }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "While", "ERROR While", "Falta una Condicion Valida", fila, columna));                                                
                       }
                       break;
                   case 3://luego de tener una condicion valida se encarga de obtener una asignacion valida o tomarla como condicion unica
                       if(asignacionIFS()){
                            estado = 4;
                       }else if(isLogic()){
                            estado = 1;
                       }else if(dosPuntosEspe()){
                            estado = 6;
                       }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "While", "ERROR While", "Falta una asignacion Valida", fila, columna));   
                       }
                       break;
                   case 4://luego de la asignacion puede ver una condicion
                       if(condicion2()){
                            estado = 5;
                       }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "While", "ERROR While", "Falta una Condicion Valida", fila, columna));    
                       }
                       break;
                   case 5://si el estado son dos puntos o un valor logico
                        //System.out.println("estado 5");
                       if(dosPuntos()){
                            estado = 6;
                       }else if(isLogic()){
                            estado = 1;
                       }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "While", "ERROR While", "Faltan Dos Puntos", fila, columna));   
                       }
                       break;
                   case 6://bloque encargado de buscar si existe un fin de bloque o un fin de codigo 
                       if(fin_DE_Bloque(columna)){
                            estado = 9; 
                       }else if(finCodigo()){
                            estado = 9;
                       }else{
                            estado = 7; 
                       }
                       break;
                   case 7://estado de encontrar mala identacion de un bloque
                        //System.out.println("llega al estado 7");
                       if(identacion(columna, number, columna)){
                            estado = -1;  
estrucSintactico.add(new Estructura(errorSIn, "While", "ERROR While", "Mala Identacion", fila, columna));  
                       }else{
                            estado = 8;
                       }
                       break;
                   case 8://segundo estado que se encarga de verificar la mala identacion
                        //System.out.println("estado 8");
                       if(!segundaIdentacion(columna)){
                          estado = -1; 
estrucSintactico.add(new Estructura(errorSIn, "While", "ERROR While", "Mala Identacion", fila, columna));
                       }else{
                            estado = 6;
                       }
                       break;
                   case 9://estado de aceptacion par el while
                        estado = 22;
estrucSintactico.add(new Estructura("While", "While", "While Valido", "", fila, columna));
                       break;
                   default:
                       throw new AssertionError();
               }
           }//fin del while
       }//fin del for
       return estrucSintactico;
   }//fin de la calse para la estructura del while

    //estructura base para poder analizar las funciones (Valido)
    public List<Estructura> estructuraFunciones(int number){
       estado = 0;//estado inicial
       for (int i = number; i < tokens.size(); i++) {//bucle for el cual se encarga de recorrer todos los tokens
           while(estado != -1 && estado != 22){
               switch (estado) {
                   case 0://estado incial el cual obtiene tanto la fila como la columnna de la funcion principal
                       if(tokens.get(i).getLexema().equals("def")){
                            fila = tokens.get(number).getFila();
                            columna = tokens.get(number).getColumna();
                            estado = 1;
                       }else{
                           estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Funcion", "ERROR Funcion", "ERROR desconocido", fila, columna));
                       }
                       break;
                   case 1://estado que encuentra el nombre de la funcion
                        //System.out.println("estado 1");
                       if(identificador()){
                           name = tokens.get(i+1).getLexema();
                         estado = 2;  
                        }else{
                           estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Funcion", "ERROR Funcion", "Falta un nombre de la Funcion", fila, columna)); 
                       }
                       break;
                   case 2://estado el cual se encarga de encontrar el parentesis de abertura
                        //System.out.println("estado 2");
                       if(parentesisAbierto()){
                         estado = 3;  
                       }else{
                           estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Funcion", "ERROR Funcion", "Falta un Parentesis", fila, columna));    
                       }
                       break;
                   case 3://sirve para identificar una funcion simple o con valores
                        //System.out.println("estado 3");
                       if (condicion2()) {
                            estado = 4;
                        } else if (parentesisCerrado()) {
                            estado = 8;
                        } else {
                           estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Funcion", "ERROR Funcion", "Falta un Parentesis o Condicion", fila, columna));        
                        }
                       break;
                   case 4://si es una funcion con valores esta se encarga de poder encontrar mas si hay una coma
                        //System.out.println("estado 4");
                       if(coma()){
                           estado = 5;
                       }else{
                           estado = 6;
                       }
                       break;
                   case 5://si hubo una coma se encontra otra condicion mas
                       if(condicion2()){
                         estado = 15;  
                       }else{
                        estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Funcion", "ERROR Funcion", "Falta una Condicion Luego de La coma", fila, columna));                 
                       }
                       break;
                       //estructura extra para poder encontrar mas condicines dento de este mismo
                   case 15://estado para seguir encontrando mas valores
                        //System.out.println("\u001B[31mestado 15 \u001B[31m");
                       if(coma()){
                           estado = 5;
                       }else{
                           estado = 6;
                       }
                       break;
                   case 6://estado el cual encuentra un parentesis luego de valores dentro de la funcion
                        //System.out.println("estado 6");
                       if(parentesisCerrado()){
                           estado = 8;
                       }else{
                           estado = 7;
                       }
                       break;
                   case 7://bloque extra para el parentesis
                       if(parentesisMal()){
                           estado = 8;
                       }else{
                           estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Funcion", "ERROR Funcion", "Falta un Parentesis de cierre", fila, columna));
                       }
                       break;
                   case 8://estado el cual indica el fin de la estructura de la funcion
                        //System.out.println("estado 8");
                       if(dosPuntos()){
                           estado = 9;
                       }else if(dosPuntosEspe()){
                           estado = 9;
                       }else{
                           estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Funcion", "ERROR Funcion", "Faltan dos puntos", fila, columna));       
                       }
                       break;
                   case 9://encargado de analizar si es una funcion con 1: retorno 2: fin del bloque 3: funcion vacia
                       if(fin_DE_Bloque(columna)){
                           estado = 12;
                       }else if(finCodigo()){
                           estado = 12;
                       }else if(Return(columna)){
                           estado = 13;
                       }else{
                           estado = 10;
                       }
                       break;
                   case 10://bloque que se encarga de la identacion
                       if(identacion(columna, number, columna)){
                          estado = -1; 
estrucSintactico.add(new Estructura(errorSIn, "Funcion", "ERROR Funcion", "Mala Identacion en bloque", fila, columna));       
                       }else{
                           estado = 11;
                       }
                       break;
                   case 11://bloque el cual es de respaldo para la identacion //no eliminar este y ninguno otro
                       if(!segundaIdentacion(columna)){
                           estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Funcion", "ERROR Funcion", "Mala Identacion en bloque", fila, columna));      
                       }else{
                           estado = 9;
                       }
                       break;
                   case 12://bloque de aceptacion para una funcion simple
                       estado = 22;
estrucSintactico.add(new Estructura("Funcion", name, "Funcion Valida", "", fila, columna));     
                       break;
                   case 13://bloque para retornar un valor de la funcion
                       if(condicion2()){
                           estado = 14;
                       }else{
                           estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "ERROR funcion return", "ERROR en valor de retorno", "", fila, columna));       
                       }
                       break;
                   case 14://estado de acpetacion para una funcion return
                       estado = 22;
estrucSintactico.add(new Estructura("Funcion", name, "Funcion Return Valida", "", fila, columna));      
                       break;
                   default:
                       throw new AssertionError();
               }
           }//fin del while
       }//fin del for
       return estrucSintactico;
   }
   
    //estructura de la impresion de funciones (invalido)
    public List<Estructura> impresionFunciones(int number){
        estado = 0;
        for (int i = number; i < tokens.size(); i++) {
            while(estado != -1 && estado !=22){
                switch (estado) {
                    case 0:
                        if(tokens.get(number).getLexema().equals("print")){
                            fila = tokens.get(number).getFila();
                            columna = tokens.get(number).getColumna();
                            estado = 1;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Print", "ERROR Print", "ERROR Desconocido", fila, columna));
                        }
                        break;
                    case 1:
                        if(parentesisAbierto()){
                            estado = 2;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Print", "ERROR Print", "Falta un parentesis de abertura", fila, columna));                            
                        }
                        break;
                    case 2:
                        if(condicion()){
                            name = tokens.get(i + 2).getLexema();
                            estado = 3;
                        }else if(condicion3(fila)){
                            name = tokens.get(i+2).getLexema();
                            estado = 3;
                        }else if(parentesisCerrado()){
                            estado = 12;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Print", "ERROR Print", "Falta parentesis de cierre", fila, columna));                            
                        }
                        break;
                    case 3:
                        if(parentesisAbierto()){
                            estado = 4;
                        }else if(coma()){
                            estado = 10;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Print", "ERROR Print", "Falta parentesis de cierre", fila, columna));                            
                        }
                        break;
                    case 4:
                        if(condicion2()){
                            estado = 5;
                        }else if(parentesisMal()){
                            estado = 8;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Print", "ERROR Print", "Falta parentesis de cierre en función", fila, columna));    
                        }
                        break;
                    case 5:
                        if(coma()){
                            estado = 6;
                        }else if(parentesisCerrado()){
                            estado = 8;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Print", "ERROR Print", "Falta parentesis de cierre en funcion", fila, columna));
                        }
                        break;
                    case 6:
                        if(condicion3(fila)){
                            estado = 7;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Print", "ERROR Print", "Falta una condicion luego de la coma", fila, columna));
                        }
                        break;
                    case 7:
                        if(coma()){
                            estado = 6;
                        }else if(parentesisCerrado()){
                            estado = 8;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Print", "ERROR Print", "Falta parentesis de cierre en funcion", fila, columna));
                        }
                        break;
                    case 8:
                        if(parentesisCerrado()){
                            estado = 9;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Print", "ERROR Print", "Falta parentesis de cierre", fila, columna));
                        }
                        break;
                    case 9:
                        if(!condicionERROR(fila)){
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Print", "ERROR Print", "Error en impresion", fila, columna));
                        }else{
                            estado = 22;
estrucSintactico.add(new Estructura("Print Funcion", name, "Print Valido", "", fila, columna));
                        }
                        break;
                    case 10: 
                        if(condicion3(fila)){
                            estado = 11;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Print", "ERROR Print", "Falta una condicion luego de la coma", fila, columna));
                        }
                        break;
                    case 11:
                        if(coma()){
                            estado = 10;
                        }else if(parentesisCerrado()){
                            estado = 12;
                        }else{
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Print", "ERROR Print", "Falta parentesis de cierre", fila, columna));
                        }
                        break;
                    case 12:
                        if(condicionERROR(fila)){
                            estado = -1;
estrucSintactico.add(new Estructura(errorSIn, "Print", "ERROR Print", "Impresion Errone", fila, columna));
                        }else{
                        estado = 22;
estrucSintactico.add(new Estructura("Print simple", "Print", "Print Valido", "", fila, columna));
                        }
                        break;
                    default:
                        throw new AssertionError();
                }
            }//fin del while
        }//fin del for
        return estrucSintactico;
    }//fin de la clase impresiones
    
    //estructuras para el reconocimiento de operaciones
    //ESTRUCTURA PARA LA DECLARACION DE LAS VARIABLES Y TERNARIO
    //SOLO SON ESTRUCTURAS DE VALORES ESPCIALES CON CAMBIOS SUTILES PERO UTILES VALIDADO
    private boolean asignacion(int fila) {
        // si es un tipo de token que se este asignado en el apartado
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getTipoToken().equals("Asignacion") && tokens.get(number).getFila() == fila) {
                return true;
            } else {
            }
        }
        return false;
    }
    private boolean identificadorVariables(int fila) {
    //sirve para encontrar el token identificador    
        number++;
        for (int i = number; i < tokens.size(); i++) {
        if (tokens.get(number).getTipoToken().equals("Identificador")&& tokens.get(number).getFila() == fila) {
            return true;
        }
        }
        return false;
    }
    private boolean corcheteCerradoEspe() {
    //token para los corchetes cerrado
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals("}")) {
                //System.out.println("Si son corchetes");
                return true;
            }
        }
        return false;
    }
    private boolean corcheteAbiertoEspe() {
    //token para los abiertos
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals("{")) {
                //System.out.println("Si son corchetes");
                return true;
            }
        }
        return false;
    }
    private boolean comaEspe() {
    //sirve para aplicar condiciones varias
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals(",")) {
                return true;
            }
        }
        return false;
    }
    //ESTRUCTURA PARA TIPOS DE VALOR VALIDADO
    private boolean var(int fila){
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getTipoToken().equals("Identificador") && tokens.get(number).getFila() == fila) {
                return true;
            } else {
            }
        }
        return false;
    }
    private boolean var_Espe(int fila){
    number++;
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getTipoToken().equals("Identificador") && tokens.get(number).getFila() == fila) {
                return true;
            } else {
            }
        }
        return false;
    }
    private boolean int_(int fila){
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getTipoToken().equals("Numero entero") && tokens.get(number).getFila() == fila) {
                return true;
            } else {
            }
        }
        return false;
    }    
    private boolean int_Espe(int fila){
    number++;
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getTipoToken().equals("Numero entero") && tokens.get(number).getFila() == fila) {
                return true;
            } else {
            }
        }
        return false;
    }    
    private boolean float_(int fila){
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getTipoToken().equals("Numero decimal") && tokens.get(number).getFila() == fila) {
                return true;
            } else {
            }
        }
        return false;
    }    
    private boolean float_Espe(int fila){
    number++;
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getTipoToken().equals("Numero decimal") && tokens.get(number).getFila() == fila) {
                return true;
            } else {
            }
        }
        return false;
    }    
    private boolean booleanas(int fila){
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getTipoToken().equals("Booleana") && tokens.get(number).getFila() == fila) {
                return true;
            } else {
            }
        }
        return false;
    }
    private boolean Strings_(int fila){
        for (int i = number; i < tokens.size(); i++) {
            if ((tokens.get(number).getTipoToken().equals("Cadena") 
              || tokens.get(number).getTipoToken().equals("Cadena simple") )&& tokens.get(number).getFila() == fila) {
                return true;
            } else {
            }
        }
        return false;
    }     
    private boolean Strings_Espe(int fila){
    number++;
        for (int i = number; i < tokens.size(); i++) {
            if ((tokens.get(number).getTipoToken().equals("Cadena") 
              || tokens.get(number).getTipoToken().equals("Cadena simple") )&& tokens.get(number).getFila() == fila) {
                return true;
            } else {
            }
        }
        return false;
    }        
    
    //ESTRUCTURA ESPECIALES LAS CUALES SERVIRAN PARA IDENTIICAR FIN DE BLOQUE O BLOQUES CON IDENTACION
    //sirve para identicar el final del codigo con 0 tokesn por delante V
    private boolean finCodigo() {
        return number >= tokens.size();
    }
    //sirve para reconocer fin de bloques V
    private boolean fin_DE_Bloque(int columna) {
        //identifica un token con una columna que tendra como indicativo fin de bloque 
        number++;
        for (int i = number; i < tokens.size(); i++) {
            //System.out.println(tokens.get(number).getColumna());
            if ((tokens.get(number).getTipoToken().equals("Identificador")
                    || tokens.get(number).getTipoToken().equals("Aritmetico")
                    || tokens.get(number).getTipoToken().equals("Comparación")
                    || tokens.get(number).getTipoToken().equals("Logicos")
                    || tokens.get(number).getTipoToken().equals("Asignacion")
                    || tokens.get(number).getTipoToken().equals("Palabra reservada")
                    || tokens.get(number).getTipoToken().equals("Numero entero")
                    || tokens.get(number).getTipoToken().equals("Numero decimal")
                    || tokens.get(number).getTipoToken().equals("Cadena")
                    || tokens.get(number).getTipoToken().equals("Comentario Simple")
                    || tokens.get(number).getTipoToken().equals("Comentario")
                    || tokens.get(number).getTipoToken().equals("ERROR")
                    || tokens.get(number).getTipoToken().equals("Booleana")
                    || tokens.get(number).getTipoToken().equals("Otros"))
                    && tokens.get(number).getColumna() == columna) {
                //System.out.println("ya no es parte del bloque");
                return true;
            } else {
                //System.out.println("no entro");
            }
        }
        return false;
    }
    //fin de bloque con valores especiales V
    private boolean fin_DE_Bloque_if(int columna) {
        //identifica un token con una columna que tendra como indicativo fin de bloque para if
        for (int i = number; i < tokens.size(); i++) {
            //System.out.println(tokens.get(number).getColumna());
            if ((tokens.get(number).getTipoToken().equals("Identificador")
                    || tokens.get(number).getTipoToken().equals("Aritmetico")
                    || tokens.get(number).getTipoToken().equals("Comparación")
                    || tokens.get(number).getTipoToken().equals("Logicos")
                    || tokens.get(number).getTipoToken().equals("Asignacion")
                    || tokens.get(number).getTipoToken().equals("Palabra reservada")
                    || tokens.get(number).getTipoToken().equals("Numero entero")
                    || tokens.get(number).getTipoToken().equals("Numero decimal")
                    || tokens.get(number).getTipoToken().equals("Cadena")
                    || tokens.get(number).getTipoToken().equals("Comentario Simple")
                    || tokens.get(number).getTipoToken().equals("Comentario")
                    || tokens.get(number).getTipoToken().equals("ERROR")
                    || tokens.get(number).getTipoToken().equals("Booleana")
                    || tokens.get(number).getTipoToken().equals("Otros"))
                    && tokens.get(number).getColumna() == columna) {
                estado++;
                //System.out.println("ya no es parte del bloque");
                return true;
            } else {
                //System.out.println("no entro");
            }
        }
        return false;
    }
    //sirve para identificar identaciones V
    private boolean identacion(int columnaEsperada, int number, int columna) {
        int i = number; // Inicializa la variable de iteración fuera del bucle
        int numTokens = tokens.size(); // Obtén el número de tokens una vez para mejorar la eficiencia
        columnaEsperada = columna + 4;
        while (i < numTokens) {
            Token tokenss = tokens.get(i);
            //System.out.println(columnaEsperada);
            if ((tokenss.getTipoToken().equals("Identificador")
                    || tokenss.getTipoToken().equals("Aritmetico")
                    || tokenss.getTipoToken().equals("Comparación")
                    || tokenss.getTipoToken().equals("Logicos")
                    || tokenss.getTipoToken().equals("Asignacion")
                    || tokenss.getTipoToken().equals("Palabra reservada")
                    || tokenss.getTipoToken().equals("Numero entero")
                    || tokenss.getTipoToken().equals("Numero decimal")
                    || tokenss.getTipoToken().equals("Cadena")
                    || tokenss.getTipoToken().equals("Booleana")
                    || tokenss.getTipoToken().equals("Comentario")
                    || tokenss.getTipoToken().equals("Comentario Simple")
                    || tokenss.getTipoToken().equals("Otros")
                    || tokenss.getTipoToken().equals("ERROR"))
                    && (tokenss.getColumna() == columnaEsperada) || tokenss.getColumna() > columnaEsperada) {
                // Si la columna del token actual coincide con la columna esperada, retorna true
                return true;
            } else if (tokenss.getColumna() == columna) {
                //System.out.println("columan del breac"+columna);
                break;
            }

            i++; // Avanza al siguiente token
        }

        return false; // Si no se encontró ninguna coincidencia, retorna false
    }
    //sirve para identificar las identaciones V
    private boolean segundaIdentacion(int columna) {
        //verifica una segunda identacion
        int colum = 0;
        colum = 4 + columna;
        //System.out.println("colum"+colum);
        for (int i = number; i < tokens.size(); i++) {
            //System.out.println(tokens.get(number).getColumna());
            if ((tokens.get(number).getTipoToken().equals("Identificador")
                    || tokens.get(number).getTipoToken().equals("Aritmetico")
                    || tokens.get(number).getTipoToken().equals("Comparación")
                    || tokens.get(number).getTipoToken().equals("Logicos")
                    || tokens.get(number).getTipoToken().equals("Asignacion")
                    || tokens.get(number).getTipoToken().equals("Palabra reservada")
                    || tokens.get(number).getTipoToken().equals("Numero entero")
                    || tokens.get(number).getTipoToken().equals("Numero decimal")
                    || tokens.get(number).getTipoToken().equals("Cadena")
                    || tokens.get(number).getTipoToken().equals("Comentario Simple")
                    || tokens.get(number).getTipoToken().equals("Comentario")
                    || tokens.get(number).getTipoToken().equals("ERROR")
                    || tokens.get(number).getTipoToken().equals("Booleana")
                    || tokens.get(number).getTipoToken().equals("Otros"))
                    && (tokens.get(number).getColumna() > 1 && (tokens.get(number).getColumna() == colum) 
                    || tokens.get(number).getColumna() > colum)) {
                return true;
            } else {
            }
        }
        return false;
    }
    
    //ESTRUCTURA PARA IDENTIFICAR PALABRAS RESERVADAS EN FUNCIONES
    //if para valores ternarios
    private boolean IF_Ternario(int fila) {
    //aca encuentra el valor de if y lo remueve para que noa tomado como un if simple
        for (int i = number; i < tokens.size(); i++) {
            //System.out.println(tokens.get(number).getColumna());
            if (tokens.get(number).getLexema().equals("if") && tokens.get(number).getFila() == fila) {
                tokens.remove(i);
                return true;
            } else {
            }
        }
        return false;
    }
    //estructura para elifs V
    private boolean Elif(int columna, int fila) {
        //identifica la palabra elif
        number++;
        for (int i = number; i < tokens.size(); i++) {
            //System.out.println(tokens.get(number).getColumna());
            if (tokens.get(number).getLexema().equals("elif")
                    && tokens.get(number).getColumna() == columna) {
                return true;
            } else {
                //System.out.println("No entra en su columna");
            }
        }
        return false;
    }
    //estructura para else simple V
    private boolean Else(int columna, int fila) {
    //este servira para el else en columna igual a if
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals("else")
                    && (tokens.get(number).getColumna() == columna)) {
                return true;
            } else {
                //System.out.println("No entra en su columna else");
            }
        }
        return false;
    }
    //metodo para identificar el metodo especial de un else V
    private boolean ElseBreak(int columna, int fila) {
    //este servira para el else de break en un for
        number++;
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals("else")
                    && (tokens.get(number).getColumna() == columna)) {
                return true;
            } else {
                //System.out.println("No entra en su columna else");
            }
        }
        return false;
    }
    //para identificar los valores else ternario V
    private boolean elseTernario(int fila) {
        number++;
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals("else")
                    && (tokens.get(number).getFila() == fila)) {
                return true;
            } else {
                //System.out.println("No entra en su columna else");
            }
        }
        return false;
    }
    //else ternario especial V
    private boolean elseTernarioEspe(int fila) {
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals("else")
                    && (tokens.get(number).getFila() == fila)) {
                return true;
            } else {
                //System.out.println("No entra en su columna else");
            }
        }
        return false;
    }
    //estructura para identificar los breack en funciones V
    private boolean isBreack(int columna) {
    //identifica posibles breack en funciones
        number++;
        for (int i = number; i < tokens.size(); i++) {
            //System.out.println(columna + 1);
            if (tokens.get(number).getLexema().equals("break")
                    && ((columna + 4) == tokens.get(number).getColumna() && columna < tokens.get(number).getColumna())) {
                return true;
            } else {
                //System.out.println("No entra en su columna else");
            }
        }

        return false;
    }
    //se encarga de buscar la palabra in V
    private boolean palabraIn(int fila) {
        number++;
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals("in")
                    && (tokens.get(number).getFila() == fila)) {
                estado++;
                return true;
            } else {
            }
        }
        return false;
    }
    //busca el token range para el for V
    private boolean range(int fila) {
    //palabra reservada range     
        number++;
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals("range")
                    && (tokens.get(number).getFila() == fila)) {
                return true;
            } else {
                //System.out.println("No entra en su columna else");
            }
        }
        return false;
    }
    //busca el token return para la funcion V
    private boolean Return(int columna) {
    //bloque para poder identificar un return identado
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals("return") && tokens.get(i).getColumna() == columna + 4) {
                return true;
            }
        }
        return false;
    } 
    
    //ESTRUCTURA PARA CONDICIONES ES DECIR INDETIFICADORES, CADENAS NUMERO ETC
    //condicion 1 V
    public boolean condicion() {
        //condicion valida inicial
        for (int i = number; i < tokens.size(); i++) {
            if ((tokens.get(number).getTipoToken().equals("Numero entero")
                    || tokens.get(number).getTipoToken().equals("Numero decimal")
                    || tokens.get(number).getTipoToken().equals("Cadena")
                    || tokens.get(number).getTipoToken().equals("Cadena simple")
                    || tokens.get(number).getTipoToken().equals("Booleana")
                    || tokens.get(number).getTipoToken().equals("Identificador"))) {
                estado++;
                return true;
            } else {
            }
        }
        return false;
    }
    //condicion especial para identificar errores
    public boolean condicionERROR(int fila) {
        //condicion valida inicial
        for (int i = number; i < tokens.size(); i++) {
            if ((tokens.get(number).getTipoToken().equals("Numero entero")
                    || tokens.get(number).getTipoToken().equals("Aritmetico")
                    || tokens.get(number).getTipoToken().equals("Comparación")
                    || tokens.get(number).getTipoToken().equals("Logicos")
                    || tokens.get(number).getTipoToken().equals("Asignacion")
                    || tokens.get(number).getTipoToken().equals("Palabra reservada")
                    || tokens.get(number).getTipoToken().equals("Numero entero")
                    || tokens.get(number).getTipoToken().equals("Numero decimal")
                    || tokens.get(number).getTipoToken().equals("Cadena")
                    || tokens.get(number).getTipoToken().equals("Comentario Simple")
                    || tokens.get(number).getTipoToken().equals("ERROR")
                    || tokens.get(number).getTipoToken().equals("Booleana")
                    || tokens.get(number).getTipoToken().equals("Otros")
                    || tokens.get(number).getTipoToken().equals("Identificador"))
                    && tokens.get(number).getFila() == fila) {
                estado++;
                return true;
            } else {
            }
        }
        return false;
    }
    public boolean condicionERROREspe(int fila) {
        //condicion valida inicial
        for (int i = number; i < tokens.size(); i++) {
            if ((tokens.get(number).getTipoToken().equals("Numero entero")
                    || tokens.get(number).getTipoToken().equals("Aritmetico")
                    || tokens.get(number).getTipoToken().equals("Comparación")
                    || tokens.get(number).getTipoToken().equals("Logicos")
                    || tokens.get(number).getTipoToken().equals("Asignacion")
                    || tokens.get(number).getTipoToken().equals("Palabra reservada")
                    || tokens.get(number).getTipoToken().equals("Numero entero")
                    || tokens.get(number).getTipoToken().equals("Numero decimal")
                    || tokens.get(number).getTipoToken().equals("Cadena")
                    || tokens.get(number).getTipoToken().equals("Comentario Simple")
                    || tokens.get(number).getTipoToken().equals("ERROR")
                    || tokens.get(number).getTipoToken().equals("Otros")
                    || tokens.get(number).getTipoToken().equals("Identificador"))
                    && tokens.get(number).getFila() == fila) {
                estado++;
                return true;
            } else {
            }
        }
        return false;
    }
    public boolean condicionERRORDoble(int fila) {
        //condicion valida inicial
        number++;
        for (int i = number; i < tokens.size(); i++) {
            if ((tokens.get(number).getTipoToken().equals("Numero entero")
                    || tokens.get(number).getTipoToken().equals("Aritmetico")
                    || tokens.get(number).getTipoToken().equals("Comparación")
                    || tokens.get(number).getTipoToken().equals("Logicos")
                    || tokens.get(number).getTipoToken().equals("Asignacion")
                    || tokens.get(number).getTipoToken().equals("Palabra reservada")
                    || tokens.get(number).getTipoToken().equals("Numero entero")
                    || tokens.get(number).getTipoToken().equals("Numero decimal")
                    || tokens.get(number).getTipoToken().equals("Cadena")
                    || tokens.get(number).getTipoToken().equals("Comentario Simple")
                    || tokens.get(number).getTipoToken().equals("ERROR")
                    || tokens.get(number).getTipoToken().equals("Otros")
                    || tokens.get(number).getTipoToken().equals("Identificador"))
                    && tokens.get(number).getFila() == fila) {
                return true;
            } else {
            }
        }
        return false;
    }
    //condicion 2 V
    public boolean condicion2() {
        // segunda condicion valida
        number++;
        for (int i = number; i < tokens.size(); i++) {
            if ((tokens.get(number).getTipoToken().equals("Numero entero")
                    || tokens.get(number).getTipoToken().equals("Numero decimal")
                    || tokens.get(number).getTipoToken().equals("Cadena")
                    || tokens.get(number).getTipoToken().equals("Cadena simple")
                    || tokens.get(number).getTipoToken().equals("Booleana")
                    || tokens.get(number).getTipoToken().equals("Identificador"))) {
                return true;
            } else {
            }
        }
        return false;
    }
    //condicion 3 V    
    public boolean condicion3(int fila) {
    //si es una condicion valida para la asignacion tomando de referencia el numero de fila
        number++;
        for (int i = number; i < tokens.size(); i++) {
            if ((tokens.get(number).getTipoToken().equals("Numero entero")
                    || tokens.get(number).getTipoToken().equals("Numero decimal")
                    || tokens.get(number).getTipoToken().equals("Cadena")
                    || tokens.get(number).getTipoToken().equals("Cadena Simple")
                    || tokens.get(number).getTipoToken().equals("Booleana")
                    || tokens.get(number).getTipoToken().equals("Identificador"))
                    && tokens.get(number).getFila() == fila) {
                return true;
            }
        }
        return false;
    }
    //condicion para encontrar el nombre de la funcion V
    public boolean identificador() {
    //sirve para encontrar el token identificador    
        number++;
        if (tokens.get(number).getTipoToken().equals("Identificador")) {
            return true;
        }
        return false;
    }
    //condicion logica V
    private boolean isLogic() {
        //si el valor que se tiene es logico
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getTipoToken().equals("Logicos")) {
                estado++;
                return true;
            }
        }
        return false;
    }
    //condicion logica especial V
    private boolean EspeLogic() {
        //si el valor que se tiene es logico
    number++;    
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getTipoToken().equals("Logicos")) {
                estado++;
                return true;
            }
        }
        return false;
    }
    //valores boleanos V
    private boolean valorBooleana() {
        //identifica si es un token tipo booleana
        number++;
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getTipoToken().equals("Booleana")) {
                estado++;
                return true;
            }
        }
        return false;
    }
 
    //ESTRUCTURA PARA VALORES DE ASIGNACIONES ES DECIR = < > O CARACTERES ESPECIALES COMO , { } ETC
    //asigancion IFS V
    private boolean asignacionIFS() {
        // metodo de asignacion principal para IF pero puede ser usado en otro tipo de metodo
        number++;
        for (int i = number; i < tokens.size(); i++) {
            //System.out.println("El TIPO de token es: \u001B[35m" + tokens.get(i).getTipoToken());
            if (tokens.get(number).getTipoToken().equals("Asignacion")
                    || tokens.get(number).getTipoToken().equals("Comparación")
                    || tokens.get(number).getLexema().equals("in")) {
                return true;
            } else {
            }
        }
        return false;
    }
    //si es un token coma V
    private boolean coma() {
    //sirve para aplicar condiciones varias
        number++;
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals(",")) {
                return true;
            }
        }
        return false;
    }
    //tokne de corchete abierto V
    private boolean corcheteAbierto() {
    //token para los abiertos
        number++;
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals("{")) {
                //System.out.println("Si son corchetes");
                return true;
            }
        }
        return false;
    }
    //tokne de corchete cerrado V
    private boolean corcheteCerrado() {
    //token para los corchetes cerrado
        number++;
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals("}")) {
                //System.out.println("Si son corchetes");
                return true;
            }
        }
        return false;
    }
    //dos puntos V
    private boolean dosPuntos() {
        //identifica dos puntos
        number++;
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals(":")) {
                return true;
            } else {
                //System.out.println("No es dos puntos");
            }
        }
        return false;
    }
    //dos puntos especiales V
    private boolean dosPuntosEspe() {
        //identifica dos puntos
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
    //token para encontrar una llave abierta V
    private boolean llaveAbierta() {
    //si se abre la llave o no     
        number++;
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals("[")) {
                //System.out.println("Si son corchetes");
                return true;
            }
        }
        return false;
    }
    //token para encontrar una llave cerrada V
    private boolean llaveCerradaEspe() {
    //si la llave se cierra o no
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals("]")) {
                return true;
            }
        }
        return false;
    }
    //token de parentesis inicial V
    private boolean parentesisAbierto() {
    //si es un token de parentesisi inicial
        number++;
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals("(")) {
                //System.out.println("si es un parentesis abierto");
                return true;
            }
        }
        return false;
    }
    //metodo para saber el nombre de la funcion
     private boolean parentesisAbiertoEspe() {
    //si es un token de parentesisi inicial
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals("(")) {
                //System.out.println("si es un parentesis abierto");
                return true;
            }
        }
        return false;
    }
    //token de parentesis final V
    private boolean parentesisCerrado() {
    //si es un final de parentesis    
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals(")")) {
                return true;
            }
        }
        return false;
    }
    //token de un parentesis especial V
    private boolean parentesisMal() {
    // parentesis de token especial
        number++;
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(number).getLexema().equals(")")) {
                //System.out.println("si es un parentesis cerrado ");
                return true;
            }
        }
        return false;
    }
}//fin de la clase