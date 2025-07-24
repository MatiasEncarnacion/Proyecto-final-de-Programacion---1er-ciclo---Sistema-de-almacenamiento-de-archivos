/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistema_de_almacenamiento;

import java.util.Scanner;

/**
 *
 * @author nicof
 */
public class Sistema_de_almacenamiento {

    /**
     * @param args the command line arguments
     */
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        InicioSesion rp = new InicioSesion();
        int identificador = 1;
        String U = "Matias";
        String U2 = "Leonel";
        String C = "12345";
        String C2 = "54321";
        int retorno = 1;
        System.out.println("Bienvenido al sistema de almacenamiento de documentos y archivos");
        System.out.println("Porfavor inserte el usuario y la contraseña");
        System.out.print("Usuario: ");
        String usuario = sc.next();
        System.out.print("Contraseña: ");
        String contraseña = sc.next();
        if ((usuario.equals(U) && contraseña.equals(C)) || (usuario.equals(U2) && contraseña.equals(C2))){
            System.out.println("Inicio de sesion exitoso");
        } else if ((!usuario.equals(U) && contraseña.equals(C)) || (!usuario.equals(U2) && contraseña.equals(C2))){
            System.out.println("Usuario no registrado en el sistema, porfavor intente nuevamente");
            retorno = rp.inicioSesion(usuario, contraseña, U, C);
        } else if ((usuario.equals(U) && !contraseña.equals(C)) || (usuario.equals(U2) && !contraseña.equals(C2))){
            System.out.println("Contraseña erronea, porfavor intente de nuevo");
            retorno = rp.inicioSesion(usuario, contraseña, U, C);
        }  else if ((!usuario.equals(U) && !contraseña.equals(C)) || (!usuario.equals(U2) && !contraseña.equals(C2))){
            System.out.println("Datos de inicio de sesion incorrectos, intente nuevamente");
            retorno = rp.inicioSesion(usuario, contraseña, U, C);
        }
        if (retorno == 2){
            System.out.println("Ha superado el limite de intentos de inicio de sesion");
            throw new AssertionError();
        } else if (retorno == 1){
            System.out.println("Bienvenido al sistema");
        }
    }
    
}
