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
import java.nio.file.FileStore;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author nicof
 */
public class Utilidades {

    private String path = "data";
    private String file_name = "archivos.dat";
//jose
    public void saveusuario(String text, String name_file) throws IOException {
        File carpeta = new File(path);
        if(!carpeta.exists()){
            carpeta.mkdirs();
        }
         File file = new File(path+File.separatorChar+name_file);
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter escribir = new FileWriter(file, true);
        escribir.write(text);
        escribir.close();
    }
    public List<String> readLines(String name_file) throws IOException {
        List<String> lines = new ArrayList<>();
        File file = new File(path + File.separatorChar + name_file);
        if (!file.exists()) {
            return lines;
        }
//jose
//matias
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        br.close();
        return lines;
    }
    public void save(String text, String name_file) throws IOException {
        FileWriter file = new FileWriter(path + File.separatorChar + name_file, true);
        file.write(text);
        file.close();
    }
    public boolean guardar_archivo(String nombre, String direccion) {
        String data = generar_numeracion() + "\t" + nombre + "\t" + direccion + "\t" + "\n";
        try {
            save(data, file_name);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    private int countRegister(String name_file) throws IOException {
        FileReader file = new FileReader(path + File.separatorChar + name_file);
        BufferedReader br = new BufferedReader(file);
        int lines = (int) br.lines().count();
        file.close();
        br.close();
        return lines;
    }

    private int countColumn(String name_file) throws IOException {
        FileReader file = new FileReader(path + File.separatorChar + name_file);
        BufferedReader br = new BufferedReader(file);
        String line = br.readLine();
        file.close();
        br.close();
        return line.split("\t").length;
    }

    public String[][] listAll(String name_file) throws IOException {
        String[][] data = null;
        Integer filas = countRegister(name_file);
        if (filas > 0) {
            Integer col = countColumn(name_file);
            System.out.println("Columnas " + col);
            data = new String[filas][col];
            FileReader file = new FileReader(path + File.separatorChar + name_file);
            BufferedReader br = new BufferedReader(file);
            String linea = br.readLine();
            int fil = 0;
            while (linea != null) {
                String[] columas = linea.split("\t");
                for (int j = 0; j < columas.length; j++) {
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

    public String[][] listar() {
        try {
            return listAll(file_name);
        } catch (Exception e) {
            System.out.println("Error en listar " + e);
            return null;
        }

    }

    public String generar_numeracion() {
        //00001
        String inv = "";
        String[][] listado = listar();
        if (listado != null) {
            Integer num = 0;
            num = listado.length + 1;
            inv = "000001";
            inv = inv.substring(num.toString().length());
            inv += num.toString();
        } else {
            inv = "000001";
        }
        return inv;
    }

    public File copiar_fila(String direccion) {
        
        File Archivo = new File(direccion);
        
        Archivo.mkdir();
        return Archivo;
    }
}
//matias