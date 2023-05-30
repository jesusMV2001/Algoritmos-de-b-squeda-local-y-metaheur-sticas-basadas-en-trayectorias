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
public class FuncionTrid extends Evaluador{
    final private String nombre = "Trid";
    final private double rango_min = -1000;
    final private double rango_max = 1000;
    int d;
    
    public FuncionTrid(Configurador config){
        d = config.getDimension();
    }

    @Override
    public double evaluar(double[] vector){
        double valor;
        double sum1 = 0.0;
        double sum2 = 0.0;
        
        for (int i = 0; i < d; i++) {
            sum1 += Math.pow(vector[i]-1, 2);
            if(i>=1)
                sum2 += vector[i]*vector[i-1];
        }
        
        valor = sum1 - sum2 ;
        
        return valor;
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
    public double[] getSolReal(){
        double[] v = new double[d];
        for (int i = 0; i < d; i++) {
            v[i] = (i+1)*(d+1-(i+1));
        }
        return v;
    }

    @Override
    public String getNombre() {
        return nombre;
    }
    
}

