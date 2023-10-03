package analizadorSintactico;

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

    public sintaticoPrueba(List<Token> token) {
        this.tokens = token;
        this.number = 0;
        this.estado = 0;
        this.fila = 1;
    }

    public void buscar() {
        for (int i = number; i < tokens.size(); i++) {
            if (tokens.get(i).getTipoToken().equals("Identificador") && tokens.get(i).getColumna() == 1) {
                fila = tokens.get(i).getFila();
                number = i;
                variables(number, fila);
            } else if (tokens.get(i).getTipoToken().equals("Palabra Clave")) {

            } else {

            }

        }

    }

    public void variables(int number, int fila) {
        estado = 0;
        boolean variableValida = false;
        for (int i = number; i < tokens.size(); i++) {
            if (estado == -1) {
                break;
            }
            while (estado != -1 && estado != 4) {
                System.out.println("SI ENTRA EN EL SWITCH\u001B[38m");
                System.out.println("El estado es: " + estado);
                switch (estado) {
                    case 0:
                        if (tokens.get(i).getTipoToken().equals("Identificador")) {
                            System.out.println("SI ES IDENTIFICADOR");
                            fila = tokens.get(i).getFila();
                            estado = 1;
                        } else {
                            estado = -1;
                        }
                        break;
                    case 1:
                        System.out.println("Si entre al estado 1 y su token es " + tokens.get(i).getTipoToken());
                        if (asignacion()) {
                            System.out.println("SI ES ASIGNACION");
                            estado = 2;
                        } else if (coma()) {
                            estado = 0;
                        } else {
                            estado = -1;
                        }
                        break;
                    case 2:
                        System.out.println("Tipo token nuevo es: " + tokens.get(i).getTipoToken());
                        if (number()) {
                            System.out.println("SI ES OTRO IDENTIFICADOR");
                            estado = 3;
                        } else {
                            estado = -1;

                        }
                        break;
                    case 3:
                        if (coma()) {
                            estado = 2;
                        } else {
                            estado = 4;
                            variableValida = true;
                        }
                        break;
                    default:
                        throw new AssertionError();
                }

            }//fin del while

        }//fin del for

        if (variableValida == true) {
            JOptionPane.showMessageDialog(null, "Variable Valida");
        } else {
            JOptionPane.showMessageDialog(null, "No valida " + fila);

        }
    }

    //estructura basica de las variables
    public boolean identificador() {
        if (estado < tokens.size() && tokens.get(estado).getTipoToken().equals("Identificador")) {
            estado++;
            return true;
        }
        return false;
    }

    public boolean number() {
        number++;

        for (int i = number; i < tokens.size(); i++) {
            if (estado < tokens.size() && (tokens.get(number).getTipoToken().equals("Numero entero")
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

    private boolean coma() {
        number++;
        for (int i = number; i < tokens.size(); i++) {
            if (estado < tokens.size() && tokens.get(estado).getLexema().equals(",")) {
                estado++;
                return true;
            }
        }
        return false;
    }

    private boolean asignacion() {
        System.out.println("estado es: " + estado);
        System.out.println("token size: " + tokens.size());
        number++;
        System.out.println("tipo token " + tokens.get(estado).getTipoToken().toString());
        for (int i = number; i < tokens.size(); i++) {

            System.out.println("El TIPO de token es: \u001B[35m" + tokens.get(i).getTipoToken());
            if (estado < tokens.size() && tokens.get(number).getTipoToken().equals("Asignacion")) {
                estado++;
                return true;
            } else {

                System.out.println("No entre al if");
            }
        }
        return false;
    }

}//fin de la clase
