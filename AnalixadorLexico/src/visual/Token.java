/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package visual;

/**
 *
 * @author denil
 */
//clase Token la cual nos va ayudar a poder mostrar el tipo de token fila columna y el mismo+ 
//y el mismo token (lexema)
public class Token {
    private String lexema;//token
    private String tipoToken;//que tipo de token es
    private int fila;//fila en la que se encontro
    private int columna;//columna en la que se encontro
    //constructores
    public Token(String lexema, String tipoToken, int fila, int columna) {
        this.lexema = lexema;
        this.tipoToken = tipoToken;
        this.fila = fila;
        this.columna = columna;
    }
//get de los metodos
    public String getLexema() {
        return lexema;
    }

    public String getTipoToken() {
        return tipoToken;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
    
    @Override
    //este apartado es el que se mostrar en el texto de los lexemas
    public String toString(){
        return "Token: "+lexema+". Tipo de Token: "+tipoToken+". Fila: "
                +fila+" Columna: "+columna+".";
    }

    int getPosicionInicial() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
