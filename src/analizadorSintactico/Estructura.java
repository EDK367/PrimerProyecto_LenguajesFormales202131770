/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analizadorSintactico;

/**
 *
 * @author denil
 */
public class Estructura {
    private String expresion;
    private String bloque;
    private String validacion;
    private int fila;
    private int columna;

    public Estructura(String expresion, String bloque, String validacion, int fila, int columna) {
        this.expresion = expresion;
        this.bloque = bloque;
        this.validacion = validacion;
        this.fila = fila;
        this.columna = columna;
    }

    public String getExpresion() {
        return expresion;
    }

    public String getBloque() {
        return bloque;
    }

    public String getValidacion(){
        return validacion;
    }
    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
    

   
    @Override
    public String toString() {
        return "Estructura: " + expresion + " Nombre del Bloque: " + bloque + " " 
                + validacion + " Fila: "+ fila + " Columna: " + columna ;
    }

   
    
}
