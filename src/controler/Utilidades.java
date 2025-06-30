/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author nicof
 */
public class Utilidades {
    private String path = "data";
    private String file_name = "archivos.dat";
    public void save(String text, String name_file) throws IOException{
        FileWriter file = new FileWriter(path+File.separatorChar+name_file, true);
        file.write(text);
        file.close();
    }
    public boolean guardar_archivo(String nombre, String direccion){
        String data = generar_numeracion()+"\t"+nombre+"\t"+direccion+"\t"+"\n";
        try {
            save(data, file_name);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    private int countRegister(String name_file) throws  IOException {
        FileReader file = new FileReader(path+File.separatorChar+name_file);
        BufferedReader br = new BufferedReader(file);        
        int lines = (int)br.lines().count();
        file.close();
        br.close();
        return lines;
    }
    private int countColumn(String name_file) throws  IOException {
        FileReader file = new FileReader(path+File.separatorChar+name_file);
        BufferedReader br = new BufferedReader(file);        
        String line = br.readLine();
        file.close();
        br.close();
        return line.split("\t").length;
    }
    public String[][] listAll(String name_file) throws IOException{
        String [][] data = null;       
        Integer filas = countRegister(name_file);
        if(filas > 0) {
            Integer col = countColumn(name_file);
            System.out.println("Columnas "+col);
            data = new String[filas][col];
            FileReader file = new FileReader(path+File.separatorChar+name_file);
            BufferedReader br = new BufferedReader(file);
            String linea = br.readLine();
            int fil = 0;
            while(linea != null) {
                String[] columas = linea.split("\t");
                for(int j = 0; j < columas.length; j++){
                    data[fil][j] = columas[j];
                }
                fil++;
                linea = br.readLine();
            }
            file.close();
            br.close();
        }
        
        System.out.println();
        return data;
    }
    public String[][] listar(){
        try {
            return listAll(file_name);
        } catch (Exception e) {
            System.out.println("Error en listar "+e);
            return null;
        }
        
    }
    public String generar_numeracion(){
        //00001
        String inv = "";
        String [][] listado = listar();
        if (listado != null){
            Integer num = 0;
            num = listado.length +1;
            inv = "000001";
            inv = inv.substring(num.toString().length());
            inv += num.toString();
        } else {
            inv = "000001";
        }
        return inv;
    }
}
