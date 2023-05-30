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
public class FuncionRosenbrock extends Evaluador{
    final private String nombre = "Rosenbrock";
    final private double rango_min = -5;
    final private double rango_max = 10;
    int d;
    
    public FuncionRosenbrock(Configurador config){
        d = config.getDimension();
    }
    
    @Override
    public double evaluar(double[] vector){
        double sum = 0.0;
        
        for (int i = 0; i < d-1; i++) {
            sum += 100*Math.pow(vector[i+1] - Math.pow(vector[i], 2),2) + Math.pow((vector[i]-1),2);
        }
        
        return sum;
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
            v[i] = 1;
        }
        return v;
    }

    @Override
    public String getNombre() {
        return nombre;
    }
    
}
