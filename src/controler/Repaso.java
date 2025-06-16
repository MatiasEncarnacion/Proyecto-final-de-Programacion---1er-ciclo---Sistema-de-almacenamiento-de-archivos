/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author nicof
 */
public class Repaso {
    private static Scanner sc = new Scanner(System.in);
    public int inicioSesion(String usuario, String contraseña, String U, String C, String U2, String C2){
        int contador = 3;
        int retorno = 0;
            if ((usuario.equals(U) && contraseña.equals(C)) || (usuario.equals(U2) && contraseña.equals(C2))){
                JOptionPane.showMessageDialog(null, "Contraseña ingresada correctamente");
                retorno = 1;
            } else if ((!usuario.equals(U) && contraseña.equals(C)) || (!usuario.equals(U2) && contraseña.equals(C2))){
                JOptionPane.showMessageDialog(null, "Usuario no registrado en el sistema, porfavor intente nuevamente");
                retorno = 2;
            } else if ((usuario.equals(U) && !contraseña.equals(C)) || (usuario.equals(U2) && !contraseña.equals(C2))){
                JOptionPane.showMessageDialog(null, "Contraseña erronea, porfavor intente nuevamente");
                retorno = 2;
            } else if ((!usuario.equals(U) && !contraseña.equals(C)) || (!usuario.equals(U2) && !contraseña.equals(C2))){
                JOptionPane.showMessageDialog(null, "Datos de inicio de sesion invalidos, intente nuevamente");
                retorno = 2;
            }
            contador--;
        return retorno;
    }

}
