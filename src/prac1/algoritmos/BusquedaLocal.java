/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prac1.algoritmos;

import java.util.ArrayList;
import prac1.funciones.Evaluador;
import java.util.Random;
import prac1.Configurador;
import prac1.TablaDatos;

/**
 *
 * @author Xjesu
 */
public class BusquedaLocal extends Busqueda {
    Configurador config;
    boolean bl3 = true;
    ArrayList<double []> soluciones;

    public BusquedaLocal(Configurador config, boolean bl3) {
        this.config = config;
        this.bl3 = bl3;
        soluciones = new ArrayList<>();
    }
    
    
    @Override
    public double[] busqueda(Evaluador exe, long semilla, TablaDatos datos){
        Integer d = config.getDimension();
        Integer maxIteraciones = config.getIteraciones();
        double rangoSup = 1 + config.getDiffRango();
        double rangoInf = 1 - config.getDiffRango();
        double[] v = new double[d];
        Random aleatorio = new Random(semilla);
        
        for (int i = 0; i < d; i++) {
            v[i] = aleatorio.nextDouble(exe.getRango_max() - exe.getRango_min()) + exe.getRango_min();
        }
        soluciones.add(v);
        
        int mov = 0;
        boolean mejorado = true;
        while(mov < maxIteraciones && mejorado){
            double[] mejorVecino = new double[d];
            System.arraycopy(v, 0, mejorVecino, 0, d); // Guardamos la solución inicial como mejor vecino
            int numVecinos = config.getNumVecinos(); // Establecemos el número de vecinos por defecto definido en config.txt
            if(!bl3) numVecinos = aleatorio.nextInt(config.getNumVecinosMin(), config.getNumVecinosMax() + 1);
            for (int i = 0; i < numVecinos; i++) {
                double[] actual = new double[d];
                for (int j = 0; j < d; j++) {
                    if(aleatorio.nextDouble() < config.getProbCambio()){
                        actual[j]=obtenerValorRangoAleatorio(exe, v[j], rangoInf, rangoSup, aleatorio);
                    }
                    else actual[j] = v[j];
                }
                if(exe.evaluar(actual) < exe.evaluar(mejorVecino)){
                    System.arraycopy(actual, 0, mejorVecino, 0, d);
                }
            }
            if(exe.evaluar(mejorVecino) < exe.evaluar(v)){
                System.arraycopy(mejorVecino, 0, v, 0, d);
                soluciones.add(v);
                mov++;
            }
            else mejorado = false;
        }
        return v;
    }
    
    private double obtenerValorRangoAleatorio(Evaluador exe, double valor, double rangoInf, double rangoSup, Random aleatorio){
        if (valor >= 0) {
            double originRandom = Math.max(exe.getRango_min(), valor * rangoInf);
            double boundRandom = Math.min(exe.getRango_max(), valor * rangoSup);
            return aleatorio.nextDouble(originRandom, boundRandom);
        } else {
            double originRandom = Math.max(exe.getRango_min(), valor * rangoSup);
            double boundRandom = Math.min(exe.getRango_max(), valor * rangoInf);
            return aleatorio.nextDouble(originRandom, boundRandom);
        }
    }

    @Override
    public ArrayList<double[]> getSoluciones() {
        return soluciones;
    }
    
    @Override
    public void limpiaBusqueda(){
        soluciones.clear();
    }
}
