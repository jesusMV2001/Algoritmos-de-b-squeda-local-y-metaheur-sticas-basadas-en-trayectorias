/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prac1;

import java.util.ArrayList;
import java.util.Random;
import prac1.algoritmos.*;
import prac1.funciones.*;

/**
 *
 * @author jesus
 */
public class Prac1 {
    
    public static Double redondearDecimales(Double numero, Integer numeroDecimales) {
        if(numero < 100000)
            return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
        return numero;
    }
    
    public static void evaluaFuncion(int i, int j, int col, Evaluador exe, Busqueda b, TablaDatos datos, Configurador config){
        long tiempoInicio;
        double solucion, diffTiempo;
        double[] sol;

        System.out.println("Algoritmo: " + config.getAlgoritmos().get(i));
        System.out.println("Semilla: " + config.getSemillas().get(j));
        System.out.println("Funcion " + exe.getNombre());
        tiempoInicio = System.nanoTime();
        sol = b.busqueda(exe, config.getSemillas().get(j), datos);
        diffTiempo = redondearDecimales((double)(System.nanoTime() - tiempoInicio) / 1000, config.getNumDecimales());
        solucion = redondearDecimales(exe.evaluar(sol), config.getNumDecimales());
        datos.insertarDato(solucion, i, j, col);
        datos.insertarDato(diffTiempo, i, j, col + 1);
        System.out.println("Solucion: " + solucion);
        System.out.println("Desviacion: " + Math.abs(solucion - exe.evaluar(exe.getSolReal())));
        System.out.println("DiffTiempo: " + diffTiempo);
        System.out.println("---------------------------------------");
        
        datos.escribeInfoLog(b.getSoluciones(), i, j, exe.getNombre(),exe);
        
        b.limpiaBusqueda();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int numFunciones = 10;
        Configurador config = new Configurador(args[0]);
        ArrayList<Busqueda> busquedas = config.obtenerBusquedas();
        TablaDatos datos = new TablaDatos(config, numFunciones);
        Evaluador exe;
        
        for (int i = 0; i < config.getAlgoritmos().size(); i++) {
            for (int j = 0; j < config.getSemillas().size(); j++) {         
                exe = new FuncionAckley(config);
                evaluaFuncion(i, j, 0, exe, busquedas.get(i), datos, config);
                
                exe = new funcionGriewank(config);
                evaluaFuncion(i, j, 2, exe, busquedas.get(i), datos, config);
                
                exe = new FuncionRastringin(config);
                evaluaFuncion(i, j, 4, exe, busquedas.get(i), datos, config);
                
                exe = new FuncionSchewefel(config);
                evaluaFuncion(i, j, 6, exe, busquedas.get(i), datos, config);
                
                exe = new FuncionPermBeta(config);
                evaluaFuncion(i, j, 8, exe, busquedas.get(i), datos, config);
                
                exe = new FuncionRotatedHypeEllipsoid(config);
                evaluaFuncion(i, j,10, exe, busquedas.get(i), datos, config);
                
                exe = new FuncionRosenbrock(config);
                evaluaFuncion(i, j,12, exe, busquedas.get(i), datos, config);
                
                exe = new FuncionMICHALEWICZ(config);
                evaluaFuncion(i, j,14, exe, busquedas.get(i), datos, config);
                
                exe = new FuncionTrid(config);
                evaluaFuncion(i, j, 16, exe, busquedas.get(i), datos, config);
                
                exe = new FuncionDixon(config);
                evaluaFuncion(i, j, 18, exe, busquedas.get(i), datos, config);
            }
        }
        datos.generarFichero();
    }  
}
