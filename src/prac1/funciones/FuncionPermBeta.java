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
public class FuncionPermBeta extends Evaluador{
    final private String nombre = "PermBeta";
    final private double rango_min = -100;
    final private double rango_max = 100;
    int d;
    
    public FuncionPermBeta(Configurador config){
        d = config.getDimension();
    }
    
    @Override
    public double evaluar(double[] vector){
        double sum1 = 0.0;
        double sum2;
        
        for (int i = 0; i < d; i++) {
            sum2=0.0;
            for (int j = 0; j < d; j++) {
                sum2 +=  ((j+1) + 10)*(Math.pow(vector[j],i+1) - (Math.pow(1/(j+1), i+1)) ) ;
            }
            sum1 += Math.pow(sum2, 2);
        }
        
        return sum1;
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
            v[i] = 1/(i+1);
        }
        return v;
    }

    @Override
    public String getNombre() {
        return nombre;
    }
    
}
