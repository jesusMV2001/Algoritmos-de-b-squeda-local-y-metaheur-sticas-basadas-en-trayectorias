/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prac1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author jesus
 */
public class Archivodedatos {
    private int matriz1[][];
    private int matriz2[][];
    private String nombre;
    
    public Archivodedatos(String ruta){
        String linea;
        nombre = ruta.split("\\.")[0];
        FileReader f =null;
        try{
            f = new FileReader(ruta);
            BufferedReader b = new BufferedReader(f);
            int numero = Integer.parseInt(b.readLine());
            matriz1 = new int[numero][numero];
            matriz2 = new int[numero][numero];
            linea = b.readLine();
            for (int i = 0; i < numero; i++) {
                int errores=0;
                linea = b.readLine();
                String[] split = linea.split(" ");
                for (int j = 0; j < split.length; j++) {
                    try{
                        matriz1[i][j-errores] = Integer.parseInt(split[j]);
                    }catch(NumberFormatException ex){
                        errores++;
                    }
                    
                }
            }
            
            linea = b.readLine();
            for (int i = 0; i < numero; i++) {
                int errores=0;
                linea = b.readLine();
                String[] split = linea.split(" ");
                for (int j = 0; j < split.length; j++) {
                    try{
                        matriz1[i][j-errores] = Integer.parseInt(split[j]);
                    }catch(NumberFormatException ex){
                        errores++;
                    }
                    
                }
            }
            
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public int[][] getMatriz1() {
        return matriz1;
    }

    public int[][] getMatriz2() {
        return matriz2;
    }

    public String getNombre() {
        return nombre;
    }
            
    
}
