/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analizadorSintactico;

import java.util.List;
import visual.Token;

/**
 *
 * @author denil
 */
public class claves {
     private List<Token> tokens;
    private int estado;

    public claves(List<Token> token) {
        this.tokens = token;
        this.estado = 0;
    }
}
