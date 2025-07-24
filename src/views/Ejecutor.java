/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import controler.Utilidades;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import views.tablas.ModeloTablaArchivo;
/**
 *
 * @author nicof
 */
public class Ejecutor {
    private static ModeloTablaArchivo mtl = new ModeloTablaArchivo();
    private String path = "data";
    private String file_name = "archivos.dat";

    public void save(String text, String name_file) throws IOException {
        FileWriter file = new FileWriter(path + File.separatorChar + name_file, true);
        file.write(text);
        file.close();
    }
        
    public static void main(String[] args) {
        Utilidades u = new Utilidades();
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese ruta de archivo");
        String ruta = sc.next();
        File archivo = new File(ruta);
            String nombre = (String) mtl.getValueAt(0, 2);
            System.out.println(nombre);
            if(nombre.matches(".docx")){
                System.out.println("El documento es word");
            } else{
                System.out.println("No sirve");
            }
    
            /*
            try{
            Desktop.getDesktop().open(archivo);
            Desktop.Action[] valores = Desktop.Action.values();
            for(int i = 0; i<=valores.length;i++){
            System.out.println("Posicion "+i+" "+valores[i].name());
            }
            }catch (IOException e){
            System.out.println("No se pudo imprimir valores");
            }*/
       
        }
    }

