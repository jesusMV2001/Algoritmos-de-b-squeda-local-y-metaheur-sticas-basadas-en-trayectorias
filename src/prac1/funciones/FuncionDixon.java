/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prac1.funciones;

import prac1.Configurador;

/**
 *
 * @author cojou
 */
public class FuncionDixon extends Evaluador {
    final private String nombre = "Dixon";
    final private double rango_min = -100;
    final private double rango_max = 100;
    int d;
    
    public FuncionDixon(Configurador config){
        d = config.getDimension();
    }
    
    @Override
    public double evaluar(double[] vector){
        double sum = 0.0;
        
        for (int i = 1; i < d; i++) {
            sum += (i+1) * Math.pow(2*Math.pow(vector[i], 2) - vector[i-1] ,2);
        }
        
        return Math.pow(vector[0]-1, 2) + sum;
    }
    
    @Override
    public double getRango_min() {
        return rango_min;
    }

    @Override
    public double getRango_max() {
        return rango_max;
    } 

    @Override
    public double[] getSolReal() {
        double[] v = new double[d];
        for (int i = 0; i < d; i++) {
            v[i] = Math.pow(2, -(Math.pow(2, i+1) - 2) / Math.pow(2, i+1));
        }
        return v;
    }

    @Override
    public String getNombre() {
        return nombre;
    }
    
}
