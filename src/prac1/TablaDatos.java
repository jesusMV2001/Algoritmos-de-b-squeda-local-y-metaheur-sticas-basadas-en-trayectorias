/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prac1;

import prac1.funciones.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Xjesu
 */
public class TablaDatos {
    private Configurador config = null;
    private int numAlgt = 0, filas = 0, cols = 0;
    private ArrayList<double [][]> datos = null;
    private FileWriter fichero = null;
    private PrintWriter pw = null;

    public TablaDatos(Configurador config, int numFunciones) {
        this.config = config;
        numAlgt = config.getAlgoritmos().size();
        filas = config.getSemillas().size();
        cols = numFunciones * 2;
        datos = new ArrayList<>();
        for (int i = 0; i < numAlgt; i++)             
            datos.add(new double[filas][cols]);
        
        
    }
    
    public void insertarDato(double valor, int ejec, int fila, int col){
        datos.get(ejec)[fila][col] = valor;
    }
    
    public void escribeInfoLog(ArrayList<double []> soluciones, int numAlg, int numEjec, String nombreFunc, Evaluador exe){
        try
        {            
            
            String semilla = String.valueOf(config.getSemillas().get(numEjec));
            //Generación del fichero con todo el log de la ejecución
            fichero = new FileWriter("logs/"+config.getAlgoritmos().get(numAlg)+"_"+
                   config.getSemillas().get(numEjec)+"_"+nombreFunc+"_log.txt");
            pw = new PrintWriter(fichero);

            pw.println("Algoritmo " + config.getAlgoritmos().get(numAlg));
            pw.println("Semilla " + config.getSemillas().get(numEjec));
            pw.println("Funcion " + nombreFunc);
            pw.println("-----------------------------------------");
            for (int i = 0; i < soluciones.size(); i++) {
                pw.print("Solucion " + (i+1) + ": [");
                for (int j = 0; j < soluciones.get(i).length; j++) {
                    pw.print(soluciones.get(i)[j] + ", ");                    
                }
                pw.println("]");
                pw.println("Evaluacion de la solucion "+(i+1)+": "+exe.evaluar(soluciones.get(i)));
                pw.println("-----------------------------------------");
            }
            pw.println();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    
    public void generarFichero(){
        try
        {
            // Generacion del fichero con los datos en formato CSV
            fichero = new FileWriter("salida.txt");
            pw = new PrintWriter(fichero);

            for (int i = 0; i < numAlgt; i++) {
                pw.print(config.getAlgoritmos().get(i) + ","); // Nombre tabla
                for (int j = 0; j < cols / 2; j++) pw.print("Solucion,Tiempo,"); // Cabecera tabla
                //Datos
                pw.println();
                for (int j = 0; j < filas; j++){
                    pw.print("Ejecucion_" + (j+1) + ",");
                    for (int k = 0; k < cols; k++) {
                        pw.print(datos.get(i)[j][k] + ",");
                    }
                    pw.println();
                }
                pw.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }    
}
